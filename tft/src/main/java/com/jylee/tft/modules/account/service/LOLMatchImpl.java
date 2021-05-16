/**
  * @Package : com.jylee.tft.modules.account.service
  * @FileName : LOLMatchImpl.java
  * @Date : 2021. 5. 16. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import org.springframework.stereotype.Service;

import com.jylee.tft.modules.account.domain.LOLMatch;
import com.jylee.tft.modules.account.repository.LOLMatchRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.modules.account.service
  * @FileName : LOLMatchImpl.java
  * @Date : 2021. 5. 16. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class LOLMatchImpl {

	private final LOLMatchRepository lolMatch;
	
	public LOLMatch getRecent(String accountId) {
		
		
		return null;
	}
}
