package singleproject.bulletinboard;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest
class HomeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@DisplayName("홈페이지로 리다이렉트")
	@Test
	void redirectHomePage() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/home");
		mockMvc.perform(requestBuilder)
			.andExpect(status().is3xxRedirection());
	}
}
