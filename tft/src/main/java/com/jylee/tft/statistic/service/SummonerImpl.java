/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : SummonerImpl.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.RiotAccount;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.repository.SummonerRepository;

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
	
	public Optional<Summoner> findByNameAndType(String name,AccountType type) {
		return summonerRepository.findByNameAndType(name, type);
	}
	
	public Summoner getSummoner(RiotAccount account) {
		Optional<Summoner> user = this.findByNameAndType(account.getAccountId(), account.getAccountType());	
		Summoner summoner = user.get();
		return summoner;
	}
	
}
