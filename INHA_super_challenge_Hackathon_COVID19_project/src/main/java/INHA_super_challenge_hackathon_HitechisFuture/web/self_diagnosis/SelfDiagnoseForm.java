package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import INHA_super_challenge_hackathon_HitechisFuture.web.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "selfDiagnoseForms")
public class SelfDiagnoseForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long sequence;
    // 각 자가진단서의 고유 ID


    // 추가
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    // 추가

    @NotNull
    @JsonFormat(pattern = "yyyy.mm.dd")
    // 자가진단 시행 날짜
    private Date date;
    @NotNull
    // 체온
    private double fever;
    @NotNull
    // 2주 내에 해외 방문 여부
    private boolean visitOverseas;
    @NotNull
    // 자가격리중인 가족 유무
    private boolean haveSelfQuarantineFamily;
    @NotNull
    // 확진자 접촉 여부
    private boolean contactConfirmedPerson;
    @NotNull
    // 고위험 시설 방문 여부
    private boolean visitHighRiskFacilities;

    // 기침 : 1
    @NotNull
    private boolean cough;

    // 호흡 곤란 : 2
    @NotNull
    private boolean shortness_OF_BREATH;

    // 오한 : 3
    @NotNull
    private boolean chills;

    // 근육통 : 4
    @NotNull
    private boolean arches_PAINS;

    // 두통 : 5
    @NotNull
    private boolean headache;

    // 인후통 : 6
    @NotNull
    private boolean sore_THROAT;

    // 미각 상실 : 7
    @NotNull
    private boolean loss_OF_TASTE;

    // 후각 상실 : 8
    @NotNull
    private boolean loss_OF_SMELL;



}
