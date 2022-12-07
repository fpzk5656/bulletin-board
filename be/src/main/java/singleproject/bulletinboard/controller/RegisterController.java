package singleproject.bulletinboard.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import singleproject.bulletinboard.service.MemberService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RegisterController {

	private final MemberService memberService;

	@GetMapping("/register")
	public String goRegister(@ModelAttribute("joinForm") RequestJoinUserInfo memberJoinInfo) {

		log.info("registry form");
		return "page/register";
	}

	@PostMapping("/register")
	public String joinMembership(
		@Valid @ModelAttribute("joinForm") RequestJoinUserInfo memberJoinInfo,
		BindingResult bindingResult) {

		log.info("register");

		if (bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult);
			return "page/register";
		}

		memberService.join(memberJoinInfo);

		log.info("register success");
		return "redirect:/";
	}
}
