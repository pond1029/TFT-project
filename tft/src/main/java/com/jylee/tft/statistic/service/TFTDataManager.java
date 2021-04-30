/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jylee.tft.statistic.domain.Account;
import com.jylee.tft.statistic.domain.RiotAccount;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.domain.TFTMatch;
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

	private final TFTDataManager tftDataManager;
	private final  SummonerImpl summonerRepository;	
	private final  TFTDataCollector dataCollector;
	private final  TFTMatchRepository matchRepository;
	private final  TFTParticipantRepository participantRepository;	

	@Override
	public void initAccount(Account account) throws JsonProcessingException {
		Summoner summoner = dataCollector.retrieveSummoner(account.getAccountName());
		summonerRepository.save(summoner);		
	}
	
	@Override
	public void update(Account account) throws JsonProcessingException {
		Set<TFTMatch> matchLists = new HashSet();
		
		Summoner summoner = summonerRepository.getSummoner((RiotAccount) account);
		
		if(summoner == null) {
			tftDataManager.initAccount(account);
			summoner = summonerRepository.getSummoner((RiotAccount) account);
		}
		
		List<TFTMatch> matches= dataCollector.retrieveMatches(summoner.getPuuid());
		
		//TODO TFT account의 가장 최근 데이터 조회
		//TFTMatch recentMatch = matchRepository.getRecent();
				
		for(TFTMatch match : matches) {
			//if(match.getMatchId().equals(recentMatch.getMatchId())) break;
			matchLists.add(dataCollector.retrieveMatchDetail(match, summoner.getPuuid()));			
		}
		
		matchRepository.saveAll(matchLists);				
	}
		

}
