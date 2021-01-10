/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jylee.tft.statistic.repository.LOLMatchRepository;
import com.jylee.tft.statistic.repository.LOLParticipantRepository;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.service.LOLDataCollector;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Getter @Setter
@EqualsAndHashCode
@RequiredArgsConstructor
public class LOLAccount implements Account{

	@Autowired
	private SummonerRepository summoner;	
	@Autowired
	private LOLDataCollector lolCollector;
	@Autowired
	private LOLMatchRepository lolMatch;
	@Autowired
	private LOLParticipantRepository lolParticipant;
	@NonNull
	private String id;
	private AccountType type = AccountType.LOL;
	 
	@Override
	public List<PlayTime> getPlayTimes(Period period) {
		List<PlayTime> playTimes = new ArrayList<>();		
		Summoner summoner = this.getSummoner();
		
		if(summoner.getUpdateDate().isBefore(LocalDateTime.now())) {
			lolCollector.update(summoner.getAccountId());
		}
		
		Optional<List<LOLMatch>> matches = lolMatch.findByAccountIdAndBetweenGameCreation(summoner.getAccountId(), period.getFrom(), period.getTo());
		
		//TODO n+1 select가 일어나는지 확인
		for(LOLMatch detail : matches.get()) {		
			playTimes.add(new PlayTime(detail.getGameCreation().toLocalDate(), detail.getGameDuration()));
		}
		
		return playTimes;
	}

	private List<LOLMatch> retrieveMatches(String apiId){
		return lolCollector.getMatches(apiId);	
	}
	
	private Summoner getSummoner() {
		Optional<Summoner> user = summoner.findByNameAndType(id,type);	
		Summoner summoner = user.orElseGet(()->retrieveSummoner(id));
		return summoner;
	}

	private Summoner retrieveSummoner(String userId) {		
		return lolCollector.getSummoner(userId);
	}
		
}
