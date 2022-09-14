package singleproject.bulletinboard;

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

	@DisplayName("홈페이지로 리다이렉트")
	@Test
	void redirectHomePage() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is3xxRedirection());
	}
}
