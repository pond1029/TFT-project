/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchInfo.java
  * @Date : 2021. 1. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LOLMatch {

	@Id @GeneratedValue
	private Long lolMatchId;
	private Long gameId;
	private String role;
	private Long season;
	private String platformId;
	private Long champion;
	private Long queue;
	private String lane;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
}
