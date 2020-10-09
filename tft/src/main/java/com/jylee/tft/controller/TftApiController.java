package com.jylee.tft.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.jylee.tft.service.TftApiService;
import com.jylee.tft.util.PondUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/tft")
public class TftApiController {
	private static final String puuid = "EbsbjdhoWzAvsnU_C8p9djSq_A9-_7pwXU0WaJ_EN_8SbWS0bR7djr5EVa99ZKq1QyT8yswhvDeeOg";
	
	@Autowired
	TftApiService tftApiService;
	
	@ResponseBody
	@RequestMapping(path="/main.do", produces="text/plain; charset=UTF-8")
	public ModelAndView tft(@RequestParam Map<String, Object> paramMap)
	{
		
		ModelAndView modelAndView = new ModelAndView("/tft");
		
		
		return modelAndView;
	}

	@RequestMapping(value="/playInfo", method = RequestMethod.GET)
	public Map<String, Object> playInfo(@RequestParam String summonerId)
	{
		Map playInfo = tftApiService.getPlayInfo(puuid);
		
		return playInfo;
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public Map<String, Object> update(@RequestParam String summonerId)
	{
  
		tftApiService.update(puuid);
		
		Map<String, Object> playInfo = tftApiService.getPlayInfo(puuid); 		

		return playInfo;
	}
	
	
	@RequestMapping(path="/simulation.do", produces="text/plain; charset=UTF-8")
	public ModelAndView simulation(@RequestParam Map<String, Object> paramMap)
	{
		
		ModelAndView modelAndView = new ModelAndView("/simulation");
		
		return modelAndView;
	}
	
}
