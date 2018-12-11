package com.ole.rentalstore.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.exceptions.bad_request.NullFieldException;

public class NullFieldValidatorTest {

	@Test
	public void testCheckForNullFieldsEmailNull() throws IllegalAccessException {
		UserDTO user = new UserDTO();
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
		UserDTO user = new UserDTO();
		user.setPassword("p");
		user.setCompleteName("cp");
		user.setEmail("u1@");
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			assertThat(ex.getFieldName()).isEqualTo("username");
		}
	}
	
	@Test
	public void testCheckForNullFieldsPasswordNull() throws IllegalAccessException {
		UserDTO user = new UserDTO();
		user.setUsername("user");
		user.setCompleteName("cp");
		user.setEmail("u1@");
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			assertThat(ex.getFieldName()).isEqualTo("password");
		}
	}
	
	@Test
	public void testCheckForNullFieldsCompleteNameNull() throws IllegalAccessException {
		UserDTO user = new UserDTO();
		user.setPassword("p");
		user.setUsername("user");
		user.setEmail("u1@");
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			assertThat(ex.getFieldName()).isEqualTo("completeName");
		}
	}
}