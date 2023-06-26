package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.TStock;
import com.example.demo.finance.DateConversion2;
import com.example.demo.finance.YahooFinanceQuotesQueryV8;
import com.example.demo.repository.TStockRepository;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes2.QueryInterval;
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
//				/*				
					String jsonString = new HistQuotesQuery2V8Request(tStock.getSymbol()).getJson();					
					YahooFinanceQuotesQueryV8 yahooFinanceQuotesQueryV8 = new YahooFinanceQuotesQueryV8();
					yahooFinanceQuotesQueryV8.finance(jsonString);		
					tStock.setChangePrice(BigDecimal.valueOf(Double.valueOf(yahooFinanceQuotesQueryV8.getRegularMarketPrice())));
					tStock.setChangeInPercent(BigDecimal.valueOf(yahooFinanceQuotesQueryV8.getChangeInPercent()));					
					tStock.setPreClosed(BigDecimal.valueOf(Double.valueOf(yahooFinanceQuotesQueryV8.getChartPreviousClose())));
					tStock.setPrice(BigDecimal.valueOf(Double.valueOf(yahooFinanceQuotesQueryV8.getRegularMarketPrice())));																				
					tStock.setTransactionDate(DateConversion2.getRegularMarketTime(yahooFinanceQuotesQueryV8.getRegularMarketTime()));
					tStock.setVolumn(Long.valueOf(yahooFinanceQuotesQueryV8.getVolume()));

//				*/
				
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
	
	@RequestMapping("/histquotes/{symbol:.+}")
	public List<HistoricalQuote> queryHistoricalQuotes(@PathVariable("symbol") String symbol) {
		List<HistoricalQuote> historicalQuotes = null;
		try {
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance(); // current date
			from.add(Calendar.MONTH, -1); // last month
			// error code: 401 
			/*
				Stock stock = YahooFinance.get(symbol);
				historicalQuotes = stock.getHistory(from, to, Interval.DAILY);
			*/
			// 改寫如下
			HistQuotesQuery2V8Request histQuotesQuery2V8Request= new HistQuotesQuery2V8Request(symbol, from, to, QueryInterval.DAILY);
			historicalQuotes = histQuotesQuery2V8Request.getResult();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return historicalQuotes;
	}
}
