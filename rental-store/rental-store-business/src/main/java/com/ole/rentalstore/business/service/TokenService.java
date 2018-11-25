package com.ole.rentalstore.business.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentEmailOnDatabaseException;
import com.ole.rentalstore.commons.utils.UserModelator;
import com.ole.rentalstore.model.repositories.UserRepository;

@Service
@PropertySource("classpath:java-mail.properties")
public class TokenService {
	
	private static final String template = "Token para confirmar operação: %s\n";
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private UserRepository userRepository;

	public String generateToken() {
		return RandomStringUtils.randomAlphanumeric(6);
	}
	
	@Transactional
	public void vinculateTokenToUser(String destinatary, String token) {
		userRepository.setConfirmationTokenByEmail(token, destinatary);
	}
	
	@Transactional
	public void clearToken(String email) {
		userRepository.setConfirmationTokenNullByEmail(email);
	}
	
	public void sendTokenToEmail(String destinatary, String token, String subject) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setText(String.format(template, token));
		mailMessage.setSubject(subject);
		mailMessage.setTo(destinatary);
		mailSender.send(mailMessage);
	}
	
	@Transactional
	public void processToken(String email) {
		String emailCaseIgnored = UserModelator.getStringLowerCase(email);
		if (!userService.isEmailOnDatabase(emailCaseIgnored)) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		String token = generateToken();
		vinculateTokenToUser(emailCaseIgnored, token);
		sendTokenToEmail(emailCaseIgnored, token, "Confirmar Operação");
	}
}