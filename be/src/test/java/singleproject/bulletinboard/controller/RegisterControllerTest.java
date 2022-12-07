package singleproject.bulletinboard.controller;

import static org.assertj.core.api.Assertions.*;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import singleproject.bulletinboard.domain.user.Member;
import singleproject.bulletinboard.repository.MemberRepository;
import singleproject.bulletinboard.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RegisterControllerTest {

	@Autowired private RegisterController registerController;
	@Autowired private MockMvc mockMvc;
	@Autowired private MemberRepository memberRepository;
	@Autowired private MemberService memberService;

	@Test
	void joinMembership() throws Exception {
		// given
		Member member1 = Member.builder()
			.name("철수")
			.build();

		String con = new ObjectMapper().writeValueAsString(member1);

		// when
		mockMvc.perform(post("/register")
				.content(con)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is3xxRedirection())
			.andDo(print());

		List<Member> members = memberService.findMembers();
		Member member2 = members.get(0);

		// then
		assertThat(1L).isEqualTo(member2.getId());
	}
}
