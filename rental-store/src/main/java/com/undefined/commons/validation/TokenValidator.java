package com.undefined.commons.validation;

public class TokenValidator {

	private TokenValidator() {
		
	}
	
	public static boolean validateToken(String token) {
		return token.length() == 6 && token.matches("[a-zA-Z0-9]+");
	}
}