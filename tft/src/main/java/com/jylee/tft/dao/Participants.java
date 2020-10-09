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
public class Participants {

	@Id @GeneratedValue
	private Long participantsId;
	private Long goldLeft;
	private Long lastRound;
	private Long level;
	private Long placement;
	private Long playersEliminated;
	private Long timeEliminated;
	private Long totalDamageToPlayers;
	private String playersGameTime;
	private String puuid;
	private String matchId;
	
}
