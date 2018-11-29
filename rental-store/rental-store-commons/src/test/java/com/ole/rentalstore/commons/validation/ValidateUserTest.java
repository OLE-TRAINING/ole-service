package com.ole.rentalstore.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.error.ErrorResponse;

public class ValidateUserTest {

	private UserDTO user;
	
	@Before
	public void initialize() {
		user = new UserDTO();
		user.setEmail("email@email.com");
		user.setCompleteName("Bruno Silva");
		user.setUsername("brunosilva");
		user.setPassword("pass123");
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
		user.setUsername("bruno silva");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.username");
	}
	
	@Test
	public void testValidateUserInvalidEmail() {
		user.setEmail("aa@-*com");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.email");
	}
	
	@Test
	public void testValidateUserValid() {
		ErrorResponse error = UserValidator.validateUser(user);
		assertThat(error).isEqualTo(null);
	}
}
