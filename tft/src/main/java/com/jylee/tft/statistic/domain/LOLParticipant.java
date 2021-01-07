/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLParticipant.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLParticipant.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LOLParticipant {

	@Id @GeneratedValue
	private Long lolParticipantId;
	private String accountId;
	private String currentAccountId;
	private String currentPlatformId;
	private String platformId;
	private Long profileIcon;
	private String summonerId;
	private String summonerName;
	private String win;
	@ManyToOne
	private LOLMatchDetail game;
	
}
