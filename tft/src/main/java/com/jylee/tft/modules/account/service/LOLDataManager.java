/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jylee.tft.modules.account.domain.Account;
import com.jylee.tft.modules.account.domain.LOLMatch;
import com.jylee.tft.modules.account.domain.RiotAccount;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.repository.LOLMatchRepository;
import com.jylee.tft.modules.account.repository.LOLParticipantRepository;

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
	@Transactional
	public void update(Account account) throws JsonProcessingException{
		Set<LOLMatch> matchLists = new HashSet<LOLMatch>();
		
		Summoner summoner = summonerRepository.getSummoner((RiotAccount) account);
		
		if(summoner == null) {
			initAccount(account);
			summoner = summonerRepository.getSummoner((RiotAccount) account);
		}
		
		List<LOLMatch> matches = dataCollector.retrieveMatches(summoner.getAccountId());		

		Page<LOLMatch> recentMatch = matchRepository.findRecent(summoner.getAccountId(), PageRequest.of(0, 1));
		LOLMatch mostRecentMatch = recentMatch.getContent().get(0);
		
		for (LOLMatch match : matches) {
			if(mostRecentMatch != null && match.getGameId().equals(mostRecentMatch.getGameId())) break;
			matchLists.add(dataCollector.retrieveMatchDetail(match, summoner.getAccountId()));			
		}		
		
		matchRepository.saveAll(matchLists);
	}
	
}
