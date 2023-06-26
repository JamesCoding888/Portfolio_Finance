package com.example.demo.finance;

public class ChangeInPercentCalculator {
	
	public static Double getChangeInPercent(Double currentPrice, Double previousPrice) {
        
        double changeInPercent = ((currentPrice - previousPrice) / previousPrice) * 100;       
        
        return changeInPercent;
    }
}
