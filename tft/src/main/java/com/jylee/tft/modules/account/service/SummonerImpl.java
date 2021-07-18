/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : SummonerImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jylee.tft.modules.account.domain.AccountType;
import com.jylee.tft.modules.account.domain.RiotAccount;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.repository.SummonerRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : SummonerImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class SummonerImpl{

	private final SummonerRepository summonerRepository;
	
	public Summoner save(Summoner summoner) {
		return summonerRepository.save(summoner);
	}
	
	public Summoner findByNameAndType(String name,AccountType type) {
		return summonerRepository.findByNameAndType(name, type);
	}
	
	public Summoner getSummoner(RiotAccount account) {
		Summoner summoner = summonerRepository.findByNameAndType(account.getAccountName(), account.getAccountType());	
		return summoner;
	}
	
}
