package com.undefined.model.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.undefined.commons.error.ErrorResponse;
import com.undefined.commons.validation.UserValidator;
import com.undefined.model.entities.User;

public class ValidateUserTest {

	private User user;
	
	@Before
	public void initialize() {
		user = new User();
		user.setEmail("email@email.com");
		user.setCompleteName("Bruno Silva");
		user.setUsername("brunosilva");
		user.setPassword("pass123");
	}
	
	@Test
	public void testValidateUserInvalidEmail() {
		user.setEmail("email@invalid");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.email");
	}
	
	@Test
	public void testValidateUserInvalidPassword() {
		user.setPassword("password");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.password");
	}
	
	@Test
	public void testValidateUserInvalidName() {
		user.setCompleteName("Bruno Silva1");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.name");
	}
	
	@Test
	public void testValidateUserInvalidUsername() {
		user.setEmail("bruno silva");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.email");
	}
	
	@Test
	public void testValidateUserValid() {
		ErrorResponse error = UserValidator.validateUser(user);
		assertThat(error).isEqualTo(null);
	}
}
