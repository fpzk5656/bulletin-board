package singleproject.bulletinboard;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrlTemplate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import singleproject.bulletinboard.controller.HomeController;

class HomeControllerTest {

	private HomeController homeController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		homeController = new HomeController();
		this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@DisplayName("접속 시 홈페이지로 이동")
	@Test
	void goHomePage() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(forwardedUrlTemplate("home"));
	}

	@DisplayName("홈 버튼: 홈으로 리다이렉트")
	@Test
	void redirectHome() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("redirect:/");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is3xxRedirection());
	}
	@DisplayName("로그인 버튼: 로그인 페이지로 이동한다.")
	@Test
	void goLoginPage() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logi");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(forwardedUrlTemplate("login"));
	}
	@DisplayName("회원가입 버튼: 회원가입 페이지로 이동한다.")
	@Test
	void goRegisterPage() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/register");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(forwardedUrlTemplate("register"));
	}
	@DisplayName("게시판 버튼: 게시판 페이지로 이동한다.")
	@Test
	void goArticlesPage() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/articles/board");
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(forwardedUrlTemplate("board"));
	}
}
