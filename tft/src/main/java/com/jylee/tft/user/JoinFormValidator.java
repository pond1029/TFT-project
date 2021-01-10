/**
  * @Package : com.jylee.tft.user
  * @FileName : JoinFormRepository.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.user;

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
		String nickname = joinForm.getNickname();
		errors.rejectValue("nickname", "invalid.nickname", new Object[]{nickname},"Nickname must be between 1 and 20 characters.");	
		if(nickname == null || nickname.length() <= 0 || nickname.length() >= 20) {
			errors.rejectValue("nickname", "invalid.nickname", new Object[]{nickname},"Nickname must be between 1 and 20 characters.");	
		}
		
		
		
	}


//	public JoinForm(String nickname, String email, String password) throws BindException {
//		if(nickname == null || nickname.length() <= 0 || nickname.length() >= 20) {
//			throw new BindException();			
//		}
//		if(email == null) {
//			throw new BindException();			
//		}
//		if(password == null || password.length() <= 0 || password.length() >= 20) {
//			throw new BindException();			
//		}
//		this.nickname = nickname;
//		this.email = email;
//		this.password = password;
//	}
}
