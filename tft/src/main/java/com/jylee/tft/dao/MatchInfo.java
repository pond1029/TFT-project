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
public class MatchInfo {

	@Id @GeneratedValue
	private Long matchInfoId;
	private String matchId;
	private Long gameDatetime;
	private Long gameLength;
	private String gameDate;
	private String gameVariation;
	private String gameVersion;	
}
