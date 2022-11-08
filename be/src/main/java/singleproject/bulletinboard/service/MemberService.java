package singleproject.bulletinboard.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import singleproject.bulletinboard.controller.RequestJoinMemberInfo;
import singleproject.bulletinboard.domain.Member;
import singleproject.bulletinboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	public static final Integer FirstUserPoint = 0;
	private final MemberRepository memberRepository;

	// 회원 가입
	public void join(RequestJoinMemberInfo joinMemberInfo) {

		duplicateMemberNameValidate(joinMemberInfo.getName());

		Member member = Member.builder()
			.name(joinMemberInfo.getName())
			.password(joinMemberInfo.getPassword())
			.age(joinMemberInfo.getAge())
			.point(FirstUserPoint)
			.birthday(joinMemberInfo.getBirthday())
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
