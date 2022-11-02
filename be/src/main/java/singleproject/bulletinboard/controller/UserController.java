package singleproject.bulletinboard.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import singleproject.bulletinboard.domain.Member;
import singleproject.bulletinboard.service.MemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final MemberService memberService;

	@GetMapping("/login")
	public String loginForm(@ModelAttribute("loginForm") UserLoginForm loginForm){
		return "page/login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginForm") UserLoginForm loginForm,
		BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			log.info("errors = {}", bindingResult);
			return "page/login";
		}

		Member loginUser = memberService.login(loginForm.getName(),loginForm.getPassword());

		if(loginUser == null){
			log.info("login Fail");
			bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
			return "page/login";
		}

		// TODO 로그인 성공

		return "redirect:/";
	}
}
