package com.undefined.model.utils;

import java.util.regex.Pattern;

public class EmailValidator {
	
	private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	private static Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
	
	private EmailValidator() {
		
	}
	
	public static boolean validateEmail(String email) {
		return pattern.matcher(email).matches();
	}
}