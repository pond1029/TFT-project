/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class LOLMatchDetail {

	@Id @GeneratedValue
	private Long lolMatchDetailId;
	private Long queueId;
	private String gameType;
	@Temporal(TemporalType.TIME)
	private Date gameDuration;
	private String platformId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date gameCreation;
	private Long seasonId;
	private String gameVersion;
	private Long mapId;
	private String gameMode;
	private Long gameId;
	@OneToMany(mappedBy = "game")
	private Set<LOLParticipant> participants = new HashSet<>();
	
	public void addParticipants(LOLParticipant lolParticipant) {
		this.getParticipants().add(lolParticipant);	
		lolParticipant.setGame(this);	
	}
	
	public void removeParticipants(LOLParticipant lolParticipant) {
		this.getParticipants().remove(lolParticipant);
		lolParticipant.setGame(null);
	}
}
