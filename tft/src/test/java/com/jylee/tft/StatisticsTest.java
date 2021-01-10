/**
  * @Package : com.jylee.tft.service
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jylee.tft.statistic.domain.LOLMatch;
import com.jylee.tft.statistic.domain.LOLParticipant;
import com.jylee.tft.statistic.domain.Period;
import com.jylee.tft.statistic.domain.PeriodFigure;
import com.jylee.tft.statistic.domain.AccountType;
import com.jylee.tft.statistic.domain.Summoner;
import com.jylee.tft.statistic.domain.SummonerPuuid;
import com.jylee.tft.statistic.domain.TFTMatch;
import com.jylee.tft.statistic.domain.TFTParticipant;
import com.jylee.tft.statistic.repository.LOLMatchRepository;
import com.jylee.tft.statistic.repository.LOLParticipantRepository;
import com.jylee.tft.statistic.repository.SummonerRepository;
import com.jylee.tft.statistic.repository.TFTParticipantRepository;
import com.jylee.tft.statistic.repository.TFTMatchRepository;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.service
  * @FileName : StatisticsTest.java
  * @Date : 2021. 1. 4. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Slf4j
@DataJpaTest
public class StatisticsTest {

	@Autowired
	SummonerRepository summoner;
	
	@Autowired
	TFTMatchRepository tftMatch;
	
	@Autowired
	TFTParticipantRepository tftParticipant;
	
	@Autowired
	LOLMatchRepository lolMatch;
	
	@Autowired
	LOLParticipantRepository lolParticipant;
	
	@Test
	@DisplayName("LOL 디테일 날짜에 따른 조회")
	public void lolMatchDetailTest() {
		
		LOLParticipant participant1 = new LOLParticipant();
		participant1.setAccountId("가렌");

		LOLParticipant participant2 = new LOLParticipant();
		participant1.setAccountId("징크스");
		
		LOLParticipant participant3 = new LOLParticipant();
		participant1.setAccountId("바드");

		LOLParticipant participant4 = new LOLParticipant();
		participant1.setAccountId("가렌");
		
		LOLMatch match1 = new LOLMatch();
		match1.setGameCreation(LocalDateTime.of(2021, 1, 1, 0, 0));
		match1.addParticipants(participant1);
		match1.addParticipants(participant2);

		LOLMatch match2 = new LOLMatch();
		match2.setGameCreation(LocalDateTime.of(2021,1,10,0,0));
		match2.addParticipants(participant3);
		

		LOLMatch match3 = new LOLMatch();
		match3.setGameCreation(LocalDateTime.of(2021,1,20,0,0));
		match3.addParticipants(participant4);
		
		lolParticipant.save(participant1);
		lolParticipant.save(participant2);
		lolParticipant.save(participant3);
		lolParticipant.save(participant4);
		lolMatch.save(match1);
		lolMatch.save(match2);
		lolMatch.save(match3);
		
		Optional<List<LOLMatch>> participantList = lolMatch.findByAccountIdAndBetweenGameCreation("가렌", LocalDateTime.of(2020,12,1,0,0), LocalDateTime.of(2021,1,5,0,0)); 
		
		assertThat(participantList.get().size()).isEqualTo(1);
	}
	
	@Test
	@DisplayName("TFT 매치 디테일 날짜에 따른 조회")
	public void tftParticipantTest() {

		TFTParticipant participant1 = TFTParticipant.builder()
									.puuid("ASD123")
									.timeEliminated(LocalTime.ofSecondOfDay(2358))
									.build();

		TFTParticipant participant2 = TFTParticipant.builder()
									.puuid("ASD123")
									.timeEliminated(LocalTime.ofSecondOfDay(2253))
									.build();

		TFTParticipant participant3 = TFTParticipant.builder()
									.puuid("ASD123")
									.timeEliminated(LocalTime.ofSecondOfDay(2213))
									.build();
		
		TFTMatch match1 = new TFTMatch();
		match1.setMatchId("AAA");
		match1.addParticipants(participant1);
		match1.setGameDatetime(LocalDateTime.of(2021,1,1,0,0));

		TFTMatch match2 = new TFTMatch();
		match2.setMatchId("BBB");
		match2.addParticipants(participant2);
		match2.setGameDatetime(LocalDateTime.of(2021,1,2,0,0));

		TFTMatch match3 = new TFTMatch();
		match3.setMatchId("CCC");
		match3.addParticipants(participant3);
		match3.setGameDatetime(LocalDateTime.of(2021,1,3,0,0));

		TFTMatch savedMatch1 = tftMatch.save(match1);
		TFTMatch savedMatch2 = tftMatch.save(match2);
		TFTMatch savedMatch3 = tftMatch.save(match3);
		TFTParticipant savedParticipant1 = tftParticipant.save(participant1);
		TFTParticipant savedParticipant2 = tftParticipant.save(participant2);
		TFTParticipant savedParticipant3 = tftParticipant.save(participant3);

		Optional<List<TFTParticipant>> participantList = tftParticipant.findByPuuidAndBetweenGameDatetime("ASD123", LocalDateTime.of(2020,12,1,0,0), LocalDateTime.of(2021,1,2,0,0));
		
		//Page<TFTParticipant> participantList = tftParticipant.findByPuuid("ASD123", page);
		
		assertThat(participantList.get().size()).isEqualTo(2);
		
	}
		
	@Test
	@DisplayName("EAGER 조회")
	public void tftMatchTest() {
		
		TFTParticipant participants1 = TFTParticipant.builder()
									.puuid("ASD123")
									.timeEliminated(LocalTime.ofSecondOfDay(2358))
									.build();

		TFTParticipant participants2 = TFTParticipant.builder()
									.puuid("AWRD23")
									.timeEliminated(LocalTime.ofSecondOfDay(2253))
									.build();

		TFTMatch match = new TFTMatch();
		match.setMatchId("AAA");
		match.addParticipants(participants1);
		match.addParticipants(participants2);
		
		TFTMatch savedMatch = tftMatch.save(match);
		TFTParticipant savedparticipant1 = tftParticipant.save(participants1);
		TFTParticipant savedparticipant2 = tftParticipant.save(participants2);
		
		PageRequest page = PageRequest.of(0, 10);
		Page<TFTMatch> allMatches = tftMatch.findByMatchId("AAA", page);
		
		assertThat(allMatches.getTotalElements()).isEqualTo(1);	
	}
	
	@Test
	@DisplayName("소환사 정보 조회")
	public void summonerTest() {
		Summoner user = Summoner.builder()
					.id("pond1029")
					.name("pond")
					.puuid("NJKDDNWJK214DJ")
					.type(AccountType.TFT)
					.accountId("DHWUNDJS")
					.build();
					
		Summoner savedUser = summoner.save(user);
		
		Optional<SummonerPuuid> puuid = summoner.findPuuidByName("pond");
		
		assertThat(puuid.get().getPuuid()).isEqualTo("NJKDDNWJK214DJ");
		
	}
	
	@Test
	@DisplayName("산출물 조회")
	public void figureTest() {
		PeriodFigure figure = new PeriodFigure(new Period(2021,1));
		String[] labels = figure.gatLabel();
		assertThat(labels.length).isEqualTo(31);
		
	}
	
	@Test
	@DisplayName("기간 일별 분할")
	public void periodTest() {
		Period period = new Period(2021);
		List<LocalDateTime> days = period.splitByDay();
		assertThat(days.size()).isEqualTo(365);
	}
}
