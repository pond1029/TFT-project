/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import java.util.List;

import javax.persistence.Entity;

import com.jylee.tft.dao.MatchDatas.MatchDatasBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Getter @Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LOLMatchDetail {

	private Long gameId;
	private List participantIdentities;
	private Long queueId;
	private String gameType;
	private Long gameDuration;
	private List teams;
	private String platformId;
	private Long gameCreation;
	private Long seasonId;
	private String gameVersion;
	private Long mapId;
	private String gameMode;
}
