package INHA_super_challenge_hackathon_HitechisFuture.web.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
        // 학생, 또는 선생님을 DB에 저장
    Optional<Member> findById(Long id);
        // 고유 ID로 멤버 조회
    Optional<Member> findByLoginId(String Id);
        // 로그인 ID로 멤버 조회
    Optional<Member> findByName(String name);
        // 이름으로 멤버 조회
    List<Member> findAllStudent();
        // 모든 학생들의 리스트 반환
    List<Member> findAllTeacher();
        // 모든 선생님들의 리스트 반환
    List<Member> findAll();
        // 모든 멤버들의 리스트 반환

}
