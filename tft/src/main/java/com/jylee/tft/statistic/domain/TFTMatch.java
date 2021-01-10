package com.jylee.tft.statistic.domain;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TFTMatch {

	@Id @GeneratedValue
	private Long tftMatchId;
	private String dataVersion;
	private String matchId;
	private LocalDateTime gameDatetime;	
	private LocalTime gameLength;
	private String gameVariation;
	private String gameVersion;	
	private Long queueId;
	private Long tftSetNumber;
	@OneToMany(mappedBy = "match")
	private Set<TFTParticipant> participants = new HashSet<>();
	
	public void addParticipants(TFTParticipant tftParticipant) {
		this.getParticipants().add(tftParticipant);
		tftParticipant.setMatch(this);
	}
	
	public void removeParticipants(TFTParticipant tftParticipant) {
		this.getParticipants().remove(tftParticipant);
		tftParticipant.setMatch(null);
	}
}
