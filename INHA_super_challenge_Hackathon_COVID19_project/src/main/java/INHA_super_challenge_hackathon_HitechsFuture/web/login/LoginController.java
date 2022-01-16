package INHA_super_challenge_hackathon_HitechsFuture.web.login;

import INHA_super_challenge_hackathon_HitechsFuture.web.cookie.CookieClass;
import INHA_super_challenge_hackathon_HitechsFuture.domain.login.LoginService;
import INHA_super_challenge_hackathon_HitechsFuture.web.member.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CookieClass cookieClass;

    @PostMapping("/login")
    public String loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 데이터를 받는 형식
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        // String으로 받아서 넘겨줘도 objectMapper가 알아서 JSON 처리를 해준다.

        LoginForm loginForm = objectMapper.readValue(messageBody, LoginForm.class);

        if (loginForm == null) {
            // 로그인 실패
            // 사실 애매한 코드, loginForm이 받을때부터 빌 가능성이 있나?
            // NotEmpty가 있으니까 안 그럴거 같긴함
            return "EXCEPTION_LOGINFORM_EMPTY";
            // fail이라는 body를 HTTP에 넣어서 return
        }
        Member member = loginService.login(loginForm.getLoginId(), loginForm.getPassword());

        if (member == null) {
            // 이건 잘못된 정보로 인한 로그인 실패
            return "EXCEPTION_WRONG_ID_OR_PASSWORD";
        }

        // 로그인 성공 : 쿠키를 부여
        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(idCookie);
        // 고유 ID에 대한 정보를 저장


        // 로그인 성공
        return "LOGIN_SUCCESS";


        // TODO
        // 생각해봐야할 점 : 일단 당장은 로그인을 했을 때 Member을 JSON으로 넘겨주지 않고, 로그인 성공에 대한 상태만 넘겨주었다.
        // 로그인을 성공하면 다시 홈 화면이나 다른 학생/학부모에 알맞은 창으로 넘어갈거라고 생각해서이다.
        // 만약 로그인을 성공하자마자 멤버에 대해 필요하다면 멤버를 memberRepository에서 꺼내서 return하면 된다.

    }


    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        cookieClass.expireCookie(response, "memberId");

        return "LOGOUT_SUCCESS";
    }


}
