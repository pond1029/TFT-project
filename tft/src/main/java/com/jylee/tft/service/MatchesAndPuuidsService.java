package com.jylee.tft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jylee.tft.dao.MatchesAndPuuids;
import com.jylee.tft.repository.MatchesAndPuuidsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class MatchesAndPuuidsService {
	
	private final MatchesAndPuuidsRepository matchesAndPuuidsRepository;
		
	public List<MatchesAndPuuids> getListMatchesAndPuuids() {
		List<MatchesAndPuuids> listMatchesAndPuuids = matchesAndPuuidsRepository.findAll();
		return listMatchesAndPuuids;
	}
	
	public List<MatchesAndPuuids> getListMatchesAndPuuids(String puuid) {
		List<MatchesAndPuuids> listMatchesAndPuuids = matchesAndPuuidsRepository.findAllByPuuid(puuid);
		return listMatchesAndPuuids;
	}
	
	public MatchesAndPuuids getMatchesAndPuuids(String matchId, String puuid) {
		return matchesAndPuuidsRepository.findByMatchIdAndPuuid(matchId, puuid);
	}
	
	public void setMatchesAndPuuids(String matchId, String puuid) {
		MatchesAndPuuids matchesAndPuuids = MatchesAndPuuids.builder()
				.matchId(matchId)
				.puuid(puuid)
				.build();
		matchesAndPuuidsRepository.save(matchesAndPuuids);
	}

}
