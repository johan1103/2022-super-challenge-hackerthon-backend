package INHA_super_challenge_hackathon_HitechsFuture.web.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private int age;
    @NotEmpty
    private Job job;
    @NotEmpty
    private Gender gender;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;


}
