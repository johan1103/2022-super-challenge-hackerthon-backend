package INHA_super_challenge_hackathon_HitechsFuture.web;

import INHA_super_challenge_hackathon_HitechsFuture.web.cookie.CookieClass;
import INHA_super_challenge_hackathon_HitechsFuture.web.member.Job;
import INHA_super_challenge_hackathon_HitechsFuture.web.member.Member;
import INHA_super_challenge_hackathon_HitechsFuture.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final CookieClass cookieClass;

    // 홈 화면에 대한 처리

    @GetMapping("/")
    @ResponseBody
    public Member home(@CookieValue(name = "memberId", required = false) Long memberId, Model model,
                     HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 로그인된 사용자가 없으면 null data를 JSON 형식으로 return
        if (memberId == null) {
            return null;
        }

        Optional<Member> member = memberRepository.findById(memberId);

        if (member.isPresent()) {
            // 사용자가 존재하면
            // 사용자를 JSON 형식으로 HTTP body에 포함하여 반환

            return member.get();
        }
        else {
            // 쿠키에 저장된 ID에 해당하는 사용자가 유효하지 않은 사용자면
            // 쿠키를 만료시키고
            // 로그인이 되지 않은 사용자와 동일하게 처리
            cookieClass.expireCookie(response, "memberId");
            return null;
        }

        // TODO
        // 선생님과 학생 창을 나누려고 하는데, 프론트엔드에서 넘겨받은 JSON을 처리할 수 있는지 확인 필요
        // 일단 로그인이 되어있으면 홈화면에서 쿠키도 있고, 쿠키를 이용해 HTTP body에 게속 member JSON 을 가지고 있음
        // 로그인이 안되어 있거나 (또는 실패하면) HTTP body에 아무것도 가지고 있지 않음

    }

}
