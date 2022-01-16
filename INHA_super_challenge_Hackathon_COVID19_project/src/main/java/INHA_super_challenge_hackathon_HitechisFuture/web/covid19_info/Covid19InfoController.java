package INHA_super_challenge_hackathon_HitechisFuture.web.covid19_info;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class Covid19InfoController {

    private static String AddDate (String strDate, int year, int month, int day) throws Exception {
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance(); Date dt = dtFormat.parse(strDate);
        cal.setTime(dt); cal.add(Calendar.YEAR, year); cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day); return dtFormat.format(cal.getTime());
    }

    @GetMapping("/api")
    public String callApi() throws Exception {
        StringBuilder result = new StringBuilder();

        // 디코딩해야 되는지 안해도 되는지 의문
        String decode_url
                = URLDecoder.decode("EVK8sALIQuyk3bk%2FC9vzOIbFTR6lpSjMVmvJ0korF56Z3oDPZCdwVN6TllXO3jLQ5cxsWq0cr9edjdPlko2EQA%3D%3D",
                "UTF-8");



        // 오늘의 날짜를 yyyyMMdd 형식으로 변환한다
        // 이후 AddDate 함수를 이용해 어제의 날짜를 계산하여 똑같은 형식으로 맞춘다
        // (COVID19 open api를 받는 규약을 따르기 위함)
        // 당일 기준 5일전부터 데이터 조회
        Date today = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");

        String today_string = transFormat.format(today);
        String prev_string = AddDate(today_string, 0, 0, -5);



        String urlStr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" +
                "?serviceKey=" + "EVK8sALIQuyk3bk%2FC9vzOIbFTR6lpSjMVmvJ0korF56Z3oDPZCdwVN6TllXO3jLQ5cxsWq0cr9edjdPlko2EQA%3D%3D" +
                "&pageNo=1" +
                "&numOfRows=10" +
                "&type=xml" +
                "&startCreateDt=" + prev_string +
                "&endCreateDt=" + today_string;

        URL url = new URL(urlStr);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");

        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

        String returnLine;

        // result에 xml이 저장
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine+"\n\r");
        }
        urlConnection.disconnect();

        // xml string 변수
        String result_xml = result.toString();

        // 테스트를 위한 콘솔 출력
        System.out.println(result_xml);

        // xml을 파싱해주는 객체를 생성
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        // xml 문자열을 InputStream으로 변환
        InputStream is = new ByteArrayInputStream(result_xml.getBytes());

        // 파싱 시작
        Document doc = documentBuilder.parse(is);
        doc.getDoctype().normalize();

        // 최상위 노드 탐색
        Element element = doc.getDocumentElement();

            /*// 원하는 태그 데이터 탐색
            NodeList standardDayList = element.getElementsByTagName("STATE_DT");
            NodeList confirmedCaseList = element.getElementsByTagName("DECIDE_CNT");
            NodeList deadPeopleList = element.getElementsByTagName("DEATH_CNT");*/


        // Map에 데이터를 넣어서 JSON으로 변환 후 반환
        Map<Date, Covid19Info> map = new HashMap<>();


        // 각각의 객체에 삽입
        NodeList nList = element.getElementsByTagName("item");

        for (int index = 0; index < nList.getLength(); index++) {
            Node nNode = nList.item(index);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                // xml의 날짜 형식이 yyyy-MM-dd HH:mm:ss.000 형식이므로 알맞게 조정
                String date = Objects.requireNonNull(getTagValue("STATE_DT", eElement)).substring(0, 19);
                // String으로 받아와서 Integer로 변환
                int confirmedCase = Integer.parseInt(Objects.requireNonNull(getTagValue("DECIDE_CNT", eElement)));
                int deadPeople = Integer.parseInt(Objects.requireNonNull(getTagValue("DEATH_CNT", eElement)));

                // 알맞은 날짜 형식에 대해 Data Format을 맞춰주는 SimpleDateFormat 함수
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // map에 삽입
                map.put(transFormat.parse(date), new Covid19Info(transFormat.parse(date),confirmedCase, deadPeople));
            }
        }
        // JSON 객체 생성
        String json = new ObjectMapper().writeValueAsString(map);

        // return
        return json;

    }

    // 태그를 가져와서 해당 데이터를 String으로 바꿔주는 메서드
    private static String getTagValue(String tag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue == null)
            return null;
        return nValue.getNodeValue();
    }
}
