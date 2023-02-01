package singleproject.bulletinboard.controller;

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

	@GetMapping("/user/login")
	public String loginForm(@ModelAttribute("loginForm") UserLoginForm loginForm) {
		return "page/login";
	}

	@PostMapping("/user/login")
	public String login(@Valid @ModelAttribute("loginForm") UserLoginForm loginForm,
		BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL) {
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

		// 로그인에 성공 시 처음 요청한 URL로 이동
		return "redirect:" + redirectURL;
	}
}
