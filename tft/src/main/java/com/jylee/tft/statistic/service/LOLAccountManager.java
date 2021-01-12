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

import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.LOLMatch;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.repository.LOLMatchRepository;
import com.jylee.tft.statistic.repository.SummonerRepository;

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
public class LOLAccountManager implements DataManager{

	private final SummonerRepository summoner;	
	private final LOLDataCollector lolCollector;
	private final LOLMatchRepository lolMatch;
	private AccountType type = AccountType.LOL;
	 
	@Override
	public List<PlayTime> getPlayTimes(String summonerName, Period period) {
		List<PlayTime> playTimes = new ArrayList<>();		
		Summoner summoner = this.getSummoner(summonerName);

		Page<LOLMatch> recent = lolMatch.findRecent(summoner.getAccountId(), PageRequest.of(0,1));
		
		if(recent.isEmpty() || recent.get().findFirst().get().getGameCreation().isBefore(period.getTo())) {		
			lolCollector.update(summoner.getAccountId());
		}
		
		Optional<List<LOLMatch>> matches = 
				lolMatch.findByAccountIdAndBetweenGameCreation(summoner.getAccountId(), period.getFrom(), period.getTo());

		if(matches.isEmpty()) return playTimes;
		
		//TODO n+1 select가 일어나는지 확인
		for(LOLMatch detail : matches.get()) {		
			playTimes.add(new PlayTime(detail.getGameCreation().toLocalDate(), detail.getGameDuration()));
		}
		
		return playTimes;
	}
	
	private Summoner getSummoner(String summonerName) {
		Optional<Summoner> user = summoner.findByNameAndType(summonerName,type);	
		Summoner summoner = user.orElseGet(()->lolCollector.getSummoner(summonerName));
		return summoner;
	}

		
}
