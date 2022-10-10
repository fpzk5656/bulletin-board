package singleproject.bulletinboard.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import singleproject.bulletinboard.controller.RequestJoinMemberInfo;
import singleproject.bulletinboard.domain.Member;
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
		RequestJoinMemberInfo joinMemberInfo = new RequestJoinMemberInfo("철수", "123", 25,
			LocalDateTime.of(1999, 12, 31, 00, 00, 00));
		memberService.join(joinMemberInfo);
		// when
		String member1Name = memberService.findById(1L).orElseThrow().getName();
		// then
		assertThat(member1Name).isEqualTo(joinMemberInfo.getName());
	}

	@Test
	void findMembers() {
		// given
		RequestJoinMemberInfo joinMemberInfo1 = new RequestJoinMemberInfo("철수", "123", 25,
			LocalDateTime.of(1999, 12, 31, 00, 00, 00));
		RequestJoinMemberInfo joinMemberInfo2 = new RequestJoinMemberInfo("영희", "456", 27,
			LocalDateTime.of(1997, 1, 1, 00, 00, 00));
		memberService.join(joinMemberInfo1);
		memberService.join(joinMemberInfo2);
		// when
		List<Member> members = memberService.findMembers();
		// then
		assertThat(members.size()).isEqualTo(2);
	}

	@Test
	void findById() {
		// given
		RequestJoinMemberInfo joinMemberInfo1 = new RequestJoinMemberInfo("철수", "123", 25,
			LocalDateTime.of(1999, 12, 31, 00, 00, 00));
		RequestJoinMemberInfo joinMemberInfo2 = new RequestJoinMemberInfo("영희", "456", 27,
			LocalDateTime.of(1997, 1, 1, 00, 00, 00));
		memberService.join(joinMemberInfo1);
		memberService.join(joinMemberInfo2);
		// when
		String member1Name = memberService.findById(1L).orElseThrow().getName();
		String member2Name = memberService.findById(2L).orElseThrow().getName();
		// then
		assertThat(joinMemberInfo1.getName()).isEqualTo(member1Name);
		assertThat(joinMemberInfo2.getName()).isEqualTo(member2Name);
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
}
