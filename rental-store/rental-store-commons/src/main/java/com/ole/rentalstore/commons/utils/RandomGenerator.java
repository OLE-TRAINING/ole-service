package com.ole.rentalstore.commons.utils;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
	
	private RandomGenerator() {
		
	}

	public static boolean getRandomBoolean() {
		return ThreadLocalRandom.current().nextBoolean();
	}
	
	public static BigDecimal getRandomFormattedPrice() {
		BigDecimal price = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1.99, 70));
		return price.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public static String getRandomFormattedRuntime() {
		int runtime = ThreadLocalRandom.current().nextInt(50, 181);
		StringBuilder runtimeFormatted = new StringBuilder();
		int hours = runtime/60;
		if (hours != 0) {
			runtimeFormatted.append(String.valueOf(hours + "h"));
		}
		int minutes = runtime%60;
		if (minutes != 0) {
			runtimeFormatted.append(' ');
			if (minutes < 10) {
				runtimeFormatted.append('0');
			}
			runtimeFormatted.append(String.valueOf(minutes + "min"));
		}
		return runtimeFormatted.toString();
	} 
}
