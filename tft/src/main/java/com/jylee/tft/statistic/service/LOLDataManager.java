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
import com.jylee.tft.statistic.domain.LOLMatch;
import com.jylee.tft.statistic.domain.RiotAccount;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.repository.LOLMatchRepository;
import com.jylee.tft.statistic.repository.LOLParticipantRepository;
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
public class LOLDataManager extends RiotDataManager{

	private final LOLDataManager dataManager;
	private final SummonerImpl summonerRepository;	
	private final LOLMatchRepository matchRepository;	
	private final LOLParticipantRepository participantRepository;	
	private final LOLDataCollector dataCollector;

	@Override
	public void initAccount(Account account) throws JsonProcessingException {
		Summoner summoner = dataCollector.retrieveSummoner(account.getAccountName());
		summonerRepository.save(summoner);		
	}
	
	@Override
	public void update(Account account) throws JsonProcessingException{
		Set<LOLMatch> matchLists = new HashSet<LOLMatch>();
		
		Summoner summoner = summonerRepository.getSummoner((RiotAccount) account);
		
		if(summoner == null) {
			dataManager.initAccount(account);
			summoner = summonerRepository.getSummoner((RiotAccount) account);
		}
		
		List<LOLMatch> matches = dataCollector.retrieveMatches(summoner.getAccountId());		

		//TODO LOL account의 가장 최근 데이터 조회
		//LOLMatch recentMatch = matchRepository.getRecent();
		
		for (LOLMatch match : matches) {
			//if(match.getGameId().equals(recentMatch.getGameId())) break;
			matchLists.add(dataCollector.retrieveMatchDetail(match, summoner.getAccountId()));			
		}		
		
		matchRepository.saveAll(matchLists);
	}
	
}
