package com.ole.rentalstore.business.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.ole.rentalstore.commons.exceptions.not_found.InexistentEmailOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.not_found.UnreachableEmailException;
import com.ole.rentalstore.model.repositories.UserRepository;

public class TokenServiceTest {

	@Mock
	private UserService userService;
	
	@Mock
	private JavaMailSender mailSender;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private TokenService tokenService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGenerateToken() {
		String token = tokenService.generateToken();
		assertThat(token.length()).isEqualTo(6);
		assertThat(token).matches("^[a-zA-Z0-9]*$");
	}
	
	@Test(expected = UnreachableEmailException.class)
	public void testSendTokenToEmailUnreachableEmail() {
		doThrow(new MailSendException("Unrecheable!")).when(mailSender).send((SimpleMailMessage)any());
		tokenService.sendTokenToEmail("dest", "subject", "subj");
	}
	
	@Test(expected = None.class)
	public void testSendTokenToEmailSuccess() {
		tokenService.sendTokenToEmail("dest", "123456", "subj");
	}
	
	@Test(expected = InexistentEmailOnDatabaseException.class)
	public void testProcessTokenInexistentEmail() {
		when(userService.isEmailOnDatabase(anyString())).thenReturn(false);
		tokenService.processToken("foo@bar");
	}
	
	@Test(expected = None.class)
	public void testProcessTokenSuccess() {
		when(userService.isEmailOnDatabase(anyString())).thenReturn(true);
		tokenService.processToken("foo@bar");
	}
}