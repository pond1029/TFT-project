/**
  * @Package : com.jylee.tft.user
  * @FileName : JoinFormRepository.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.user.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jylee.tft.user.domain.JoinForm;
import com.jylee.tft.user.repository.SiteUserRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.user
  * @FileName : JoinFormRepository.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Component
@RequiredArgsConstructor
public class JoinFormValidator implements Validator{

	private final SiteUserRepository siteUserRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(JoinForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		JoinForm joinForm = (JoinForm) target;
		if(siteUserRepository.existsByEmail(joinForm.getEmail())) {
			errors.rejectValue("email", "invalid.emial", new Object[] {joinForm.getEmail()},"email is alreay exists");
		}

		if(siteUserRepository.existsByNickname(joinForm.getNickname())) {
			errors.rejectValue("nickname", "invalid.nickname", new Object[] {joinForm.getNickname()},"nickname is alreay exists");
		}
		
		if(joinForm.getNickname() == null || joinForm.getNickname().length() <= 0 || joinForm.getNickname().length() >= 20) {
			errors.rejectValue("nickname", "invalid.nickname", new Object[]{joinForm.getNickname()},"Nickname must be between 1 and 20 characters.");	
		}		
		
		String regex = "^(.+)@(.+)$"; 
		Pattern p = Pattern.compile(regex); 
		Matcher m = p.matcher(joinForm.getEmail()); 
		if(!m.matches()){
			errors.rejectValue("email", "invalid.email", new Object[]{joinForm.getEmail()},"Wrong email.");
		}

	}


}
