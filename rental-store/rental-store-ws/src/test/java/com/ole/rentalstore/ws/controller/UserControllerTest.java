package com.ole.rentalstore.ws.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import com.ole.rentalstore.business.service.UserService;
import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedEmailException;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedUsernameException;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentEmailOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentUsernameOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.not_found.UnreachableEmailException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenEmailAndPasswordException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenEmailAndUsernameException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenTokenAndEmailException;
import com.ole.rentalstore.commons.utils.RegistrationStatus;

@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private static UserDTO user = new UserDTO("email@email.com", "senha123", "Joao da Silva", "username1");
	
	protected static final String USER_DTO_SUCCESS = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
	protected static final String USER_DTO_INVALID_EMAIL = " {\"email\": \"emailemail.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
	protected static final String USER_DTO_INVALID_USERNAME = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joa*o001\"}";
	protected static final String USER_DTO_INVALID_PASSWORD = " {\"email\": \"email@email.com\",\"password\": \"se123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
	protected static final String USER_DTO_INVALID_COMPLETE_NAME = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao1 da Silva\",\"username\": \"joao001\"}";
	
	protected static final String USER_FOR_PASSWORD_CHANGE_SUCCESS = " {\"email\": \"email@email.com\",\"newPassword\": \"senha123\",\"newPasswordConfirmation\": \"senha123\",\"confirmationToken\": \"123456\"}";
	protected static final String USER_FOR_PASSWORD_CHANGE_INVALID_EMAIL = " {\"email\": \"emailemail.com\",\"newPassword\": \"senha123\",\"newPasswordConfirmation\": \"senha123\",\"confirmationToken\": \"123456\"}";
	protected static final String USER_FOR_PASSWORD_CHANGE_INVALID_NEW_PASSWORD = " {\"email\": \"email@email.com\",\"newPassword\": \"123\",\"newPasswordConfirmation\": \"senha123\",\"confirmationToken\": \"123456\"}";
	protected static final String USER_FOR_PASSWORD_CHANGE_INVALID_PASSWORDS_MISMATCH = " {\"email\": \"email@email.com\",\"newPassword\": \"senha123\",\"newPasswordConfirmation\": \"senha1234\",\"confirmationToken\": \"123456\"}";
	protected static final String USER_FOR_PASSWORD_CHANGE_INVALID_CONFIRMATION_TOKEN = " {\"email\": \"email@email.com\",\"newPassword\": \"senha123\",\"newPasswordConfirmation\": \"senha123\",\"confirmationToken\": \"12456\"}";
	
	@Before
	public void setUp() {
		setUp(userController);
	}

	@Test
	public void testGetUserThroughEmailRegistered() throws Exception {
		user.setRegistrationStatus(RegistrationStatus.REGISTERED);
		when(userService.getUserThroughEmail(anyString())).thenReturn(user);
		mockMvc.perform(get("/users/{email}".replace("{email}", "foo@bar"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.registrationStatus", equalTo("REGISTERED")));
	}

	@Test
	public void testGetUserThroughEmailInexistent() throws Exception {
		user.setRegistrationStatus(RegistrationStatus.INEXISTENT);
		when(userService.getUserThroughEmail(anyString())).thenReturn(user);
		mockMvc.perform(get("/users/{email}".replace("{email}", "foo@bar"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.registrationStatus", equalTo("INEXISTENT")));
	}

	@Test
	public void testGetUserThroughEmailPending() throws Exception {
		when(userService.getUserThroughEmail(anyString())).thenReturn(user);
		mockMvc.perform(get("/users/{email}".replace("{email}", "foo@bar"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.registrationStatus", equalTo("PENDING")));
	}

	@Test
	public void testGetUserThroughEmailInvalidEmail() throws Exception {
		when(userService.getUserThroughEmail(anyString())).thenReturn(user);
		mockMvc.perform(get("/users/{email}".replace("{email}", "foobar"))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testCreateUserSuccess() throws Exception {
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isOk());
	}

	@Test
	public void testCreateUserInvalidEmail() throws Exception {
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_INVALID_EMAIL))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testCreateInvalidUsername() throws Exception {
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_INVALID_USERNAME))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.key", equalTo("error.invalid.username")));
	}

	@Test
	public void testCreateUserInvalidPassword() throws Exception {
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_INVALID_PASSWORD))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.key", equalTo("error.invalid.password")));
	}

	@Test
	public void testCreateUserInvalidCompleteName() throws Exception {
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_INVALID_COMPLETE_NAME))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.key", equalTo("error.invalid.name")));
	}

	@Test
	public void testCreateUserDuplicatedEmail() throws Exception {
		doThrow(new DuplicatedEmailException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_EMAIL)))
				.when(userService).createUser(any());
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.key", equalTo("error.resource.email.duplicated")));
	}

	@Test
	public void testCreateUserDuplicatedUsername() throws Exception {
		doThrow(new DuplicatedUsernameException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_USERNAME)))
				.when(userService).createUser(any());
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.key", equalTo("error.resource.username.duplicated")));
	}

	@Test
	public void testCreateUserUnrecheableEmailAddress() throws Exception {
		doThrow(new UnreachableEmailException(new ErrorResponse(ErrorMessage.Inexistent.UNREACHABLE_EMAIL)))
				.when(userService).createUser(any());
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.key", equalTo("error.inexistent.email.smtp")));
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		mockMvc.perform(
				post("/users/{email}/register/{token}".replace("{email}", "foo@bar").replace("{token}", "123456")))
				.andExpect(status().isOk());
	}

	@Test
	public void testRegisterUserInvalidEmail() throws Exception {
		mockMvc.perform(
				post("/users/{email}/register/{token}".replace("{email}", "foobar").replace("{token}", "123456")))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testRegisterUserInvalidToken() throws Exception {
		mockMvc.perform(
				post("/users/{email}/register/{token}".replace("{email}", "foo@bar").replace("{token}", "13456")))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.key", equalTo("error.invalid.token")));
	}

	@Test
	public void testRegisterUserIncorrectToken() throws Exception {
		doThrow(new IncorrectAssociationBetweenTokenAndEmailException(
				new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_TOKEN)))
				.when(userService).registerUser(anyString(), anyString());
		mockMvc.perform(
				post("/users/{email}/register/{token}".replace("{email}", "foo@bar").replace("{token}", "123456")))
				.andExpect(status().isUnauthorized()).andExpect(jsonPath("$.key", equalTo("error.unauthorized.token")));
	}

	@Test
	public void testRegisterUserInexistentEmail() throws Exception {
		doThrow(new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL)))
				.when(userService).registerUser(anyString(), anyString());
		mockMvc.perform(
				post("/users/{email}/register/{token}".replace("{email}", "foo@bar").replace("{token}", "123456")))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.key", equalTo("error.inexistent.email")));
	}

	@Test
	public void testAuthenticateUserSuccess() throws Exception {
		mockMvc.perform(
				post("/users/validate").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isOk());
	}

	@Test
	public void testAuthenticateUserIncorrectData() throws Exception {
		doThrow(new IncorrectAssociationBetweenEmailAndPasswordException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_PASSWORD)))
				.when(userService).authenticateUser(any());
		mockMvc.perform(
				post("/users/validate").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.key", equalTo("error.unauthorized.login")));
	}

	@Test
	public void testValidateEmailAndUsernameSuccess() throws Exception {
		mockMvc.perform(
				post("/users/confirm-data").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isOk());
	}

	@Test
	public void testValidateEmailAndUsernameInvalidEmail() throws Exception {
		mockMvc.perform(
				post("/users/confirm-data").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_INVALID_EMAIL))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testValidateEmailAndUsernameInvalidUsername() throws Exception {
		mockMvc.perform(
				post("/users/confirm-data").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_INVALID_USERNAME))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.username")));
	}

	@Test
	public void testValidateEmailAndUsernameInexistentEmail() throws Exception {
		doThrow(new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL)))
				.when(userService).validateEmailAndUsername(any());
		mockMvc.perform(
				post("/users/confirm-data").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key", equalTo("error.inexistent.email")));
	}

	@Test
	public void testValidateEmailAndUsernameInexistentUsername() throws Exception {
		doThrow(new InexistentUsernameOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_USERNAME)))
				.when(userService).validateEmailAndUsername(any());
		mockMvc.perform(
				post("/users/confirm-data").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key", equalTo("error.inexistent.username")));
	}

	@Test
	public void testValidateEmailAndUsernameIncorrectData() throws Exception {
		doThrow(new IncorrectAssociationBetweenEmailAndUsernameException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_USERNAME)))
				.when(userService).validateEmailAndUsername(any());
		mockMvc.perform(
				post("/users/confirm-data").contentType(MediaType.APPLICATION_JSON).content(USER_DTO_SUCCESS))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.key", equalTo("error.unauthorized.username")));
	}

	@Test
	public void testChangeUserPasswordSuccess() throws Exception {
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_SUCCESS))
				.andExpect(status().isOk());
	}

	@Test
	public void testChangeUserPasswordInvalidEmail() throws Exception {
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_INVALID_EMAIL))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testChangeUserPasswordInvalidNewPassword() throws Exception {
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_INVALID_NEW_PASSWORD))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.password")));
	}

	@Test
	public void testChangeUserPasswordNewPasswordAndConfirmationMismatch() throws Exception {
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_INVALID_PASSWORDS_MISMATCH))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.password.mismatch")));
	}

	@Test
	public void testChangeUserPasswordInvalidToken() throws Exception {
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_INVALID_CONFIRMATION_TOKEN))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.key", equalTo("error.invalid.token")));
	}

	@Test
	public void testChangeUserPasswordIncorrectToken() throws Exception {
		doThrow(new IncorrectAssociationBetweenTokenAndEmailException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_TOKEN)))
				.when(userService).changeUserPassword(any());
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_SUCCESS))
				.andExpect(status().isUnauthorized())
				.andExpect(jsonPath("$.key", equalTo("error.unauthorized.token")));
	}

	@Test
	public void testChangeUserPasswordInexistentEmail() throws Exception {
		doThrow(new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL)))
				.when(userService).changeUserPassword(any());
		mockMvc.perform(
				put("/users/password").contentType(MediaType.APPLICATION_JSON).content(USER_FOR_PASSWORD_CHANGE_SUCCESS))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key", equalTo("error.inexistent.email")));
	}
}