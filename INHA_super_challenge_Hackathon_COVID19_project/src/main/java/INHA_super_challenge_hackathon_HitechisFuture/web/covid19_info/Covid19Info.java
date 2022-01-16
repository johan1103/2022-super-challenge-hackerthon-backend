package INHA_super_challenge_hackathon_HitechisFuture.web.covid19_info;

import lombok.Data;

import java.util.Date;

@Data
public class Covid19Info {
    // 기준 일자
    private Date date;

    // 확진자 수
    private int confirmedCase;

    // 사망자 수
    private int deadPeople;

    public Covid19Info(Date date, int confirmedCase, int deadPeople) {
        this.date = date;
        this.confirmedCase = confirmedCase;
        this.deadPeople = deadPeople;
    }
}
