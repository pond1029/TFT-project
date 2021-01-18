/**
  * @Package : com.jylee.tft.user.service
  * @FileName : SendMail.java
  * @Date : 2021. 1. 13. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.user.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jylee.tft.user.domain.JoinForm;
import com.jylee.tft.user.domain.SiteUser;
import com.jylee.tft.user.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.user.service
  * @FileName : SendMail.java
  * @Date : 2021. 1. 13. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class SiteUserService {

	private final SiteUserRepository siteUserRepository;
	private final ConsoleMailSender mailSender;
	private final PasswordEncoder passwordEncoder;

	/**
	  * @Method Name : processJoin
	  * @Date : 2021. 1. 13.
	  * @Author : "LeeJaeYeon"
	  * @Version : 
	  * @Information :
	  * @param joinForm
	  */	
	@Transactional
	public SiteUser processJoin(JoinForm joinForm) {
		SiteUser joinUser = saveJoinUser(joinForm);
		joinUser.generateToken();	
		sendCheckEmail(joinUser);		
		return joinUser;
	}
	
	private SiteUser saveJoinUser(JoinForm joinForm) {
		SiteUser joinUser = SiteUser.builder()
				.email(joinForm.getEmail())
				.nickname(joinForm.getNickname())
				.password(passwordEncoder.encode(joinForm.getPassword()))
				.build();
		return siteUserRepository.save(joinUser);
	}
	
	private void sendCheckEmail(SiteUser joinUser) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(joinUser.getEmail());
		mailMessage.setSubject("TFT, confirm email and join us");
		mailMessage.setText("/check-email-token?token=" + joinUser.getEmailToken() + 
				"&email=" + joinUser.getEmail());
		mailSender.send(mailMessage);
	}

	/**
	  * @Method Name : login
	  * @Date : 2021. 1. 13.
	  * @Author : "LeeJaeYeon"
	  * @Version : 
	  * @Information :
	  * @param joinUser
	  */
	
	public void login(SiteUser user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				user.getNickname(), 
				user.getPassword(),
				List.of(new SimpleGrantedAuthority("ROLE_USER")));
		SecurityContextHolder.getContext().setAuthentication(token);
		
	}

}
