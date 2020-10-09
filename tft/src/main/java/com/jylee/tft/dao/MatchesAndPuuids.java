package com.jylee.tft.dao;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class MatchesAndPuuids {


	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long MatchesAndPuuidsId;
	private String matchId;
	private String puuid;
	
}
