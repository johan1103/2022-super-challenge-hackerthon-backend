package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryAllDiagnosisFormRepository implements AllDiagnosisFormRepository {

    private static Map<Member, PersonalDiagnoseFormRepository> diagnoseFormMap = new HashMap<>();
    // <멤버 객체, 자가진단서 repository> 로 구성된 MAP

    @Override
    public Optional<PersonalDiagnoseFormRepository> findById(Long id) {

        // key값 중 받은 id와 일치하는 id를 가진 멤버가 있는지 탐색
        Optional<Member> result = diagnoseFormMap.keySet().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();

        // 멤버가 있으면
        if (result.isPresent()) {
            // 해당 멤버의 repository 반환
            return Optional.ofNullable(diagnoseFormMap.get(result.get()));
        }
        // 멤버가 없으면
        else {
            // 빈 객체 반환
            return Optional.empty();
        }
    }

    @Override
    public void save(PersonalDiagnoseFormRepository personalDiagnoseFormRepository) {
        diagnoseFormMap.put(personalDiagnoseFormRepository.getMember(), personalDiagnoseFormRepository);
    }


}
