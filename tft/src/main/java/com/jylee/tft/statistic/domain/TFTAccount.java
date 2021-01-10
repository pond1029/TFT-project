/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;
import com.jylee.tft.statistic.service.TFTDataCollector;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@RequiredArgsConstructor
public class TFTAccount implements Account{

	@Autowired
	private SummonerRepository summoner;	
	@Autowired
	private TFTDataCollector tftCollector;
	@Autowired
	private TFTMatchRepository tftMatch;
	@Autowired
	private TFTParticipantRepository tftParticipant;
	@NonNull
	private AccountType type;
	@NonNull
	private String id;
	
	public void setType(String type) {
		this.type = AccountType.valueOf(type);
	}
	
	private String getApiId() {
		Optional<Summoner> user = summoner.findByNameAndType(id,type);	
		Summoner userInfo = user.orElseGet(()->retrieveSummoner(id));
		return userInfo.getPuuid();
	}

	private Summoner retrieveSummoner(String userId) {
		
		return null;
	}
	
	@Override
	public List<PlayTime> getPlayTimes(Period period) {
		List<PlayTime> playTimes = new ArrayList<>();
		
		Optional<List<TFTParticipant>> participants = tftParticipant.findByPuuidAndBetweenGameDatetime(this.getApiId(), period.getFrom(), period.getTo());
		//TODO n+1 select가 일어나는지 확인
		for(TFTParticipant participant : participants.get()) {		
			playTimes.add(new PlayTime(participant.getMatch().getGameDatetime().toLocalDate(), participant.getTimeEliminated()));
		}
		
		return playTimes;
	}
	
	
}
