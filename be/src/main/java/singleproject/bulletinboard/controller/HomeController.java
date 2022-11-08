package singleproject.bulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import singleproject.bulletinboard.domain.Member;

@Controller
public class HomeController {

	@GetMapping("/")
	public String loginHome(
		@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) Member loginUser,
		Model model) {
		if(loginUser == null){
			return "home";
		}

		model.addAttribute("user", ResponseUserInfo.from(loginUser));
		return "page/loginHome";
	}
}
