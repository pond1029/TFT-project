package com.jylee.tft.statistic.domain;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

	/**
	 * 키
	 */
	@Id @GeneratedValue
	private Long summonerId;
	
	/**
	 * 암호화 소환사 ID
	 */
	@NotNull
	private String id;
	
	/**
	 * 암호화 계정명
	 */
	@NotNull
	private String accountId;
	
	/**
	 * 프로필 아이콘 ID
	 */
	private Long profileIconId;
	
	/**
	 * 최종 변경일
	 */
	private LocalDateTime revisionDate;
	
	/**
	 * 소환사명
	 */
	@NotNull
	private String name;
	
	/**
	 * 암호화 PUUID
	 */
	@NotNull
	private String puuid;
	
	/**
	 * 소환사 레벨
	 */
	private Long summonerLevel;
	
	/**
	 * TFT/LOL 타입
	 */
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountType type;

}
