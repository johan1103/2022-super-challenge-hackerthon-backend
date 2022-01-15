package INHA_super_challenge_hackathon_HitechsFuture.web.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    // /members 를 받았을 떄 실행하는 멤버 컨트롤러

    private final MemberRepository memberRepository;


    // /members/add 를 받았을 때 실행
    @GetMapping("/add")
    public String createForm() {
        return "members/createForm";
    }


}
