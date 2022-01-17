package INHA_super_challenge_hackathon_HitechisFuture;

import INHA_super_challenge_hackathon_HitechisFuture.domain.member.MemberService;
import INHA_super_challenge_hackathon_HitechisFuture.web.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ChoApplication {


	public static void main(String[] args) {

		// 테스트 코드
		/*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		MemberRepository memberRepository = applicationContext.getBean(MemberRepository.class);
		MemberService memberService = applicationContext.getBean(MemberService.class);
		Member member = new Member("chominho96", "12345", "CHO", 24, Gender.MAN, Job.STUDENT);
		memberService.join(member);*/


		SpringApplication.run(ChoApplication.class, args);

	}

}
