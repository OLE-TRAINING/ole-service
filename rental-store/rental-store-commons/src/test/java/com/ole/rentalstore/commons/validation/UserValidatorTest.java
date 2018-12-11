package com.ole.rentalstore.commons.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.dto.UserForPasswordChangeDTO;
import com.ole.rentalstore.commons.error.ErrorResponse;

public class UserValidatorTest {

	private static UserDTO user = getUser();
	private static UserForPasswordChangeDTO userPasswordChange = getUserPasswordChange();
	
	private static UserDTO getUser() {
		UserDTO user = new UserDTO();
		user.setEmail("email@email.com");
		user.setCompleteName("Bruno Silva");
		user.setUsername("brunosilva");
		user.setPassword("pass123");
		return user;
	}
	
	private static UserForPasswordChangeDTO getUserPasswordChange() {
		UserForPasswordChangeDTO userPasswordChange = new UserForPasswordChangeDTO();
		userPasswordChange.setEmail("foo@bar");
		userPasswordChange.setConfirmationToken("123456");
		userPasswordChange.setNewPassword("senha123");
		userPasswordChange.setNewPasswordConfirmation("senha123");
		return userPasswordChange;
	}
	
	@Test
	public void testValidateUserInvalidPassword() {
		user = getUser();
		user.setPassword("password");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.password");
	}
	
	@Test
	public void testValidateUserInvalidName() {
		user = getUser();
		user.setCompleteName("Bruno Silva1");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.name");
	}
	
	@Test
	public void testValidateUserInvalidUsername() {
		user = getUser();
		user.setUsername("bruno silva");
		ErrorResponse error = UserValidator.validateUser(user);
		
		assertThat(error.getKey()).isEqualTo("error.invalid.username");
	}
	
	@Test
	public void testValidateUserInvalidEmail() {
		user = getUser();
		user.setEmail("aa@-*com");
		ErrorResponse error = UserValidator.validateUser(user);
		assertThat(error.getKey()).isEqualTo("error.invalid.email");
	}
	
	@Test
	public void testValidateUserValid() {
		user = getUser();
		ErrorResponse error = UserValidator.validateUser(user);
		assertThat(error).isNull();
	}
	
	@Test
	public void testValidateUserPasswordChangeInvalidEmail() {
		userPasswordChange = getUserPasswordChange();
		userPasswordChange.setEmail("foobar");
		ErrorResponse error = UserValidator.validateUserPasswordChange(userPasswordChange);
		assertThat(error.getKey()).isEqualTo("error.invalid.email");
	}
	
	@Test
	public void testValidateUserPasswordChangeInvalidNewPassword() {
		userPasswordChange = getUserPasswordChange();
		userPasswordChange.setNewPassword("123");
		ErrorResponse error = UserValidator.validateUserPasswordChange(userPasswordChange);
		assertThat(error.getKey()).isEqualTo("error.invalid.password");
	}
	
	@Test
	public void testValidateUserPasswordChangeInvalidNewPasswordConfirmation() {
		userPasswordChange = getUserPasswordChange();
		userPasswordChange.setNewPasswordConfirmation("senha1234");
		ErrorResponse error = UserValidator.validateUserPasswordChange(userPasswordChange);
		assertThat(error.getKey()).isEqualTo("error.invalid.password.mismatch");
	}
	
	@Test
	public void testValidateUserPasswordChangeInvalidConfirmationToken() {
		userPasswordChange = getUserPasswordChange();
		userPasswordChange.setConfirmationToken("12345");
		ErrorResponse error = UserValidator.validateUserPasswordChange(userPasswordChange);
		assertThat(error.getKey()).isEqualTo("error.invalid.token");
	}
	
	@Test
	public void testValidateUserForPasswordChangeValid() {
		userPasswordChange = getUserPasswordChange();
		ErrorResponse error = UserValidator.validateUserPasswordChange(userPasswordChange);
		assertThat(error).isNull();
	}
	
	@Test
	public void testValidatePasswordInvalidSpecialChar() {
		assertThat(UserValidator.validatePassword("nomequal98*quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidSpace() {
		assertThat(UserValidator.validatePassword("nomequal98 quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidMinimumLength() {
		assertThat(UserValidator.validatePassword("nom")).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidMaximumLength() {
		assertThat(UserValidator.validatePassword("passpasspasspasspasspass")).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidOnlyLetters() {
		assertThat(UserValidator.validatePassword("password")).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordInvalidOnlyNumbers() {
		assertThat(UserValidator.validatePassword("1234567")).isEqualTo(false);
	}
	
	@Test
	public void testValidatePasswordValid() {
		assertThat(UserValidator.validatePassword("senha123")).isEqualTo(true);
	}
	
	@Test
	public void testValidateNameInvalidNumber() {
		assertThat(UserValidator.validateName("nomequal98quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidSpecialChar() {
		assertThat(UserValidator.validateName("nomequal_quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidDoubleSpace() {
		assertThat(UserValidator.validateName("nomequal  quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidBeginningSpace() {
		assertThat(UserValidator.validateName(" nomequalquer")).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidEmpty() {
		assertThat(UserValidator.validateName("")).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameInvalidLengthExceeded() {
		assertThat(UserValidator.validateName("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu")).isEqualTo(false);
	}
	
	@Test
	public void testValidateNameValidSpace() {
		assertThat(UserValidator.validateName("nomequal quer")).isEqualTo(true);
	}
	
	@Test
	public void testValidateNameValid() {
		assertThat(UserValidator.validateName("nomequalquer")).isEqualTo(true);
	}
	
	@Test
	public void testValidEmail() {
		assertThat(UserValidator.validateEmail("teste@teste.com")).isEqualTo(true);
	}
	
	@Test
	public void testInvalidEmailDoubleAt() {
		assertThat(UserValidator.validateEmail("teste@@teste.com")).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailDotBeforeAt() {
		assertThat(UserValidator.validateEmail("teste.@teste.com")).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailStartWithDot() {
		assertThat(UserValidator.validateEmail(".teste.@teste.com")).isEqualTo(false);
	}
	
	@Test
	public void testInvalidEmailDotAfterAt() {
		assertThat(UserValidator.validateEmail("teste@.teste.com")).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidSpecialChar() {
		assertThat(UserValidator.validateUsername("nomequal98*quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidSpace() {
		assertThat(UserValidator.validateUsername("nome qual98quer")).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidEmpty() {
		assertThat(UserValidator.validateUsername("")).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameInvalidLengthExceeded() {
		assertThat(UserValidator.validateUsername("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu")).isEqualTo(false);
	}
	
	@Test
	public void testValidateUsernameValid() {
		assertThat(UserValidator.validateUsername("nomequal98quer")).isEqualTo(true);
	}
}