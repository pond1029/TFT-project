package com.jylee.tft.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jylee.tft.simulation.ReRoll;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path="/simulation")
public class SimulationController {
	
	@RequestMapping(value="/reroll", method = RequestMethod.GET)
	public ResponseEntity playInfo()
	{		
		ReRoll reroll = new ReRoll(1, false);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	
}
