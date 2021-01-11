/**
  * @Package : com.jylee.tft.dao
  * @FileName : LOLMatchDetail.java
  * @Date : 2021. 1. 5. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class LOLMatch {

	@Id @GeneratedValue
	private Long lolMatchId;
	private Long queue;
	private String gameType;
	private LocalTime gameDuration;
	private String platformId;
	private LocalDateTime gameCreation;
	private Long season;
	private String gameVersion;
	private Long mapId;
	private String gameMode;
	@Column(unique = true)
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
