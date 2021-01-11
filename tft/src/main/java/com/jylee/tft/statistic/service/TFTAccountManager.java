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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PlayTime;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.domain.TFTMatch;
import com.jylee.tft.statistic.domain.TFTParticipant;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Service
public class TFTAccountManager implements DataManager{

	@Autowired
	private SummonerRepository summoner;	
	@Autowired
	private TFTDataCollector tftCollector;
	@Autowired
	private TFTMatchRepository tftMatch;
	@Autowired
	private TFTParticipantRepository tftParticipant;
	private final AccountType type = AccountType.TFT;

	@Override
	public List<PlayTime> getPlayTimes(String summonerName, Period period) {
		List<PlayTime> playTimes = new ArrayList<>();			
		Summoner summoner = this.getSummoner(summonerName);
		
		Page<TFTMatch> recent = tftMatch.findRecent(summoner.getPuuid(), PageRequest.of(0,1));
		
		if(recent.isEmpty() || recent.get().findFirst().get().getGameDatetime().isBefore(period.getTo())) {
			tftCollector.update(summoner.getPuuid());			
		}
		
		Optional<List<TFTParticipant>> participants = 
				tftParticipant.findByPuuidAndBetweenGameDatetime(summoner.getPuuid(), period.getFrom(), period.getTo());

		if(participants.isEmpty()) return playTimes;
		
		for(TFTParticipant participant : participants.get()) {		
			playTimes.add(new PlayTime(participant.getMatch().getGameDatetime().toLocalDate(), participant.getTimeEliminated()));
		}
		
		return playTimes;
	}

	private Summoner getSummoner(String summonerName) {
		Optional<Summoner> user = summoner.findByNameAndType(summonerName,type);	
		Summoner summoner = user.orElseGet(()->tftCollector.getSummoner(summonerName));
		return summoner;
	}
		
}
