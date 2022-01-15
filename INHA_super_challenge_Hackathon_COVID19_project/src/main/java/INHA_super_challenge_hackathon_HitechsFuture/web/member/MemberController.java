package INHA_super_challenge_hackathon_HitechsFuture.web.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    // /members 를 받았을 떄 실행하는 멤버 컨트롤러

    private final MemberRepository memberRepository;


    // /members/add 를 받았을 때 실행
    @GetMapping("/add")
    public String createForm(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);


        return "members/createForm";
    }

    // 주석
    /*public String createForm() {
        return "members/createForm";
    }*/


}
