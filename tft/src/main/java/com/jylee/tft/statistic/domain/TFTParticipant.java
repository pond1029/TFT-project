package com.jylee.tft.statistic.domain;


import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TFTParticipant {

	@Id @GeneratedValue
	private Long tftParticipantId;
	private Long goldLeft;
	private Long lastRound;
	private Long level;
	private Long placement;
	private Long playersEliminated;
	private String puuid;
	private LocalTime timeEliminated;
	private Long totalDamageToPlayers;	
	@ManyToOne
	private TFTMatch match;
	
}
