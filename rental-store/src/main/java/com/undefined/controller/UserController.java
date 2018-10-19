package com.undefined.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.undefined.business.UserService;
import com.undefined.error.ErrorMessage;
import com.undefined.error.ErrorResponse;
import com.undefined.model.entities.User;
import com.undefined.model.exceptions.InvalidEmailException;
import com.undefined.model.exceptions.NullFieldException;
import com.undefined.model.utils.EmailValidator;
import com.undefined.model.utils.UserModelator;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users/{email}")
	public ResponseEntity<User> getUserThroughEmail(@PathVariable("email") String email) {
		if (StringUtils.isEmpty(email)) {
			throw new NullFieldException(new ErrorResponse(ErrorMessage.Validation.NULL_OR_EMPTY_FIELD));
		}
		if (!EmailValidator.validateEmail(email)) {
			throw new InvalidEmailException(new ErrorResponse(ErrorMessage.Validation.INVALID_EMAIL));
		}
		Optional<User> user = userService.findUserByEmail(email);
		if (!user.isPresent()) {
			return ResponseEntity.ok(UserModelator.getUnexistingUser());	
		} else {
			return ResponseEntity.ok(user.get());
		}
	}
}