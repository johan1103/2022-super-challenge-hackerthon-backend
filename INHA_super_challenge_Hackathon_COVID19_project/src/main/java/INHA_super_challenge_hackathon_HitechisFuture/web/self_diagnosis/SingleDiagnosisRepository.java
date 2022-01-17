package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SingleDiagnosisRepository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long sequence;
    // 각 자가진단서 repository의 고유 ID

    private Long userId;
    // 해당 repository의 memeber의 ID
    //private Member member;

    @OneToMany(targetEntity=SelfDiagnoseForm.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private List<SelfDiagnoseForm> selfDiagnoseForms;



    // 기본 생성자
    public SingleDiagnosisRepository() {

    }
    public SingleDiagnosisRepository(Member member) {
        //this.member = member;
        this.userId = member.getId();
        this.selfDiagnoseForms = new ArrayList<>();
    }
}
