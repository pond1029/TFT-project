/**
  * @Package : com.jylee.tft.controller
  * @FileName : StatisticsController.java
  * @Date : 2020. 12. 14. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.statistic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jylee.tft.modules.account.domain.Account;
import com.jylee.tft.modules.account.domain.AccountType;
import com.jylee.tft.modules.account.service.AccountFactory;
import com.jylee.tft.modules.statistic.domain.Figure;
import com.jylee.tft.modules.statistic.domain.Period;
import com.jylee.tft.modules.statistic.domain.PeriodFigure;
import com.jylee.tft.modules.statistic.service.PeriodStatistics;

import lombok.RequiredArgsConstructor;

/**
  * @Package : com.jylee.tft.controller
  * @FileName : StatisticsController.java
  * @Date : 2020. 12. 14. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information : 통계 컨트롤러
  */

@Controller
@RequiredArgsConstructor
public class StatisticsController {
		
	private final PeriodStatistics periodStatisticsService;
	
	@GetMapping("/statistics")
	public String statistics(Model model) {		
		model.addAttribute("accountTypes", AccountType.values());
		return "statistic/statistics";
	}
	
	@GetMapping("/statistics/figure")
	public String searchStatistics(String accountName, String accountType, String from, String to, Model model) {
		
		Period period = new Period(from, to);
		Figure figure = periodStatisticsService.getFigure(AccountFactory.getAccount(accountName, accountType), period);
		
		model.addAttribute("figure", figure);
		model.addAttribute("accountName", accountName);
		model.addAttribute("accountType", AccountType.valueOf(accountType).getFullName());
		model.addAttribute("from", from);
		model.addAttribute("from", to);
		
		return "statistic/statistics";
	}
	
	
}
