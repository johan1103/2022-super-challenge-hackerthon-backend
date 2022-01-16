package INHA_super_challenge_hackathon_HitechisFuture.web.self_diagnosis;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
public class SelfDiagnoseForm {
    @NotEmpty
    @JsonFormat(pattern = "yyyy.mm.dd")
    // 자가진단 시행 날짜
    private Date date;
    @NotEmpty
    // 체온
    private double fever;
    @NotEmpty
    // 2주 내에 해외 방문 여부
    private boolean visitOverseas;
    @NotEmpty
    // 자가격리중인 가족 유무
    private boolean haveSelfQuarantineFamily;
    @NotEmpty
    // 확진자 접촉 여부
    private boolean contactConfirmedPerson;
    @NotEmpty
    // 고위험 시설 방문 여부
    private boolean visitHighRiskFacilities;
    @NotEmpty
    // 관련 증상
    private List<symptom> symptomsArray;



    // 어차피 @RequiredArgsConstructor 를 사용
    /*public SelfDiagnoseForm (Date date, int fever, boolean visitOverseas, boolean haveSelfQuarantineFamily,
                            boolean contactConfirmedPerson, boolean visitHighRiskFacilities,
                            List<symptom> symptomsArray) {
        this.date = date;
        this.fever = fever;
        this.visitOverseas = visitOverseas;
        this.haveSelfQuarantineFamily = haveSelfQuarantineFamily;
        this.contactConfirmedPerson = contactConfirmedPerson;
        this.visitHighRiskFacilities = visitHighRiskFacilities;
        this.symptomsArray = symptomsArray;
    }*/
}
