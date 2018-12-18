package com.ole.rentalstore.business.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Test.None;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.ArgumentMatchers.any;

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
	
	/*private SimpleMailMessage getMailMessage() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setText("text");
		mailMessage.setSubject("subject");
		mailMessage.setTo("dest");
		return mailMessage;
	}*/
	
	@Test(expected = UnreachableEmailException.class)
	public void testSendTokenToEmailUnreachableEmail() {
		doThrow(new MailSendException("Unrecheable!")).when(mailSender).send((SimpleMailMessage)any());
		tokenService.sendTokenToEmail("dest", "subject", "subj");
	}
	
	@Test(expected = None.class)
	public void testSendTokenToEmailSuccess() {
		tokenService.sendTokenToEmail("dest", "123456", "subj");
	}
}