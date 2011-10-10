package com.google.code.iriyanote;

public class StringUtils {
	private StringUtils() {
		
	}
	
	public static String paddingLeft(final String x, int length, char c) {
		if(x.length() >= length) return x;
		String result = x;
		for(int i=0;i<length-x.length();i++) {
			result = String.valueOf(c).concat(result);
		}
		return result;
	}
}
