package com.example.demo.test.timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateConversion {

	public static Date getRegularMarketTime(String timestamp) {  // Unix timestamp
		long regularMarketTime = Long.valueOf(timestamp);
		String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
		
		Instant instant = Instant.ofEpochSecond(regularMarketTime);
		ZoneId cstTimezone = ZoneId.of("UTC"); 

		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, cstTimezone);
		ZonedDateTime zonedDateTime = localDateTime.atZone(cstTimezone);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		String formattedDateTime = zonedDateTime.format(formatter);
//		 System.out.println("regularMarketTime: " + formattedDateTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(formattedDateTime);          
//          System.out.println("Parsed Date: " + date);
            
            return date;
            
        } catch (ParseException e) {
            e.printStackTrace();
        }  
        return null;
	}
}
