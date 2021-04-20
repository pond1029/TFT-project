package com.jylee.tft.statistic.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.sun.istack.NotNull;

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
public class Summoner {

	@Id @GeneratedValue
	private Long summonerId;
	
	@NotNull
	private String id;
	
	@NotNull
	private String accountId;
	
	private Long profileIconId;

	private LocalDateTime revisionDate;
	
	@NotNull
	private String name;
	
	@NotNull
	private String puuid;
	
	private Long summonerLevel;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountType type;

}
