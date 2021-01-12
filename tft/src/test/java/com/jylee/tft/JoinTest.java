/**
  * @Package : com.jylee.tft
  * @FileName : joinTest.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.jylee.tft.user.domain.SiteUser;
import com.jylee.tft.user.repository.SiteUserRepository;
/**
  * @Package : com.jylee.tft
  * @FileName : joinTest.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class JoinTest {

	@Autowired
	SiteUserRepository userRepository;
	
	@Autowired
	private MockMvc mock;
	
	@Test
	@DisplayName("회원가입 에러 확인")
	public void invalidJoin() throws Exception {
		mock.perform(post("/join")
				.param("nickname", "ab")
				.param("email", "ab")
				.param("password","a")
				.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("user/join"))
		.andExpect(unauthenticated());
	}
	
	@Test
	@DisplayName("회원가입 정상 확인")
	public void correctJoin() throws Exception {
		mock.perform(post("/join")
				.param("nickname", "join")
				.param("email", "join@email.com")
				.param("password","abc")
				.with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"))
		.andExpect(authenticated().withUsername("join"));
		
		assertTrue(userRepository.existsByEmail("join@email.com"));
	}
	
	@Test
	@DisplayName("인증 입력 오류")
	public void checkEmailError() throws Exception {
		mock.perform(get("/check-email-token")
				.param("token", "aaaaaaa")
				.param("email", "abce@email.com")
				.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("error"))
		.andExpect(view().name("user/checked-email"))
		.andExpect(unauthenticated());
		
	}

	@Test
	@DisplayName("인증 정상")
	public void checkEmailCorrect() throws Exception {
		
		SiteUser user = SiteUser.builder()
				.email("abc@email.com")
				.password("123")
				.nickname("nick")
				.build();
		SiteUser joinUser = userRepository.save(user);
		joinUser.generateToken();
		
		mock.perform(get("/check-email-token")
				.param("token", joinUser.getEmailToken())
				.param("email", "abc@email.com")
				.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(model().attributeDoesNotExist("error"))
		.andExpect(model().attributeExists("nickname"))
		.andExpect(view().name("user/checked-email"))
		.andExpect(authenticated().withUsername("nick"));
		
	}
}
