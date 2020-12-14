/**
  * @Package : com.jylee.tft.dao
  * @FileName : Graph.java
  * @Date : 2020. 11. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.dao;

import java.util.ArrayList;
import java.util.List;

import com.jylee.tft.statistic.PlayTime;
import com.jylee.tft.statistic.PlayTime.PlayTimeBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
  * @Package : com.jylee.tft.dao
  * @FileName : Graph.java
  * @Date : 2020. 11. 8. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarGraph implements Graph{

	private String label;
	private List<Object> data = new ArrayList<Object>();
	private List<Object> labels = new ArrayList<Object>();
	
	public void addData(Object value) {
		this.data.add(value);
	}
	
	public void addLabels(Object lebel) {
		this.labels.add(lebel);
	}
}
