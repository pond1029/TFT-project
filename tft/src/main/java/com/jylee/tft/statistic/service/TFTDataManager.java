/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;
import com.jylee.tft.statistic.domain.RiotAccount;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.domain.TFTMatch;
import com.jylee.tft.statistic.domain.TFTParticipant;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Service
@RequiredArgsConstructor
public class TFTDataManager extends RiotDataManager{

	private final RiotDataManager riotDataManager;
	private final  SummonerImpl summonerRepository;	
	private final  TFTDataCollector tftCollector;
	private final  TFTMatchRepository tftMatch;
	private final  TFTParticipantRepository tftParticipant;

	@Override
	public void update(Account account) {			
		Summoner summoner = summonerRepository.getSummoner((RiotAccount) account);
		
		if(summoner == null) {
			riotDataManager.initAccount(account);
			summoner = summonerRepository.getSummoner((RiotAccount) account);
		}
		
		//TODO TFT 업데이트 구현
	}
		
}
