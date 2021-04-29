/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTService.java
  * @Date : 2021. 4. 29. 
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
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.domain.TFTAccount;
import com.jylee.tft.statistic.domain.TFTMatch;
import com.jylee.tft.statistic.domain.TFTParticipant;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTService.java
  * @Date : 2021. 4. 29. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Service
@RequiredArgsConstructor
public class TFTService {

	private final  SummonerImpl summonerService;	
	private final  TFTParticipantRepository tftParticipant;
	
	public List<PlayTime> getPlayTimes(TFTAccount account, Period period) {
		List<PlayTime> playTimes = new ArrayList<>();			
		Summoner summoner = summonerService.getSummoner(account);
						
		Optional<List<TFTParticipant>> participants = 
				tftParticipant.findByPuuidAndBetweenGameDatetime(summoner.getPuuid(), period.getFrom(), period.getTo());

		if(participants.isEmpty()) return playTimes;
		
		for(TFTParticipant participant : participants.get()) {		
			playTimes.add(new PlayTime(participant.getMatch().getGameDatetime().toLocalDate(), participant.getTimeEliminated()));
		}
		
		return playTimes;
	}

}
