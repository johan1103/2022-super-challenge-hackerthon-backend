package INHA_super_challenge_hackathon_HitechsFuture.web.member;


import INHA_super_challenge_hackathon_HitechsFuture.domain.member.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    // : /members 주소를 받았을 떄 실행하는 멤버 컨트롤러

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    // ObjectMapper로 값을 특정 클래스로 매핑해준다.


    // : /members/add 주소를 받았을 때 실행
    @GetMapping("/add")
    public void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 여기서 넘겨받는 데이터 : JSON (request에 해당)
        // { "loginId" : 로그인 ID, "password" : 비밀번호, "name" : 이름, "age" : 나이, "gender" : 성별, "job" : 직업 }
        // 과 같이 JSON을 넘겨받는다고 가정한다.
        ServletInputStream inputStream = request.getInputStream();
        // 데이터를 받는 형식
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // String으로 받아서 넘겨줘도 objectMapper가 알아서 JSON 처리를 해준다.

        Member member = objectMapper.readValue(messageBody, Member.class);

        memberService.join(member);
        // 회원 삽입

        System.out.println("member = " + member);
        // 확인용 코드



        // 정상 객체 생성 알림
        // 여기다가 로그인이 되었다는 신호를 넘겨주면 된다.
        // 만약 다시 JSON으로 넘겨줄 일이 있다면 return 타입을 바꾸면 된다.
        // return "str"에서 str이 그냥 HTTP의 body에 해당한다.
        response.getWriter().write("ok");
    }



}
