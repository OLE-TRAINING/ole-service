package com.undefined.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.undefined.RentalStoreApplication;
import com.undefined.model.entities.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RentalStoreApplication.class, DataSourceAutoConfiguration.class})
@TestPropertySource("classpath:application.properties")
public class TokenServiceTest {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testVinculateTokenToUser() {
		String token = tokenService.generateToken();
		tokenService.vinculateTokenToUser("bsmonteiro99@gmail.com", token);
		Optional<User> user = userService.findUserByEmail("bsmonteiro99@gmail.com");
		assertThat(user.get().getConfirmationToken()).isEqualTo(token);
	}
}