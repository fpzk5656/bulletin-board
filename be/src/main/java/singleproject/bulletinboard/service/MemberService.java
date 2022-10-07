package singleproject.bulletinboard.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import singleproject.bulletinboard.domain.Member;
import singleproject.bulletinboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	// 회원 가입
	public Long join(Member member) {
		// TODO 중복 회원 검증 추가 필요

		memberRepository.save(member);
		return member.getId();
	}

	// 전체 회원 조회
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	// 특정 회원 조회
	public Optional<Member> findById(Long id) {
		return memberRepository.findById(id);
	}
}
