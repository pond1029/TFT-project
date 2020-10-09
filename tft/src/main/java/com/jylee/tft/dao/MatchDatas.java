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
public class MatchDatas {

	@Id @GeneratedValue
	private Long MatchDataId;
	private String dataVersion;
	private String matchId;
	private String participant;
	
}
