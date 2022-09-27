package singleproject.bulletinboard;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import singleproject.bulletinboard.controller.HomeController;

class HomeControllerTest {

	private HomeController homeController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.homeController = new HomeController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@DisplayName("접속 시 홈페이지로 이동")
	@Test
	void goHomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home"));
	}

	@DisplayName("로그인 버튼: 로그인 페이지로 이동한다.")
	@Test
	void goLoginPage() throws Exception {

		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("page/login"));
	}

	@DisplayName("회원가입 버튼: 회원가입 페이지로 이동한다.")
	@Test
	void goRegisterPage() throws Exception {

		mockMvc.perform(get("/register"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("page/register"));
	}

	@DisplayName("게시판 버튼: 게시판 페이지로 이동한다.")
	@Test
	void goBoardPage() throws Exception {
		mockMvc.perform(get("/board"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("page/login"));
	}
}
