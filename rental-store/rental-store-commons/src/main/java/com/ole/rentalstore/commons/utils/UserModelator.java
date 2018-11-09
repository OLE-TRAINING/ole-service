package com.ole.rentalstore.commons.utils;

import com.ole.rentalstore.commons.dto.UserDTO;

public class UserModelator {

private UserModelator() {}
	
	public static UserDTO getInexistingUser() {
		UserDTO user = new UserDTO();
		user.setRegistrationStatus(RegistrationStatus.INEXISTENT);
		return user;
	}
	
	public static String getStringLowerCase(String str) {
		return str.toLowerCase();
	}
}