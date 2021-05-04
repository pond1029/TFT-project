/**
  * @Package : com.jylee.tft.statistic.service
  * @FileName : TFTService.java
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
import com.jylee.tft.modules.account.domain.PlayTime;
import com.jylee.tft.modules.account.domain.Summoner;
import com.jylee.tft.modules.account.domain.TFTAccount;
import com.jylee.tft.modules.account.domain.TFTMatch;
import com.jylee.tft.modules.account.domain.TFTParticipant;
import com.jylee.tft.modules.account.repository.SummonerRepository;
import com.jylee.tft.modules.account.repository.TFTMatchRepository;
import com.jylee.tft.modules.account.repository.TFTParticipantRepository;
import com.jylee.tft.modules.statistic.domain.Period;

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
	
	public List<PlayTime> getPlayTimes(TFTAccount account, LocalDateTime from, LocalDateTime to) {
		List<PlayTime> playTimes = new ArrayList<>();			
		Summoner summoner = summonerService.getSummoner(account);
						
		Optional<List<TFTParticipant>> participants = 
				tftParticipant.findByPuuidAndBetweenGameDatetime(summoner.getPuuid(), from, to);

		if(participants.isEmpty()) return playTimes;
		
		for(TFTParticipant participant : participants.get()) {		
			playTimes.add(new PlayTime(participant.getMatch().getGameDatetime().toLocalDate(), participant.getTimeEliminated()));
		}
		
		return playTimes;
	}

}
