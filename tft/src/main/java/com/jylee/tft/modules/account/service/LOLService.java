/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : LOLService.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.account.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jylee.tft.modules.account.domain.AccountType;
import com.jylee.tft.modules.account.domain.LOLAccount;
import com.jylee.tft.modules.account.domain.LOLMatch;
import com.jylee.tft.modules.account.domain.PlayTime;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.repository.LOLMatchRepository;
import com.jylee.tft.modules.account.repository.SummonerRepository;
import com.jylee.tft.modules.statistic.domain.Period;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : LOLService.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */


@Service
@RequiredArgsConstructor
public class LOLService {

	private final  SummonerImpl summonerService;	
	private final LOLMatchRepository lolMatch;
	 
	public List<PlayTime> getPlayTimes(LOLAccount account, LocalDateTime from, LocalDateTime to) {
		List<PlayTime> playTimes = new ArrayList<>();		
		Summoner summoner = summonerService.getSummoner(account);
		
		Optional<List<LOLMatch>> matches = 
				lolMatch.findByAccountIdAndBetweenGameCreation(summoner.getAccountId(), from, to);

		if(matches.isEmpty()) return playTimes;
		
		for(LOLMatch detail : matches.get()) {		
			playTimes.add(new PlayTime(detail.getGameCreation().toLocalDate(), detail.getGameDuration()));
		}
		
		return playTimes;
	}
	
}
