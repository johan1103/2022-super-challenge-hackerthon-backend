/*
package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@Entity
public class MemoryPersonalDiagnosisFormRepository {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequence;
    // 각 자가진단서 repository의 고유 ID

    private Long userId;
    // 해당 repository의 memeber의 ID
    private Member member;

    //@ManyToOne(fetch=FetchType.EAGER)
    //@JoinColumn(name = "diagnosisFormRepository")
    private List<SelfDiagnoseForm> selfDiagnoseForms;


    public void save(SelfDiagnoseForm selfDiagnoseForm) {

        // TODO
        // 중복처리는 아직 하지 않음
        // 자가진단을 한 번 하면 버튼을 비활성화시키는 등으로 두 번 일어나는 일이 아예 없도록 설계하는 방향이 나을듯
        selfDiagnoseForms.add(selfDiagnoseForm);
    }

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

    public List<SelfDiagnoseForm> findAll() {
        return selfDiagnoseForms;
    }

    public Member getMember() {
        return member;
    }

    public MemoryPersonalDiagnosisFormRepository() {

    }
    public MemoryPersonalDiagnosisFormRepository(Member member) {
        this.member = member;
        this.userId = member.getId();
        this.selfDiagnoseForms = new ArrayList<>();
    }
}
*/


// 22.01.17
// JPA 방식으로 업데이트