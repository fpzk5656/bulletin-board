package singleproject.bulletinboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import singleproject.bulletinboard.config.auth.dto.SessionUser;
import singleproject.bulletinboard.service.MemberService;

@RequiredArgsConstructor
@Controller
public class HomeController {

	private final MemberService memberService;

	@GetMapping("/")
	public String home(
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) SessionUser loginMember,
		Model model) {

		if (loginMember != null) {

			model.addAttribute("member",
				ResponseUserInfo.from(
					memberService.findByName(loginMember.getName()).orElseThrow()));
			return "page/loginHome";
		}

		return "home";
	}
}
