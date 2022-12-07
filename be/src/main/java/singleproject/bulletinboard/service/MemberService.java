package singleproject.bulletinboard.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import singleproject.bulletinboard.controller.RequestJoinUserInfo;
import singleproject.bulletinboard.domain.user.Member;
import singleproject.bulletinboard.domain.user.Role;
import singleproject.bulletinboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	public static final Integer FirstUserPoint = 0;
	private final MemberRepository memberRepository;

	// 회원 가입
	@Transactional
	public void join(RequestJoinUserInfo joinMemberInfo) {

		duplicateMemberNameValidate(joinMemberInfo.getName());

		Member member = Member.builder()
			.name(joinMemberInfo.getName())
			.password(joinMemberInfo.getPassword())
			.age(joinMemberInfo.getAge())
			.point(FirstUserPoint)
			.birthday(joinMemberInfo.getBirthday())
			.email(joinMemberInfo.getEmail())
			.role(Role.USER)
			.build();

		memberRepository.save(member);
	}

	// 전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 특정 회원 조회
	public Optional<Member> findById(Long id) {
		return memberRepository.findById(id);
	}

	// 특정 회원 이름으로 조회
	public Optional<Member> findByName(String name) {
		return memberRepository.findByName(name);
	}

	// 중복 회원 이름 검증
	private void duplicateMemberNameValidate(String memberName) {
		if (memberRepository.findByName(memberName).isPresent()) {
			throw new IllegalArgumentException("중복된 회원 이름 입니다.");
		}
	}

	/**
	 * 로그인
	 *
	 * @return null 로그인 실패
	 */
	public Member login(String name, String password) {

		return memberRepository.findByName(name)
			.filter(member -> member.getPassword().equals(password))
			.orElse(null);
	}
}
