package INHA_super_challenge_hackathon_HitechsFuture.web.self_diagnosis;

import INHA_super_challenge_hackathon_HitechsFuture.web.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

// 스프링 빈이 아닌 일반 클래스
public class MemoryPersonalDiagnosisFormRepository implements PersonalDiagnoseFormRepository {

    private Member member;
    private ArrayList<SelfDiagnoseForm> selfDiagnoseForms;


    @Override
    public void save(SelfDiagnoseForm selfDiagnoseForm) {

        // TODO
        // 중복처리는 아직 하지 않음
        // 자가진단을 한 번 하면 버튼을 비활성화시키는 등으로 두 번 일어나는 일이 아예 없도록 설계하는 방향이 나을듯
        selfDiagnoseForms.add(selfDiagnoseForm);
    }

    @Override
    public Optional<SelfDiagnoseForm> findByDate(Date date) {
        for (SelfDiagnoseForm selfDiagnoseForm : selfDiagnoseForms) {
            if (selfDiagnoseForm.getDate().equals(date)) {
                // 찾으려는 날짜의 것이면
                return Optional.of(selfDiagnoseForm);
            }
        }
        // 찾으려는 자가진단서가 없으면
        return Optional.empty();
    }

    @Override
    public ArrayList<SelfDiagnoseForm> findAll() {
        return selfDiagnoseForms;
    }

    @Override
    public Member getMember() {
        return member;
    }

    public MemoryPersonalDiagnosisFormRepository(Member member) {
        this.member = member;
        this.selfDiagnoseForms = new ArrayList<>();
    }
}
