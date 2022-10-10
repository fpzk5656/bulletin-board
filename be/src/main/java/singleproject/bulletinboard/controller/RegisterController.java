package singleproject.bulletinboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import singleproject.bulletinboard.service.MemberService;

@Controller
@RequiredArgsConstructor
public class RegisterController {

	private final MemberService memberService;

	@GetMapping("/")
	public String goHome() {
		return "home";
	}

	@PostMapping("/register/join")
	public String joinMembership(@RequestBody RequestJoinMemberInfo joinMemberInfo) {

		memberService.join(joinMemberInfo);
		return "redirect:/";
	}
}
