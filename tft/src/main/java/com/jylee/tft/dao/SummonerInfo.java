package com.jylee.tft.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SummonerInfo {

	@Id @GeneratedValue
	private Long summonerInfoId;
	private String id;
	private String accountId;
	private String puuid;
	private String name;
	private Long profileIconId;
	private Long revisionDate;
	private Long summonerLevel;
	
}
