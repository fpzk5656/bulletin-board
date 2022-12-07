package singleproject.bulletinboard.config.auth;

import java.util.Collections;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import singleproject.bulletinboard.config.auth.dto.OAuthAttributes;
import singleproject.bulletinboard.config.auth.dto.SessionUser;
import singleproject.bulletinboard.domain.user.Member;
import singleproject.bulletinboard.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final MemberRepository memberRepository;
	private final HttpSession httpSession;


	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		// 현재 로그인 진행 중인 서비스를 구분하는 코드
		String registrationId = userRequest
			.getClientRegistration().getRegistrationId();

		// OAuth2 로그인 진행 시 키가 되는 필드값에 해당함. Primary Key와 같은 의미
		// 구글만 기본적인 코드를 지원("sub")하고, 네이버 카카오 등은 기본 지원하지 않는다함
		// 네이버 로그인과 구글 로그인을 동시 지원할 때 사용
		String userNameAttributeName = userRequest
			.getClientRegistration().getProviderDetails()
			.getUserInfoEndpoint().getUserNameAttributeName();

		// OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
		// 네이버 등 다른 소셜 로그인도 이 클래스를 사용함
		OAuthAttributes attributes = OAuthAttributes
			.of(registrationId, userNameAttributeName,
				oAuth2User.getAttributes());

		Member member = saveOrUpdate(attributes);
		httpSession.setAttribute("user",
			new SessionUser(member)); // SessionUser는 세션에 사용자 정보를 저장하기 위한 Dto 클래스

		return new DefaultOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
			attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	private Member saveOrUpdate(OAuthAttributes attributes) {
		Member member = memberRepository.findByEmail(attributes.getEmail())
			.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
			.orElse(attributes.toEntity());

		return memberRepository.save(member);
	}
}
