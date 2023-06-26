package com.example.demo.test.timestamp;

import java.util.Date;

public class DateConversion2 {

	public static Date getRegularMarketTime(Integer timestamp) { // Unix timestamp: 1687239195
		
		Date date = new Date(timestamp * 1000L); // Multiply by 1000 to convert seconds to milliseconds
//		System.out.println("Converted Date: " + date);
		
		return date;
	}
}
