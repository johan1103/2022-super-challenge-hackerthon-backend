package INHA_super_challenge_hackathon_HitechsFuture.domain.service;

import INHA_super_challenge_hackathon_HitechsFuture.web.member.Member;
import INHA_super_challenge_hackathon_HitechsFuture.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입
    public Long join(Member member) {
        memberRepository.findBy

    }


}
