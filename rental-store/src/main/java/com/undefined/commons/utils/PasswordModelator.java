package com.undefined.commons.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class PasswordModelator {
	
	private static Logger logger = Logger.getLogger(PasswordModelator.class);

	private PasswordModelator() {
		
	}
	
	public static String getEncryptedPassword(String password) {
		String result = null;
		MessageDigest md = null;
		try {
			byte[] bytes = password.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			byte[] theDigest = md.digest(bytes);
			BigInteger hash = new BigInteger(1, theDigest);
			result = hash.toString(16);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return result;
	}
}