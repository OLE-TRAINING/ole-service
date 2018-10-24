package com.undefined.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource("classpath:java-mail.properties")
public class TokenController {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Environment env;
	
	@GetMapping("/token/{email}")
	public void sendTokenToEmail(@PathVariable("email") String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("ola");
		message.setTo(email);
		message.setFrom(env.getProperty("spring.mail.username"));
		
		mailSender.send(message);
	}
}