package com.undefined.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.undefined.business.TokenService;
import com.undefined.business.UserService;
import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.commons.exceptions.IncorrectAssociationBetweenTokenAndEmailException;
import com.undefined.commons.exceptions.InexistentEmailOnDatabaseException;
import com.undefined.commons.exceptions.InvalidEmailException;
import com.undefined.commons.exceptions.InvalidTokenException;
import com.undefined.commons.exceptions.NullFieldException;
import com.undefined.commons.validation.TokenValidator;
import com.undefined.commons.validation.UserValidator;
import com.undefined.model.entities.User;

@RestController
public class TokenController {

	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	
	@PutMapping("/tokens/{email}")
	public void processToken(@PathVariable("email") String email) {
		if (StringUtils.isEmpty(email)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!UserValidator.validateEmail(email)) {
			throw new InvalidEmailException(new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL));
		}
		Optional<User> user = userService.findUserByEmail(email);
		if (!user.isPresent()) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		String token = tokenService.generateToken();
		tokenService.vinculateTokenToUser(user.get().getEmail(), token);
		tokenService.sendTokenToEmail(user.get().getEmail(), token, "Cadastro de usuário");
	}
	
	@PostMapping("/tokens/{email}/{token}")
	public void validateToken(@PathVariable("email") String email, @PathVariable("token") String token) {
		if (StringUtils.isEmpty(email) || StringUtils.isEmpty(token)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!UserValidator.validateEmail(email)) {
			throw new InvalidEmailException(new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL));
		}
		if (!TokenValidator.validateToken(token)) {
			throw new InvalidTokenException(new ErrorResponse(ErrorMessage.Validation.INVALID_TOKEN));
		}
		Optional<User> user = userService.findUserByEmail(email);
		if (!user.isPresent()) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		if (!user.get().getConfirmationToken().equals(token)) {
			throw new IncorrectAssociationBetweenTokenAndEmailException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_TOKEN));
		}
		userService.registerUser(email);
	}
}