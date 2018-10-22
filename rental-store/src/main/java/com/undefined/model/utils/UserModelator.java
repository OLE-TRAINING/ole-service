package com.undefined.model.utils;

import com.undefined.model.commons.RegistrationStatus;
import com.undefined.model.entities.User;

public class UserModelator {

	private UserModelator() {}
	
	public static User getUnexistingUser() {
		User user = new User();
		user.setRegistrationStatus(RegistrationStatus.INEXISTENT);
		return user;
	}
}
