package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class JpaAllDiagnosisFormRepository implements AllDiagnosisFormRepository {

    private final EntityManager em;

    public JpaAllDiagnosisFormRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<SingleDiagnosisRepository> findById(Long userId) {
        List<SingleDiagnosisRepository> result = em
                .createQuery("select m from SingleDiagnosisRepository m where m.userId = :userId",
                        SingleDiagnosisRepository.class)
                .setParameter("userId", userId).getResultList();

        return result.stream().findAny();
    }

    @Override
    public void save(SingleDiagnosisRepository singleDiagnosisRepository) {
        em.persist(singleDiagnosisRepository);
    }


    // 테스트용
    @Override
    public int getSize() {
        return 0;
    }
}
