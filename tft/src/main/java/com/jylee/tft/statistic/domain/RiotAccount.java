/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLAccount.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.jylee.tft.statistic.repository.SummonerRepository;

import lombok.AllArgsConstructor;
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
@RequiredArgsConstructor
public class RiotAccount implements Account{

	@Autowired
	private SummonerRepository summoner;	
	@NonNull
	private RiotType type;
	@NonNull
	private String id;
	
	public void setType(String type) {
		this.type = RiotType.valueOf(type);
	}
	
	@Override
	public String getType() {
		return this.type.name();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String getApiId() {
		Optional<Summoner> user = summoner.findByNameAndType(id,type);	
		Summoner userInfo = user.orElseGet(()->retrieveSummoner(id));
		return userInfo.getPuuid();
	}

	private Summoner retrieveSummoner(String userId) {
		//TODO API 조회
		return null;
	}
	
}
