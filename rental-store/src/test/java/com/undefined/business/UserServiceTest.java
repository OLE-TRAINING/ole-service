package com.undefined.business;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.undefined.RentalStoreApplication;
import com.undefined.model.entities.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RentalStoreApplication.class, DataSourceAutoConfiguration.class})
@TestPropertySource("classpath:application.properties")
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testFindUserByEmail() {
		Optional<User> user = userService.findUserByEmail("exemplo@exemplo.com");
		assertThat(user.isPresent()).isEqualTo(true);
	}
	
	@Test
	public void testFindUserByEmailFalse() {
		Optional<User> user = userService.findUserByEmail("exemplo@naoexiste.com");
		assertThat(user.isPresent()).isEqualTo(false);
	}

	@Test
	public void testFindUserByUsername() {
		Optional<User> user = userService.findUserByUsername("fulano1");
		assertThat(user.isPresent()).isEqualTo(true);
	}
	
	@Test
	public void testFindUserByUsernameFalse() {
		Optional<User> user = userService.findUserByUsername("noexiste");
		assertThat(user.isPresent()).isEqualTo(false);
	}

	@Test
	@Rollback
	public void testInsertUser() {
		User user = new User();
		user.setCompleteName("Rodrigo Paiva da Silva");
		user.setEmail("rodrigo@gmail.com");
		user.setPassword("rod");
		user.setUsername("rodrigo256");
		userService.insertUser(user);
		
		Optional<User> optUser = userService.findUserByEmail("rodrigo@gmail.com");
		assertThat(optUser.isPresent()).isEqualTo(true);
	}
}
