package singleproject.bulletinboard.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import singleproject.bulletinboard.domain.user.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@After
	public void cleanup() {
		memberRepository.deleteAll();
	}

	@Test
	public void 유저정보_불러오기() {
		// given
		String name = "홍길동";
		Integer age = 20;

		memberRepository.save(Member.builder()
			.name(name)
			.age(age)
			.build());

		// when
		List<Member> members = memberRepository.findAll();

		// then
		Member member = members.get(0);
		assertThat(member.getName()).isEqualTo(name);
		assertThat(member.getAge()).isEqualTo(age);
	}
}
