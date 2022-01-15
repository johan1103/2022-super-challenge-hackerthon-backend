package INHA_super_challenge_hackathon_HitechsFuture.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @NotEmpty
    private int age;
    @NotEmpty
    private Gender gender;
    @NotEmpty
    private Job job;

    public Member(String loginId, String password, String name, int age, Gender gender, Job job) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.job = job;
    }


}
