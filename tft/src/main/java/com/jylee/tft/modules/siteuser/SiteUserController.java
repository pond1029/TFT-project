/**
  * @Package : com.jylee.tft.user
  * @FileName : UserController.java
  * @Date : 2021. 1. 10. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.siteuser;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jylee.tft.modules.siteuser.domain.JoinForm;
import com.jylee.tft.modules.siteuser.domain.SiteUser;
import com.jylee.tft.modules.siteuser.repository.SiteUserRepository;
import com.jylee.tft.modules.siteuser.service.JoinFormValidator;
import com.jylee.tft.modules.siteuser.service.SiteUserService;

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

	private final SiteUserService userService;	
	private final JoinFormValidator joinFormValidator;
	private final SiteUserRepository userRepository;
	
	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("joinForm",new JoinForm());
		return "user/join";
	}
	
	@PostMapping("/join")
	public String joinSubmit(@ModelAttribute JoinForm joinForm, Errors errors) {
		joinFormValidator.validate(joinForm, errors);
		if(errors.hasErrors()) {
			return "user/join";
		}		
		SiteUser joinUser = userService.processJoin(joinForm);
		userService.login(joinUser);
		return "redirect:/";
	}
	
	@GetMapping("/check-email-token")
	public String checkEmailToken(String token, String email, Model model) {
		Optional<SiteUser> user = userRepository.findByEmail(email);
		String view = "user/checked-email";
		if(user.isEmpty()) {
			model.addAttribute("error","wrong.email");
			return view;
		}
		SiteUser checkedUser = user.get();
		if(!checkedUser.isValidToken(token)) {
			model.addAttribute("error","wrong.token");
			return view;
		}
		checkedUser.confirmUser();
		userService.login(checkedUser);
		model.addAttribute("numberOfUser",userRepository.count());
		model.addAttribute("nickname",checkedUser.getNickname());
		return view;
		
	}
	
}
