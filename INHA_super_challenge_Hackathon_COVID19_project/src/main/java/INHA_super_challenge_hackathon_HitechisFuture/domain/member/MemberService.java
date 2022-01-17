package INHA_super_challenge_hackathon_HitechisFuture.domain.member;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;
import INHA_super_challenge_hackathon_HitechisFuture.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;


    // 회원가입
    public void join(Member member) {
        //validateDuplicateMember(member);
        memberRepository.save(member);
        //return member.getId();
    }

    // 회원 ID 중복처리
    // 중복되는 내용이고, 실패시 JSON으로 return 할 것이므로 일단 생략

    /*private void validateDuplicateMember(Member member) {
        memberRepository.findByLoginId(member.getLoginId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원ID입니다.");
                });
    }*/


}
