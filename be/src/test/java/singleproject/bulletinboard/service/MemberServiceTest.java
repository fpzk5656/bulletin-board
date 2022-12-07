package singleproject.bulletinboard.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import singleproject.bulletinboard.controller.RequestJoinUserInfo;
import singleproject.bulletinboard.domain.user.Member;
import singleproject.bulletinboard.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void join() {
		// given
		RequestJoinUserInfo joinMemberInfo = new RequestJoinUserInfo("철수", "123", "eee@naver.com",
			25,
			LocalDate.of(1999, 12, 31));
		memberService.join(joinMemberInfo);
		// when
		List<Member> members = memberService.findMembers();
		// then
		assertThat(members.size()).isEqualTo(1);
	}

	@Test
	void findMembers() {
		// given
		RequestJoinUserInfo joinMemberInfo1 = new RequestJoinUserInfo("철수", "123", "eee@naver.com",
			25,
			LocalDate.of(1999, 12, 31));
		RequestJoinUserInfo joinMemberInfo2 = new RequestJoinUserInfo("영희", "456", "eee@naver.com",
			27,
			LocalDate.of(1999, 12, 31));
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
		RequestJoinUserInfo joinMemberInfo1 = new RequestJoinUserInfo("철수", "123", "eee@naver.com",
			25,
			LocalDate.of(1999, 12, 31));
		RequestJoinUserInfo joinMemberInfo2 = new RequestJoinUserInfo("영희", "456", "eee@naver.com",
			27,
			LocalDate.of(1999, 12, 31));
		memberService.join(joinMemberInfo1);
		memberService.join(joinMemberInfo2);
		// when
		String member1Name = memberService.findById(1L).orElseThrow().getName();
		String member2Name = memberService.findById(2L).orElseThrow().getName();
		// then
		assertThat(joinMemberInfo1.getName()).isEqualTo(member1Name);
		assertThat(joinMemberInfo2.getName()).isEqualTo(member2Name);
	}
}
