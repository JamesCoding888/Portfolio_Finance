package com.example.demo.test.percentInchange;

import org.junit.jupiter.api.Test;

public class ChangeInPercentCalculator {
	@Test
	public void test() {
		Double changeInPercent = ChangeInPercentCalculator.getChangeInPercent(110.0, 100.0);
		System.out.println("Change in percent: " + changeInPercent + "%");
	}

	public static Double getChangeInPercent(Double currentPrice, Double previousPrice) {

		double changeInPercent = ((currentPrice - previousPrice) / previousPrice) * 100;

		return changeInPercent;
	}
	
	
}
