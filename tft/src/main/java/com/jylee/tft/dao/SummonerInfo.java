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
	private Long profileIconId;
	private Long revisionDate;
	private String name;
	private String puuid;
	private Long summonerLevel;
	
}
