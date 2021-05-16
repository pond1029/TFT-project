/**
  * @Package : com.jylee.tft.modules.account
  * @FileName : AccountController.java
  * @Date : 2021. 5. 16. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.jylee.tft.modules.account.domain.Account;
import com.jylee.tft.modules.account.service.AccountFactory;
import com.jylee.tft.modules.account.service.AccountVisitorImpl;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.modules.account
  * @FileName : AccountController.java
  * @Date : 2021. 5. 16. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Controller
@RequiredArgsConstructor
public class AccountController {

	private final AccountVisitorImpl visitor;	
	
	@PostMapping("/account/update")
	public String updateAccount() {
//
//		Account account = (AccountFactory.getAccount(accountName, accountType));
//		account.update(visitor);
		
		return "redirect:/";
	}
}
