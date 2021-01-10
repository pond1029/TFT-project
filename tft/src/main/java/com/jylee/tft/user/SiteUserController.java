/**
  * @Package : com.jylee.tft.user
  * @FileName : UserController.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jylee.tft.user.domain.JoinForm;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.user
  * @FileName : UserController.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 사용자 컨트롤러
  */

@Controller
@RequiredArgsConstructor
public class SiteUserController {

	private final JoinFormValidator joinFormValidator;
	
	@InitBinder("joinForm")
	public void InitBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(joinFormValidator);
	}

	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("joinForm",new JoinForm());
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinSubmit(@ModelAttribute JoinForm joinForm, Errors errors) {
		System.out.println("post");
		if(errors.hasErrors()) {
			return "user/join";
		}
		return "redirct:/";
	}
	
}
