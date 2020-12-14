/**
  * @Package : com.jylee.tft.controller
  * @FileName : StatisticsController.java
  * @Date : 2020. 12. 14. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.controller;

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

import com.jylee.tft.service.DataManagerService;
import com.jylee.tft.service.StatisticsService;
import com.jylee.tft.service.statistic.PlayStatistics;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.controller
  * @FileName : StatisticsController.java
  * @Date : 2020. 12. 14. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계 컨트롤러
  */

@Slf4j
@RestController
@RequestMapping(path="/statistics")
public class StatisticsController {

	@Autowired
	DataManagerService dataManagerService;
	
	@Autowired
	StatisticsService statisticsService;
	
	@ResponseBody
	@RequestMapping(path="/main.do", produces="text/plain; charset=UTF-8")
	public ModelAndView tft(@RequestParam Map<String, Object> paramMap)
	{
		
		ModelAndView modelAndView = new ModelAndView("/statistics");
		
		return modelAndView; 
	}

	@RequestMapping(value="/playTime", method = RequestMethod.GET)
	public PlayStatistics playInfo(@RequestParam int year, @RequestParam int month, @RequestParam String summonerName)
	{		
		PlayStatistics playStatistics = statisticsService.getPlayTimeStatistics(year, month, summonerName);

		return playStatistics;
	}

	@RequestMapping(value="/update", method = RequestMethod.GET)
	public ResponseEntity update(@RequestParam String summonerId)
	{  
		
		dataManagerService.update(summonerId);	

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
