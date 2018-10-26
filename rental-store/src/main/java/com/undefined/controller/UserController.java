package com.undefined.controller;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.undefined.business.TokenService;
import com.undefined.business.UserService;
import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.commons.exceptions.DuplicatedEmailException;
import com.undefined.commons.exceptions.DuplicatedUsernameException;
import com.undefined.commons.exceptions.InvalidEmailException;
import com.undefined.commons.exceptions.InvalidUserException;
import com.undefined.commons.exceptions.NullFieldException;
import com.undefined.commons.utils.UserModelator;
import com.undefined.commons.validation.NullFieldValidator;
import com.undefined.commons.validation.UserDataValidator;
import com.undefined.commons.validation.UserValidator;
import com.undefined.model.entities.User;

@RestController
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserDataValidator userDataValidator;
	
	@GetMapping("/users/{email}")
	public ResponseEntity<User> getUserThroughEmail(@PathVariable("email") String email) {
		if (StringUtils.isEmpty(email)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!UserValidator.validateEmail(email)) {
			throw new InvalidEmailException(new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL));
		}
		Optional<User> user = userService.findUserByEmail(email);
		if (!user.isPresent()) {
			return ResponseEntity.ok(UserModelator.getUnexistingUser());	
		} else {
			return ResponseEntity.ok(user.get());
		}
	}
	
	@PostMapping("/users")
	public void processUser(@RequestBody User user) {
		try {
			NullFieldValidator.checkForNullFields(user);
		} catch (NullFieldException ex) {
			if (!ex.getFieldName().equals("registrationStatus") && !ex.getFieldName().equals("confirmationToken")) {
				throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
			}
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage());
		}
		ErrorResponse error = UserValidator.validateUser(user);
		if (error != null) {
			throw new InvalidUserException(error);
		}
		if (userDataValidator.emailAlreadyExists(user.getEmail())) {
			throw new DuplicatedEmailException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_EMAIL));
		}
		if (userDataValidator.usernameAlreadyExists(user.getUsername())) {
			throw new DuplicatedUsernameException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_USERNAME));
		}
		userService.insertUser(user);
		String token = tokenService.generateToken();
		tokenService.vinculateTokenToUser(user.getEmail(), token);
		tokenService.sendTokenToEmail(user.getEmail(), token, "Cadastro de usuário");
	}
}