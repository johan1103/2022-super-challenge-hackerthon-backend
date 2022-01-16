package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import java.util.Optional;

public interface AllDiagnosisFormRepository {

    // 특정인의 자가진단서 repository 조회
    // **************************************
    // 주의 : 특정인의 이름으로 조회하는 것은 안된다. 중복된 이름이 있을 수 있기 떄문이다.
    // 따라서 특정인의 이름으로 조회를 시도하면, 해당 member 객체의 고유 ID를 가져와서 그것으로 조회를 하는 로직을 설계했다.
    // **************************************
    Optional<PersonalDiagnoseFormRepository> findById(Long id);

    // 특정인의 자가진단서 repository 생성
    void save(PersonalDiagnoseFormRepository personalDiagnoseFormRepository);

}
