package INHA_super_challenge_hackathon_HitechsFuture.web.member;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/*")
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
    // ResponseEntity 사용하지 않은 이유 : post일 때 적절하지 않다.
    @PostMapping("/Login")
    public Optional<Member> loginForm(@RequestBody String id, String password){
        Optional<Member> user = memberRepository.LoginSuccess(id,password);
        if(user == null){
//            return ResponseEntity.noContent().build();
            return null;
        }
        else {
            //여기에 쿠키 구현해야함
            return user;
        }
    }
    /*
    참고문서에 따라 결론부터 말하면 GET과 POST 중에서는 POST가 나음. 하지만 바람직한 건 SSL(HTTPS)이다.
우선 둘 중 POST가 나은 이유를 말하자면 GET 요청은 서버 데이터의 상태를 변경하지 않기 때문에 쿼리를 적극적으로 캐싱할 수 있어 아이디와 비밀번호와 같은 정보를 쿼리에 담아 보내게 되면 보안에 취약할 수 있기 때문이다.(다음페이지가 로딩되거나 다른페이지로 이동할때까지 로그파일이나 사용자 브라우저에 일반텍스트로 표시됨)
반면, POST는 서버의 상태를 변경시키고 동일한 응답을 주며 서버를 동일한 상태로 유지하기위해 캐싱을 하지 않기 때문에 GET보다는 보안에 용이할 수 있다.
예를 들면 5번의 로그인에 실패했을 때 6번째 요청은 "당신의 IP는 30분동안 차단되었습니다." 라고 응답할 수 있다.
     */


}
