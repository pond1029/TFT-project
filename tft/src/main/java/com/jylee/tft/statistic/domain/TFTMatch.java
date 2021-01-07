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

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TFTMatch {

	@Id @GeneratedValue
	private Long tftMatchId;
	private String dataVersion;
	private String matchId;
	@Temporal(TemporalType.TIMESTAMP)
	private Date gameDatetime;	
	private Long gameLength;
	private String gameVariation;
	private String gameVersion;	
	private Long queueId;
	private Long tftSetNumber;
	@OneToMany(mappedBy = "match")
	private Set<TFTMatchDetail> matchDetails = new HashSet<>();
	
	public void addMatchDetails(TFTMatchDetail matchDetail) {
		this.getMatchDetails().add(matchDetail);
		matchDetail.setMatch(this);
	}
	
	public void removeMatchDetails(TFTMatchDetail matchDetail) {
		this.getMatchDetails().remove(matchDetail);
		matchDetail.setMatch(null);
	}
}
