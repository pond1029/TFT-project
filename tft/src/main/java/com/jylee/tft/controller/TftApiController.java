package com.jylee.tft.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jylee.tft.dao.PlayStatistics;
import com.jylee.tft.service.ApiManager;
import com.jylee.tft.service.Statistic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/tft")
public class TftApiController {
	private static final String puuid = "EbsbjdhoWzAvsnU_C8p9djSq_A9-_7pwXU0WaJ_EN_8SbWS0bR7djr5EVa99ZKq1QyT8yswhvDeeOg";
	
	@Autowired
	ApiManager apiManager;
	
	@Autowired
	Statistic statistic;
	
	@ResponseBody
	@RequestMapping(path="/main.do", produces="text/plain; charset=UTF-8")
	public ModelAndView tft(@RequestParam Map<String, Object> paramMap)
	{
		
		ModelAndView modelAndView = new ModelAndView("/tft");
		
		return modelAndView;
	}

	@RequestMapping(value="/playInfo", method = RequestMethod.GET)
	public PlayStatistics playInfo(@RequestParam String summonerId)
	{		
		PlayStatistics playStatistics = statistic.getPlayStatistics(2020, 9,puuid);

		return playStatistics;
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public ResponseEntity update(@RequestParam String summonerId)
	{  
		apiManager.update(puuid);	

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping(path="/simulation.do", produces="text/plain; charset=UTF-8")
	public ModelAndView simulation(@RequestParam Map<String, Object> paramMap)
	{
		
		ModelAndView modelAndView = new ModelAndView("/simulation");
		
		return modelAndView;
	}
	
}
