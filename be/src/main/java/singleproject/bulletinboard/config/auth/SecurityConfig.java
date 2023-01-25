package singleproject.bulletinboard.config.auth;

import static org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import singleproject.bulletinboard.domain.user.Role;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity  // 스프링 시큐리티 설정들을 활성화시켜 줍니다. 스프링 시큐리티 필터가 스프링 필터체인에 등록
public class SecurityConfig {

	private final CustomOAuth2UserService customOAuth2UserService;
	private final CustomUserDetailService customUserDetailService;

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(createDelegatingPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(customUserDetailService);
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf().disable().headers().frameOptions().disable()
			.and()
			.authorizeHttpRequests()
			.antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**","/register","/members","/board/**","/user/login","/profile").permitAll()   // 인증 없이도 접근가능하게 설정
			.antMatchers("/board/article/**").hasRole(Role.USER.name())
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/user/login")
			.usernameParameter("name")
			.passwordParameter("password")
			.loginProcessingUrl("/authenticate")   // 로그인 페이지에 가려하니 이대로 옴
			.defaultSuccessUrl("/board", true) // 로그인 성공 후 가게될 곳
			.successHandler(
				new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
						System.out.println("authentication : " + authentication.getName());
						response.sendRedirect("/"); // 인증이 성공한 후에는 root로 이동
					}
				}
			)
			.failureHandler(
				new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
						System.out.println("exception : " + exception.getMessage());
						response.sendRedirect("/login");
					}
				}
			)
			.permitAll()
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
