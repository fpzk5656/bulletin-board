package singleproject.bulletinboard.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import singleproject.bulletinboard.domain.user.Member;

class MemoryMemberRepositoryTest {

	private MemoryMemberRepository memberRepository;

	@BeforeEach
	public void setUp() {
		memberRepository = new MemoryMemberRepository();
	}

	@Test
	void save() {
		// given
		Member member1 = Member.builder()
			.name("김철수")
			.build();
		// when
		Member member2 = memberRepository.save(member1);
		// then
		assertThat(member1.getName()).isEqualTo(member2.getName());
	}

	@Test
	void findById() {
		// given
		Member member1 = memberRepository.save(new Member());
		// when
		Member member2 = memberRepository.findById(1L).orElseThrow();
		// then
		assertThat(member1).isEqualTo(member2);
	}

	@Test
	void findByName() {
		// given
		String memberName = "아리랑";
		Member member1 = Member.builder()
			.name(memberName)
			.build();
		memberRepository.save(member1);
		// when
		Member member2 = memberRepository.findByName(memberName).orElseThrow();
		// then
		assertThat(member1.getName()).isEqualTo(member2.getName());
	}

	@Test
	void findAll() {
		// given
		Member member1 = new Member();
		Member member2 = new Member();
		memberRepository.save(member1);
		memberRepository.save(member2);
		// when
		List<Member> result = memberRepository.findAll();
		// then
		assertThat(result.size()).isEqualTo(2);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
}
