package singleproject.bulletinboard.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import singleproject.bulletinboard.domain.Member;
import singleproject.bulletinboard.repository.MemberRepository;
import singleproject.bulletinboard.repository.MemoryMemberRepository;

class MemberServiceTest {

	private MemberService memberService;
	private MemoryMemberRepository memberRepository;

	@BeforeEach
	public void setUp() {
		this.memberRepository = new MemoryMemberRepository();
		this.memberService = new MemberService(this.memberRepository);
	}

	@Test
	void join() {
		// given
		Member member1 = Member.builder()
			.name("철수")
			.build();
		memberService.join(member1);
		// when
		String member1Name = memberService.findById(1L).orElseThrow().getName();
		// then
		assertThat(member1Name).isEqualTo(member1.getName());
	}

	@Test
	void findMembers() {
		// given
		Member member1 = new Member();
		Member member2 = new Member();
		memberService.join(member1);
		memberService.join(member2);
		// when
		List<Member> members = memberService.findMembers();
		// then
		assertThat(members.size()).isEqualTo(2);
	}

	@Test
	void findById() {
		// given
		Member member1 = Member.builder()
			.name("철수")
			.build();
		Member member2 = Member.builder()
			.name("영희")
			.build();
		memberService.join(member1);
		memberService.join(member2);
		// when
		String member1Name = memberService.findById(1L).orElseThrow().getName();
		String member2Name = memberService.findById(2L).orElseThrow().getName();
		// then
		assertThat(member1.getName()).isEqualTo(member1Name);
		assertThat(member2.getName()).isEqualTo(member2Name);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
}
