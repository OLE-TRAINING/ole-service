package com.ole.rentalstore.ws.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ole.rentalstore.business.configuration.BusinessConfiguration;
import com.ole.rentalstore.business.service.UserService;
import com.ole.rentalstore.commons.dto.UserDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@Import({ BusinessConfiguration.class })
public class UserControllerTest {

	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	@Autowired
	UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testGetUserThroughEmail() throws Exception {
		UserDTO user = new UserDTO("email@email.com", "senha123", "João da Silva", "username1");
		when(userService.getUserThroughEmail(anyString())).thenReturn(user);
		
		mockMvc.perform(get("users/{email}".replace("{email}", "anyString"))).andExpect(status().isOk());
	}
}