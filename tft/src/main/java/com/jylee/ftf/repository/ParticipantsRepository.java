package com.jylee.ftf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jylee.tft.dao.Participants;

public interface ParticipantsRepository  extends JpaRepository<Participants,Long> {

	public List<Participants> findAllByMatchId(String matchId);
	public List<Participants> findAllByPuuid(String puuid);
	public Participants save(Participants participants);
}