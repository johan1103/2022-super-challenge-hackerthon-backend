package INHA_super_challenge_hackathon_HitechisFuture.domain.self_diagnosis;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;
import INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SelfDiagnosisService {
    private final AllDiagnosisFormRepository allDiagnosisFormRepository;

    // 새 회원 자가진단서 repository 추가
    // 회원가입 시 중복 검사를 하므로, 따로 중복검사를 하지 않아도 된다.
    public void join_new_member(Member member) {
        allDiagnosisFormRepository.save(new SingleDiagnosisRepository(member));
    }

    // 특정 회원 자가진단서 추가
    public void create_new_diagnosis(Member member, SelfDiagnoseForm selfDiagnoseForm) {
        Optional<SingleDiagnosisRepository> repository = allDiagnosisFormRepository.findById(member.getId());

        if (repository.isPresent()) {
            repository.get().getSelfDiagnoseForms().add(selfDiagnoseForm);

        }
        else {
            System.out.println("FAIL_TO_ADD");
        }

    }


}
