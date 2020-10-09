package com.jylee.tft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jylee.ftf.repository.ParticipantsRepository;
import com.jylee.tft.dao.Participants;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ParticipantsService {

	private final ParticipantsRepository participantsRepository;
	
	public List<Participants> getParticipants(String puuid){
		return participantsRepository.findAllByPuuid(puuid);
	}
	
	public Participants setparticipants(Participants participant) {
		return participantsRepository.save(participant);
	}
}
