package com.example.demo.test.timestamp;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class YahooFinanceUtil {
	// Set specific parameters from the meta object
	private String currency = null;
	private String symbol = null;
	private String exchangeName = null;
	private String instrumentType = null;
	private Integer firstTradeDate = null;
	private Integer regularMarketTime = null;
	private Integer gmtoffset = null;
	private String timezone = null;
	private String exchangeTimezoneName = null;
	private Double regularMarketPrice = null;
	private Double chartPreviousClose = null;
	private Double previousClose = null;
	private Integer scale = null;
	private Integer priceHint = null;
	private JsonElement currentTradingPeriod = null;
	private JsonElement tradingPeriods = null;
	private String dataGranularity = null;
	private String range = null;
	private JsonElement validRanges = null;

	// Set specific parameters from the quote object
	private Double low = null;
	private Double open = null;
	private Double close = null;
	private Long volume = null;
	private Double high = null;

	// Set the change in percent
	private Double changeInPercent = null;

	// Getter
	public String getCurrency() {
		return currency;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public String getInstrumentType() {
		return instrumentType;
	}

	public Integer getFirstTradeDate() {
		return firstTradeDate;
	}

	public Integer getRegularMarketTime() {
		return regularMarketTime;
	}

	public Integer getGmtoffset() {
		return gmtoffset;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getExchangeTimezoneName() {
		return exchangeTimezoneName;
	}

	public Double getRegularMarketPrice() {
		return regularMarketPrice;
	}

	public Double getChartPreviousClose() {
		return chartPreviousClose;
	}

	public Double getPreviousClose() {
		return previousClose;
	}

	public Integer getScale() {
		return scale;
	}

	public Integer getPriceHint() {
		return priceHint;
	}

	public JsonElement getCurrentTradingPeriod() {
		return currentTradingPeriod;
	}

	public JsonElement getTradingPeriods() {
		return tradingPeriods;
	}

	public String getDataGranularity() {
		return dataGranularity;
	}

	public String getRange() {
		return range;
	}

	public JsonElement getValidRanges() {
		return validRanges;
	}

	public Double getLow() {
		return low;
	}

	public Double getOpen() {
		return open;
	}

	public Double getClose() {
		return close;
	}

	public Long getVolume() {
		return volume;
	}

	public Double getHigh() {
		return high;
	}

	public Double getChangeInPercent() {
		return changeInPercent;
	}
    
    
	public void finance(String ticker) {
        
        try {
            // Create the URL object
            URL url = new URL("https://query1.finance.yahoo.com/v7/finance/chart/" + ticker);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response from the URL
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response into a string
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse the JSON response using Gson
                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

                // Extract parameters from the JSON object
                JsonObject chartObject = jsonResponse.getAsJsonObject("chart");
                JsonObject resultObject = chartObject.getAsJsonArray("result").get(0).getAsJsonObject();
                JsonObject metaObject = resultObject.getAsJsonObject("meta");
                JsonObject indicatorsObject = resultObject.getAsJsonObject("indicators");
                JsonObject quoteObject = indicatorsObject.getAsJsonArray("quote").get(0).getAsJsonObject();
                JsonElement timestampObject = resultObject.get("timestamp").getAsJsonArray().get(0);
                
                int sizeOfVolume = quoteObject.getAsJsonArray("volume").size();
                                
                // Get specific parameters from the quote object
                currency = metaObject.get("currency").getAsString();
                symbol = metaObject.get("symbol").getAsString();
                exchangeName = metaObject.get("exchangeName").getAsString();
                instrumentType = metaObject.get("instrumentType").getAsString();
                firstTradeDate = metaObject.get("firstTradeDate").getAsInt();
                regularMarketTime = metaObject.get("regularMarketTime").getAsInt();
                gmtoffset = metaObject.get("gmtoffset").getAsInt();
                timezone = metaObject.get("timezone").getAsString();
                exchangeTimezoneName = metaObject.get("exchangeTimezoneName").getAsString();
                regularMarketPrice = metaObject.get("regularMarketPrice").getAsDouble();
                chartPreviousClose = metaObject.get("chartPreviousClose").getAsDouble();
                previousClose = metaObject.get("previousClose").getAsDouble();
                scale = metaObject.get("scale").getAsInt();
                priceHint = metaObject.get("priceHint").getAsInt();
                currentTradingPeriod = metaObject.get("currentTradingPeriod");
                tradingPeriods = metaObject.get("tradingPeriods");
                dataGranularity = metaObject.get("dataGranularity").getAsString();
                range = metaObject.get("range").getAsString();
                validRanges = metaObject.get("validRanges");
                
                // Get specific parameters from the quote object               
                low = quoteObject.getAsJsonArray("low").get(1).getAsDouble();
                open = quoteObject.getAsJsonArray("open").get(1).getAsDouble();
                close = quoteObject.getAsJsonArray("close").get(1).getAsDouble();
                volume = quoteObject.getAsJsonArray("volume").get(1).getAsLong();
                high = quoteObject.getAsJsonArray("high").get(1).getAsDouble();
              
               
                // Print the extracted parameters
                System.out.println("Currency: " + currency);
                System.out.println("Symbol: " + symbol);
                System.out.println("Exchange Name: " + exchangeName);
                System.out.println("Instrument Type: " + instrumentType);
                System.out.println("First Trade Date: " + firstTradeDate);
                System.out.println("Regular Market Time: " + regularMarketTime);
                System.out.println("gmtoffset: " + gmtoffset);
                System.out.println("timezone: " + timezone);
                System.out.println("exchangeTimezoneName: " + exchangeTimezoneName);
                System.out.println("Regular Market Price: " + regularMarketPrice);
                System.out.println("chartPreviousClose: " + chartPreviousClose);                
                System.out.println("Previous Close: " + previousClose);
                System.out.println("scale: " + scale);                
                System.out.println("priceHint: " + priceHint);
                System.out.println("currentTradingPeriod: " + currentTradingPeriod);
                System.out.println("tradingPeriods: " + tradingPeriods);
                System.out.println("dataGranularity: " + dataGranularity);
                System.out.println("range: " + range);
                System.out.println("validRanges: " + validRanges);
                System.out.println("timestampObject: " + timestampObject);
                System.out.println("low: " + low);
                System.out.println("open: " + open);
                System.out.println("close: " + close);
                System.out.println("Volume: " + volume);
                System.out.println("high: " + high);
                System.out.println("sizeOfVolume: " + sizeOfVolume);
                // System.out.println("quoteObject: " + quoteObject);                
                
                                              
            } else {
                System.out.println("HTTP error code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
	public void getJsonElementFromQuote(int index) {
		// Get specific parameters from the quote object
		String low = null;
		String open = null;
		String close = null;
		String volume = null;
		String high = null;

		try {
			// Create the URL object
			URL url = new URL("https://query1.finance.yahoo.com/v7/finance/chart/2330.TW");

			// Open a connection to the URL
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Get the response from the URL
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Read the response into a string
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
				reader.close();

				// Parse the JSON response using Gson
				JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
				JsonObject chartObject = jsonResponse.getAsJsonObject("chart");
				JsonObject resultObject = chartObject.getAsJsonArray("result").get(0).getAsJsonObject();
				JsonObject indicatorsObject = resultObject.getAsJsonObject("indicators");
				JsonObject quoteObject = indicatorsObject.getAsJsonArray("quote").get(0).getAsJsonObject();
				
				// (60 min * 4 hrs) + 30 min = 270 mins ; 每一分鐘產生一筆資料
				for (index = 0; index < 270; index++) {
					if (quoteObject.getAsJsonArray("volume").get(index).getAsString() != null) {
						volume = quoteObject.getAsJsonArray("volume").get(index).getAsString();
					}
					if (quoteObject.getAsJsonArray("low").get(index).getAsString() != null) {
						low = quoteObject.getAsJsonArray("low").get(index).getAsString();
					}
					if (quoteObject.getAsJsonArray("high").get(index).getAsString() != null) {
						high = quoteObject.getAsJsonArray("high").get(index).getAsString();
					}
					if (quoteObject.getAsJsonArray("close").get(index).getAsString() != null) {
						close = quoteObject.getAsJsonArray("close").get(index).getAsString();
					}
					if (quoteObject.getAsJsonArray("open").get(index).getAsString() != null) {
						open = quoteObject.getAsJsonArray("open").get(index).getAsString();
					}

					System.out.println("low: " + low);
					System.out.println("open: " + open);
					System.out.println("close: " + close);
					System.out.println("Volume: " + volume);
					System.out.println("high: " + high);
				}
			} else {
				System.out.println("HTTP error code: " + responseCode);
			}

			// Close the connection
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


 
