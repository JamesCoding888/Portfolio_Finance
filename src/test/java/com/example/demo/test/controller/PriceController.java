package com.example.demo.test.controller;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.TStock;
import com.example.demo.finance.DateConversion2;
import com.example.demo.finance.YahooFinanceQuotesQueryV8;
import com.example.demo.finance.YahooFinanceUtil;
import com.example.demo.repository.TStockRepository;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.query2v8.HistQuotesQuery2V8Request;

@RestController
@RequestMapping("/price")
public class PriceController {
	
	@Autowired
	private TStockRepository tStockRepository;
	
	@RequestMapping("/refresh")
	@Transactional
	public List<TStock> refresh() {
		List<TStock> list = tStockRepository.findAll();
		for(TStock tStock: list) {
			try {
				// 抓取報價
				/*
					Stock stock = YahooFinance.get(tStock.getSymbol());
					tStock.setChangePrice(stock.getQuote().getChange());
					tStock.setChangeInPercent(stock.getQuote().getChange());
					tStock.setPreClosed(stock.getQuote().getPreviousClose());
					tStock.setPrice(stock.getQuote().getPrice());
					tStock.setTransactionDate(stock.getQuote().getLastTradeTime().getTime());
					tStock.setVolumn(stock.getQuote().getVolume());
				*/
				// 抓取報價 - 使用 HISTQUOTES_QUERY2V8_BASE_URL
				/*				
					String jsonString = new HistQuotesQuery2V8Request(tStock.getSymbol()).getJson();					
					YahooFinanceQuotesQueryV8 yahooFinanceQuotesQueryV8 = new YahooFinanceQuotesQueryV8();
					yahooFinanceQuotesQueryV8.finance(jsonString);		
					tStock.setChangePrice(BigDecimal.valueOf(Double.valueOf(yahooFinanceQuotesQueryV8.regularMarketPrice)));
					tStock.setChangeInPercent(BigDecimal.valueOf(10.0));
					tStock.setPreClosed(BigDecimal.valueOf(Double.valueOf(yahooFinanceQuotesQueryV8.previousClose)));
					tStock.setPrice(BigDecimal.valueOf(Double.valueOf(yahooFinanceQuotesQueryV8.open)));																								
					tStock.setTransactionDate(DateConversion2.getRegularMarketTime(yahooFinanceQuotesQueryV8.regularMarketTime));
					tStock.setVolumn(Long.valueOf(yahooFinanceQuotesQueryV8.volume));
				*/
				// 自行設計 YahooFinanceUtil
				/*
				String ticker = tStock.getSymbol();
				YahooFinanceUtil yahooFinanceUtil = new YahooFinanceUtil();
				yahooFinanceUtil.finance(ticker);
				
				tStock.setChangePrice(BigDecimal.valueOf(Double.valueOf(yahooFinanceUtil.regularMarketPrice)));
				tStock.setChangeInPercent(BigDecimal.valueOf(10.0));
				tStock.setPreClosed(BigDecimal.valueOf(Double.valueOf(yahooFinanceUtil.previousClose)));
				tStock.setPrice(BigDecimal.valueOf(Double.valueOf(yahooFinanceUtil.open)));
				tStock.setTransactionDate(new Date());
				tStock.setVolumn(Long.valueOf(yahooFinanceUtil.volume));
				*/
				// 更新報價
				tStockRepository.updatePrice(
						tStock.getId(), 
						tStock.getChangePrice(), 
						tStock.getChangeInPercent(), 
						tStock.getPreClosed(), 
						tStock.getPrice(), 
						tStock.getTransactionDate(), 
						tStock.getVolumn());
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return list;
	}
	
	
}
