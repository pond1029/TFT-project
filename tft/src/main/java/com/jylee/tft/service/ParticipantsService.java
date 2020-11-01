package com.jylee.tft.service;

import java.util.List;

import javax.transaction.Transactional;

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
	
	public Participants setparticipants(Participants participants) {
		return participantsRepository.save(participants);
	}
	

	@Transactional
	public void setparticipants(List<Participants> participantsLists) {
		for(Participants participant : participantsLists) {
			participantsRepository.save(participant);
		}
	}
	
}
