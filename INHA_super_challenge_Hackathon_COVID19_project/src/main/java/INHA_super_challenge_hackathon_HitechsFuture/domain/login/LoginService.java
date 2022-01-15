package INHA_super_challenge_hackathon_HitechsFuture.domain.login;

import INHA_super_challenge_hackathon_HitechsFuture.web.member.Member;
import INHA_super_challenge_hackathon_HitechsFuture.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String login_id, String password) {
        return memberRepository.findByLoginId(login_id).
                filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
