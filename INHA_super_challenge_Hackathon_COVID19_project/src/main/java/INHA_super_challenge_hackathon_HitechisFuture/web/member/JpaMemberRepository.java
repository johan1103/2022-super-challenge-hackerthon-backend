package INHA_super_challenge_hackathon_HitechisFuture.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

//@Repository
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // DB에 member을 저장
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByLoginId(String login_Id) {
        List<Member> result = em.createQuery("select m from Member m where m.loginId = :login_Id", Member.class)
                .setParameter("loginId", login_Id).getResultList();

        return result.stream().findAny();
    }

    // TODO
    // 로직 생각하고 변경 필요
    // 이름은 중복 가능한데, 하나만 찾도록 되어 있음.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();

        return result.stream().findAny();
    }

    // TODO
    // 쿼리에 대해서 맞는지 검증 필요
    @Override
    public List<Member> findAllStudent() {
        return em.createQuery("select m from Member m where m.Job = STUDENT", Member.class)
                .getResultList();
    }

    @Override
    public List<Member> findAllTeacher() {
        return em.createQuery("select m from Member m where m.Job = TEACHER", Member.class)
                .getResultList();
    }

    @Override
    public List<Member> findAll() {
        // 쿼리를 이용해 모든 DB에 저장된 member을 찾고, 이를 리스트로 반환한다.
        // select m 의 의미는 m이라는 엔티티를 그냥 m 그대로 가져다 쓰겠다는 의미이다.
        // JPQL
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}


// 2022_01_16
// TODO
// H2 DB 이용해서 memberRepository로 이식하는 작업 필요