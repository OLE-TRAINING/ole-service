package com.undefined.model.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.undefined.commons.exceptions.NullFieldException;
import com.undefined.commons.validation.NullFieldValidator;
import com.undefined.model.entities.User;

public class NullFieldValidatorTest {

	@Test
	public void testCheckForNullFieldsEmailNull() throws IllegalAccessException {
		User user = new User();
		user.setPassword("p");
		user.setCompleteName("cp");
		user.setUsername("u1");
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			assertThat(ex.getFieldName()).isEqualTo("email");
		}
	}
	
	@Test
	public void testCheckForNullFieldsUsernameNull() throws IllegalAccessException {
		User user = new User();
		user.setPassword("p");
		user.setCompleteName("cp");
		user.setEmail("u1@");
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			assertThat(ex.getFieldName()).isEqualTo("username");
		}
	}
}