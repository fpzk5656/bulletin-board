package singleproject.bulletinboard.controller;

import static org.assertj.core.api.Assertions.*;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import singleproject.bulletinboard.domain.Member;
import singleproject.bulletinboard.repository.MemoryMemberRepository;
import singleproject.bulletinboard.service.MemberService;

class RegisterControllerTest {

	private RegisterController registerController;
	private MemoryMemberRepository memberRepository;
	private MemberService memberService;
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.memberRepository = new MemoryMemberRepository();
		this.memberService = new MemberService(memberRepository);
		this.registerController = new RegisterController(memberService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
	}


	@Test
	void goHome() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("home"));
	}

	@Test
	void joinMembership() throws Exception {
		// given
		Member member1 = Member.builder()
			.name("철수")
			.build();

		String con = new ObjectMapper().writeValueAsString(member1);

		// when
		mockMvc.perform(post("/register/join")
				.content(con)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is3xxRedirection())
			.andDo(print());

		Member member2 = memberService.findById(1L).orElseThrow();

		// then
		assertThat(member1.getName()).isEqualTo(member2.getName());
	}

	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
}
