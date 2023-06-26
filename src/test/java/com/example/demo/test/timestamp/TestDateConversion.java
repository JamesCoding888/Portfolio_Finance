package com.example.demo.test.timestamp;

import java.io.IOException;
import java.util.Date;
import com.example.demo.finance.YahooFinanceQuotesQueryV8;
import yahoofinance.query2v8.HistQuotesQuery2V8Request;
import com.example.demo.test.timestamp.YahooFinanceUtil;

public class TestDateConversion {
	public static void main(String[] args) throws IOException {
		// YahooFinanceUtil
//		/*
		YahooFinanceUtil yahooFinanceUtil = new YahooFinanceUtil();
		yahooFinanceUtil.finance("2330.TW");
		// Get regularMarketTime (Unit: Unix timestamp)		
		Integer regularMarketTime = yahooFinanceUtil.getRegularMarketTime();
		System.out.println(regularMarketTime);
		// DateConversion from timestamp to Date
		Date date = DateConversion2.getRegularMarketTime(regularMarketTime);
		System.out.println(date);
//		*/
		
		// YahooFinanceQuotesQueryV8
		/*
		YahooFinanceQuotesQueryV8 yahooFinanceQuotesQueryV8 = new YahooFinanceQuotesQueryV8();
		String jsonString = new HistQuotesQuery2V8Request("2330.TW").getJson();							
		yahooFinanceQuotesQueryV8.finance(jsonString);
		
		// Acquire data from Yahoo Finance 
		System.out.println(jsonString);
		
		// Get regularMarketTime (Unit: Unix timestamp)
		Integer regularMarketTime = yahooFinanceQuotesQueryV8.getRegularMarketTime();
		System.out.println(regularMarketTime);
		
		// DateConversion from timestamp to Date
		Date date = DateConversion2.getRegularMarketTime(regularMarketTime);
		System.out.println(date);
		*/
	}
}
