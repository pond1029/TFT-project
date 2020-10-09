package com.jylee.tft.service;

import org.springframework.stereotype.Service;

import com.jylee.ftf.repository.SummonerInfoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SummonerInfoService {

	  private final SummonerInfoRepository summonerInfoRepository;
	  
}

