package singleproject.bulletinboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "page/login";
	}

	@GetMapping("/register")
	public String goRegister() {
		return "page/register";
	}

	@GetMapping("/board")
	public String goBoard() {
		return "page/login";
	}
}
