package singleproject.bulletinboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import singleproject.bulletinboard.domain.user.Member;
import singleproject.bulletinboard.service.MemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

	private final MemberService memberService;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") UserLoginForm loginForm) {
		return "page/login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginForm") UserLoginForm loginForm,
		BindingResult bindingResult, HttpServletRequest request,
		@RequestParam(defaultValue = "/") String redirectURL) {
		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "page/login";
		}

		Member loginMember = memberService.login(loginForm.getName(), loginForm.getPassword());

		if (loginMember == null) {
			log.info("login Fail");
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "page/login";
		}

		// 로그인 성공
		// 세션이 있으면 세션 반환, 없다면 신규 세션을 생성한다.
		HttpSession session = request.getSession();

		// 세션에 회원 로그인 정보 보관
		session.setAttribute(SessionConst.LOGIN_USER, loginMember);

		// 로그인에 성공 시 처음 요청한 URL로 이동
		return "redirect:" + redirectURL;
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			// 세션 무효화
			session.invalidate();
		}
		return "redirect:/";
	}
}
