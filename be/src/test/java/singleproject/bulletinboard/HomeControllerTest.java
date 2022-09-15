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
}
