package INHA_super_challenge_hackathon_HitechisFuture.web.covid19_info;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String callApi(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuilder result = new StringBuilder();


        // 오늘의 날짜를 yyyyMMdd 형식으로 변환한다
        // 이후 AddDate 함수를 이용해 어제의 날짜를 계산하여 똑같은 형식으로 맞춘다
        // (COVID19 open api를 받는 규약을 따르기 위함)
        // 당일 기준 5일전부터 데이터 조회
        Date today = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");

        String today_string = transFormat.format(today);
        String prev_string = AddDate(today_string, 0, 0, -5);

        String serviceKey =
                "EVK8sALIQuyk3bk%2FC9vzOIbFTR6lpSjMVmvJ0korF56Z3oDPZCdwVN6TllXO3jLQ5cxsWq0cr9edjdPlko2EQA%3D%3D";


        String urlStr = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" +
                "?serviceKey=" + serviceKey +
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

        // result에 xml을 저장
        result.append("<xmp>");
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        //result.append("</xmp");
        urlConnection.disconnect();
        // xml string 변수
        String result_xml = result.toString();


        // 지금 하고 있는거
        Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(urlStr);
        documentInfo.getDocumentElement().normalize();

        NodeList nList = documentInfo.getElementsByTagName("item");

        Map<String, Covid19Info> map = new HashMap<>();
        // 1st parameter : 날짜에 해당하는 String
        // 2nd parameter : 코로나19 관련 정보 객체

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                // ****************************************************
                // 현재 JSON에 date 형식으로 넣어서 보내는 코드를 작성
                // format만 바꿔주면 원하는 형식에 맞춰 String으로 전달도 가능
                // 필요시 이 부분을 수정할 것
                // ****************************************************
                // 2022.01.17 : Date 객체를 넣을 시 Postman에서 정확한 날짜인데도 하루 날짜가 빠른 15:00 가 된다.
                // GMT 관련인지 알아봐야 할 것 같고, 일단 String으로 날짜 정보를 수정
                // date 형식 : yyyyMMdd

                String date = getTagValue("stateDt", eElement);

                // String으로 받아와서 Integer로 변환
                // 확진자 정보
                int confirmedCase = Integer.parseInt(Objects.requireNonNull(getTagValue("decideCnt", eElement)));
                // 사망자 정보보
               int deadPeople = Integer.parseInt(Objects.requireNonNull(getTagValue("deathCnt", eElement)));



                // 알맞은 날짜 형식에 대해 Data Format을 맞춰주는 SimpleDateFormat 함수
                // 2022.01.17 : 현재 date를 String으로 임시 사용중이므로, 미사용
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");



                // map에 삽입
                map.put(date, new Covid19Info(date, confirmedCase, deadPeople));
            }
        }

        // JSON 객체 생성
        String json = new ObjectMapper().writeValueAsString(map);

        // JSON 객체를 HTTP body에 실어서 반환
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
