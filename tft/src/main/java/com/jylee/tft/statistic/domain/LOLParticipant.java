/**
  * @Package : com.jylee.tft.statistic.domain
  * @FileName : LOLParticipant.java
  * @Date : 2021. 1. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
	private String lane;
	private String role;
	private String win;
	private Long spell1;
	private Long spell2;
	private Long kills;
	private Long deaths;
	private Long assists;
	@ManyToOne
	private LOLMatch game;
	
}
