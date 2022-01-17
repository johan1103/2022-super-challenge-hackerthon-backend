package INHA_super_challenge_hackathon_HitechisFuture.domain.login;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;
import INHA_super_challenge_hackathon_HitechisFuture.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).
                filter(m -> m.getPassword().equals(password)).orElse(null);
    }
}
