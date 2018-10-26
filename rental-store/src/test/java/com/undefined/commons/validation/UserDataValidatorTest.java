package com.undefined.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.undefined.RentalStoreApplication;
import com.undefined.commons.validation.UserDataValidator;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RentalStoreApplication.class, DataSourceAutoConfiguration.class})
@TestPropertySource("classpath:application.properties")
public class UserDataValidatorTest {

	@Autowired
	private UserDataValidator userDataValidator;
	
	@Test
	public void testEmailAlreadyExistsTrue() {
		assertThat(userDataValidator.emailAlreadyExists("exemplo@exemplo.com")).isEqualTo(true);
	}
	
	@Test
	public void testEmailAlreadyExistsFalse() {
		assertThat(userDataValidator.emailAlreadyExists("exemplo@naoexiste.com")).isEqualTo(false);
	}
	
	@Test
	public void testUsernameAlreadyExistsTrue() {
		assertThat(userDataValidator.usernameAlreadyExists("fulano1")).isEqualTo(true);
	}
	
	@Test
	public void testUsernameAlreadyExistsFalse() {
		assertThat(userDataValidator.usernameAlreadyExists("usernaoexiste")).isEqualTo(false);
	}
}