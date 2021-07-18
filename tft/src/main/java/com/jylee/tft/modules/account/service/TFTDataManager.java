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
import com.jylee.tft.modules.account.domain.RiotAccount;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.domain.TFTMatch;
import com.jylee.tft.modules.account.repository.TFTMatchRepository;
import com.jylee.tft.modules.account.repository.TFTParticipantRepository;

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

	private final  SummonerImpl summonerRepository;	
	private final  TFTDataCollector dataCollector;
	private final  TFTMatchRepository matchRepository;
	private final  TFTParticipantRepository participantRepository;	

	@Override
	public Object initAccount(Account account) throws JsonProcessingException {
		Summoner summoner = dataCollector.getSummoner(account.getAccountName());
		return summonerRepository.save(summoner);		
	}
	
	@Override
	@Transactional
	public void update(Account account) throws JsonProcessingException {
		Set<TFTMatch> matchLists = new HashSet();
		
		Summoner summoner = summonerRepository.getSummoner((RiotAccount) account);
		
		if(summoner == null) {
			summoner = (Summoner) initAccount(account);
		}
		
		List<TFTMatch> matches= dataCollector.getMatches(summoner.getPuuid());
		
		Page<TFTMatch> recentMatch = matchRepository.findRecent(summoner.getPuuid(), PageRequest.of(0, 1));
		TFTMatch mostRecentMatch = null;
		
		if(recentMatch.getTotalPages() > 0 ) {
			mostRecentMatch = recentMatch.getContent().get(0);		
		}
		
		for(TFTMatch match : matches) {
			if(mostRecentMatch != null && match.getMatchId().equals(mostRecentMatch.getMatchId())) break;
			matchLists.add(dataCollector.getMatchDetail(match, summoner.getPuuid()));			
		}
		
		matchRepository.saveAll(matchLists);				
	}
		

}
