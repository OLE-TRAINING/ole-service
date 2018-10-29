package com.undefined.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.undefined.commons.error.ErrorMessage;
import com.undefined.commons.error.ErrorResponse;
import com.undefined.commons.exceptions.DuplicatedEmailException;
import com.undefined.commons.exceptions.DuplicatedUsernameException;
import com.undefined.commons.exceptions.IncorrectAssociationBetweenEmailAndPasswordException;
import com.undefined.commons.exceptions.IncorrectAssociationBetweenTokenAndEmailException;
import com.undefined.commons.exceptions.InexistentEmailOnDatabaseException;
import com.undefined.commons.utils.PasswordModelator;
import com.undefined.commons.utils.RegistrationStatus;
import com.undefined.commons.utils.UserModelator;
import com.undefined.model.entities.User;
import com.undefined.model.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	
	public User getUserThroughEmail(String email) {
		Optional<User> user = findUserByEmail(email);
		if (!user.isPresent()) {
			return UserModelator.getUnexistingUser();	
		} else {
			return user.get();
		}
	}
	
	public void createUser(User user) {
		if (isEmailOnDatabase(user.getEmail())) {
			throw new DuplicatedEmailException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_EMAIL));
		}
		if (isUsernameOnDatabase(user.getUsername())) {
			throw new DuplicatedUsernameException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_USERNAME));
		}
		insertUser(user);
		tokenService.processToken(user.getEmail());
	}
	
	@Transactional
	public void registerUser(String email, String token) {
		if (!isEmailOnDatabase(email)) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		if (!isTokenAssociatedToEmailCorrect(email, token)) {
			throw new IncorrectAssociationBetweenTokenAndEmailException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_TOKEN));
		}
		setUserAsRegistered(email);
	}
	
	public void authenticateUser(User user) {
		if (!isEmailOnDatabase(user.getEmail())) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		String password = PasswordModelator.getEncryptedPassword(user.getPassword());
		if (!isPasswordAssociatedToEmailCorrect(user.getEmail(), password)) {
			throw new IncorrectAssociationBetweenEmailAndPasswordException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_PASSWORD));
		}
	}
	
	public boolean isTokenAssociatedToEmailCorrect(String email, String token) {
		return findUserByEmail(email).get().getConfirmationToken().equals(token);
	}
	
	public boolean isPasswordAssociatedToEmailCorrect(String email, String password) {
		return findUserByEmail(email).get().getPassword().equals(password);
	}
	
	public Optional<User> findUserByEmail(String email) {
		return userRepository.findById(email);
	}
	
	public Optional<User> findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	public void insertUser(User user) {
		user.setPassword(PasswordModelator.getEncryptedPassword(user.getPassword()));
		userRepository.save(user);
	}
	
	@Transactional
	public void setUserAsRegistered(String email) {
		userRepository.setRegistrationStatusByEmail(RegistrationStatus.REGISTERED, email);
	}
	
	public boolean isEmailOnDatabase(String email) {
		return findUserByEmail(email).isPresent();
	}
	
	public boolean isUsernameOnDatabase(String username) {
		return findUserByUsername(username).isPresent();
	}
}