package singleproject.bulletinboard.config.auth;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import singleproject.bulletinboard.config.auth.dto.SessionUser;
import singleproject.bulletinboard.controller.SessionConst;
import singleproject.bulletinboard.domain.user.Member;
import singleproject.bulletinboard.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final MemberRepository memberRepository;

	private final HttpSession httpSession; // 임시

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByName(username)
			.orElseThrow(() -> new UsernameNotFoundException("not found " + username));

		// UserDetails 구현체인 User로 빌드해서 받으며 아이디 비번 검증
		UserDetails userDetails = User.builder()
			.username(member.getName())
			.roles(member.getRole().name())
			.password(member.getPassword())
			.passwordEncoder(p -> createDelegatingPasswordEncoder().encode(p)) // 비밀번호 검증
			.build();

		// 비밀번호 검증까지 통과했다면 세션 등록
		httpSession.setAttribute(SessionConst.LOGIN_USER, new SessionUser(member));

		return userDetails;
	}
}
