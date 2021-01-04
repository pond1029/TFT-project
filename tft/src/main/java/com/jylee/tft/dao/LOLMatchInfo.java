/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchInfo.java
  * @Date : 2021. 1. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.jylee.tft.dao.MatchDatas.MatchDatasBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchInfo.java
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
public class LOLMatchInfo {

	@Id
	private Long gameId;
	private String role;
	private Long season;
	private String platformId;
	private Long champion;
	private Long queue;
	private String lane;
	private Long timestamp;
	
}
