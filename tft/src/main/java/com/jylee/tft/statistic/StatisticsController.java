/**
  * @Package : com.jylee.tft.controller
  * @FileName : StatisticsController.java
  * @Date : 2020. 12. 14. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.statistic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
  * @Package : com.jylee.tft.controller
  * @FileName : StatisticsController.java
  * @Date : 2020. 12. 14. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계 컨트롤러
  */

@Controller
public class StatisticsController {
	
	@GetMapping("/statistics")
	public String statistics(Model model) {
		return "statistic/statistics";
	}
}
