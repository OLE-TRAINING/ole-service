package com.ole.rentalstore.ws.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import com.ole.rentalstore.business.service.UserService;
import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedEmailException;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedUsernameException;
import com.ole.rentalstore.commons.exceptions.not_found.UnreachableEmailException;
import com.ole.rentalstore.commons.utils.RegistrationStatus;
import com.ole.rentalstore.ws.error.GlobalExceptionHandler;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private Environment env;

	@Mock
	private UserService userService;

	@InjectMocks
	UserController userController;

	@InjectMocks
	private GlobalExceptionHandler exceptionHandler;

	private UserDTO user;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
				.setHandlerExceptionResolvers(generateExceptionHandler()).build();

		user = new UserDTO("email@email.com", "senha123", "Joao da Silva", "username1");
	}

	private ExceptionHandlerExceptionResolver generateExceptionHandler() {
		ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
			protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod,
					Exception exception) {
				Method method = new ExceptionHandlerMethodResolver(GlobalExceptionHandler.class)
						.resolveMethod(exception);
				return new ServletInvocableHandlerMethod(exceptionHandler, method);
			}
		};
		exceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		exceptionResolver.afterPropertiesSet();
		return exceptionResolver;
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
		String json = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
	}

	@Test
	public void testCreateUserInvalidEmail() throws Exception {
		String json = " {\"email\": \"emailemail.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testCreateInvalidUsername() throws Exception {
		String json = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joa*o001\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.key", equalTo("error.invalid.username")));
	}

	@Test
	public void testCreateUserInvalidPassword() throws Exception {
		String json = " {\"email\": \"email@email.com\",\"password\": \"se123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.key", equalTo("error.invalid.password")));
	}

	@Test
	public void testCreateUserInvalidCompleteName() throws Exception {
		String json = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao1 da Silva\",\"username\": \"joao001\"}";
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.key", equalTo("error.invalid.name")));
	}

	@Test
	public void testCreateUserDuplicatedEmail() throws Exception {
		String json = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
		doThrow(new DuplicatedEmailException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_EMAIL))).when(userService).createUser(any());
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.key", equalTo("error.resource.email.duplicated")));
	}

	@Test
	public void testCreateUserDuplicatedUsername() throws Exception {
		String json = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
		doThrow(new DuplicatedUsernameException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_USERNAME))).when(userService).createUser(any());
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isConflict())
			.andExpect(jsonPath("$.key", equalTo("error.resource.username.duplicated")));
	}

	@Test
	public void testCreateUserUnrecheableEmailAddress() throws Exception {
		String json = " {\"email\": \"email@email.com\",\"password\": \"senha123\",\"completeName\": \"Joao da Silva\",\"username\": \"joao001\"}";
		doThrow(new UnreachableEmailException(new ErrorResponse(ErrorMessage.Inexistent.UNREACHABLE_EMAIL))).when(userService).createUser(any());
		mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.key", equalTo("error.inexistent.email.smtp")));
	}

	@Test
	public void testRegisterUserSuccess() throws Exception {
		mockMvc.perform(post("/users/{email}/register/{token}".replace("{email}", "foo@bar").replace("{token}", "123456")))
		.andExpect(status().isOk());
	}

	@Test
	public void testRegisterUserInvalidEmail() throws Exception {
		mockMvc.perform(post("/users/{email}/register/{token}".replace("{email}", "foobar").replace("{token}", "123456")))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}

	@Test
	public void testRegisterUserInvalidToken() throws Exception {
		mockMvc.perform(post("/users/{email}/register/{token}".replace("{email}", "foo@bar").replace("{token}", "13456")))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.key", equalTo("error.invalid.token")));
	}

	@Test
	public void testRegisterUserIncorrectToken() {
		
	}

	@Test
	public void testRegisterUserInexistentEmail() {

	}

	@Test
	public void testAuthenticateUserSuccess() {

	}

	@Test
	public void testAuthenticateUserInvalidEmail() {

	}

	@Test
	public void testAuthenticateUserInvalidPassword() {

	}

	@Test
	public void testAuthenticateUserIncorrectData() {

	}

	@Test
	public void testAuthenticateUserInexistentEmail() {

	}

	@Test
	public void testValidateEmailAndUsernameSuccess() {

	}

	@Test
	public void testValidateEmailAndUsernameInvalidEmail() {

	}

	@Test
	public void testValidateEmailAndUsernameInvalidUsername() {

	}

	@Test
	public void testValidateEmailAndUsernameInexistentEmail() {

	}

	@Test
	public void testValidateEmailAndUsernameInexistentUsername() {

	}

	@Test
	public void testValidateEmailAndUsernameIncorrectData() {

	}

	@Test
	public void testChangeUserPasswordSuccess() {

	}

	@Test
	public void testChangeUserPasswordInvalidEmail() {

	}

	@Test
	public void testChangeUserPasswordInvalidNewPassword() {

	}

	@Test
	public void testChangeUserPasswordNewPasswordAndConfirmationMismatch() {

	}

	@Test
	public void testChangeUserPasswordInvalidToken() {

	}

	@Test
	public void testChangeUserPasswordIncorrectToken() {

	}

	@Test
	public void testChangeUserPasswordInexistentEmail() {

	}
}