package com.ole.rentalstore.ws.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
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

import com.ole.rentalstore.business.service.TokenService;
import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentEmailOnDatabaseException;

@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(TokenController.class)
public class TokenControllerTest extends BaseControllerTest {

	@Mock
	private TokenService tokenService;

	@InjectMocks
	private TokenController tokenController;
	
	@Before
	public void setUp() {
		setUp(tokenController);
	}
	
	@Test
	public void testProcessTokenSuccess() throws Exception {
		mockMvc.perform(put("/tokens/{email}".replace("{email}", "foo@bar")))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testProcessTokenInvalidEmail() throws Exception {
		mockMvc.perform(put("/tokens/{email}".replace("{email}", "foobar")))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.key", equalTo("error.invalid.email")));
	}
	
	@Test
	public void testProcessTokenInexistentEmail() throws Exception {
		doThrow(new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL))).when(tokenService).processToken(anyString());
		mockMvc.perform(put("/tokens/{email}".replace("{email}", "foo@bar")))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.key", equalTo("error.inexistent.email")));
	}
}