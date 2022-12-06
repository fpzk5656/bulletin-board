package singleproject.bulletinboard.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import singleproject.bulletinboard.domain.user.Role;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // 스프링 시큐리티 설정들을 활성화시켜 줍니다. 스프링 시큐리티 필터가 스프링 필터체인에 등록
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().headers().frameOptions().disable()
			.and()
			.authorizeHttpRequests()
			.antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**","/profile").permitAll()
			.antMatchers("/api/v1/**").hasRole(Role.USER.name())
			.anyRequest().authenticated()
			.and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.oauth2Login()
			.userInfoEndpoint()
			.userService(customOAuth2UserService);
		return http.build();
	}
}
