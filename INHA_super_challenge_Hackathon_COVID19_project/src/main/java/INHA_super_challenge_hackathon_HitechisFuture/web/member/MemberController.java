package INHA_super_challenge_hackathon_HitechisFuture.web.member;


import INHA_super_challenge_hackathon_HitechisFuture.domain.member.MemberService;
import INHA_super_challenge_hackathon_HitechisFuture.domain.self_diagnosis.SelfDiagnosisService;
import INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis.AllDiagnosisFormRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
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
    private final SelfDiagnosisService selfDiagnosisService;

    private final AllDiagnosisFormRepository allDiagnosisFormRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    // ObjectMapper로 값을 특정 클래스로 매핑해준다.


    // : /members/sign_up 주소를 받았을 때 실행
    @PostMapping("/sign_up")
    public void createForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 여기서 넘겨받는 데이터 : JSON (request에 해당)

        // <입력 JSON 데이터 형식>
        // ********************************************************************************************************
        // { "loginId" : 로그인 ID, "password" : 비밀번호, "name" : 이름, "age" : 나이, "gender" : 성별, "job" : 직업 }
        // ********************************************************************************************************

        ServletInputStream inputStream = request.getInputStream();
        // 데이터를 받는 형식
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // String으로 받아서 넘겨줘도 objectMapper가 알아서 JSON 처리를 해준다.

        Member member = objectMapper.readValue(messageBody, Member.class);

        // 2022.01.17
        // TODO : member가 생성자를 통해 생성이 되는게 아닌듯함. 처음에 true로 초기화되어야 하지만 false로 설정되어 들어감.
        member.setSelf_diagnosis_notification(true);


        // 예외 처리 : 로그인 ID가 중복되면 회원가입이 불가능해야함
        if (memberRepository.findByLoginId(member.getLoginId()).isPresent()) {
            // 이미 존재하는 로그인 ID이면
            response.getWriter().write("EXCEPTION_LOGIN_ID_DUPLICATION");
            // 회원 가입 실패 메세지를 HTTP body에 삽입
        }
        else {
            // 1. 회원 삽입
            memberService.join(member);


            // 2. 해당 회원 자가진단서 생성 및 추가
            selfDiagnosisService.join_new_member(member);

            System.out.println("자가진단서 repository 크기(Test용) : " + allDiagnosisFormRepository.getSize());


            // 3. 정상 객체 생성 알림
            // 여기다가 로그인이 되었다는 신호를 넘겨주면 된다.
            // 만약 다시 JSON으로 넘겨줄 일이 있다면 return 타입을 바꾸면 된다.
            // return "str"에서 str이 그냥 HTTP의 body에 해당한다.
            response.getWriter().write("SIGN_UP_COMPLETE");
        }
    }
}
