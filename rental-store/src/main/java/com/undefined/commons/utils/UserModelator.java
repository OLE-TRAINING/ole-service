package com.undefined.commons.utils;

import com.undefined.model.entities.User;

public class UserModelator {

	private UserModelator() {}
	
	public static User getInexistingUser() {
		User user = new User();
		user.setRegistrationStatus(RegistrationStatus.INEXISTENT);
		return user;
	}
}
