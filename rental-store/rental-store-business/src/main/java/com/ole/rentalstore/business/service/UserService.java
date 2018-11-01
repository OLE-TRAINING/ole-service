package com.ole.rentalstore.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ole.rentalstore.business.mapper.UserMapper;
import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.dto.UserForPasswordChangeDTO;
import com.ole.rentalstore.commons.error.ErrorMessage;
import com.ole.rentalstore.commons.error.ErrorResponse;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedEmailException;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedUsernameException;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentEmailOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentUsernameOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenEmailAndPasswordException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenEmailAndUsernameException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenTokenAndEmailException;
import com.ole.rentalstore.commons.utils.PasswordModelator;
import com.ole.rentalstore.commons.utils.RegistrationStatus;
import com.ole.rentalstore.commons.utils.UserModelator;
import com.ole.rentalstore.model.entities.User;
import com.ole.rentalstore.model.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserMapper userMapper;
	
	public UserDTO getUserThroughEmail(String email) {
		String emailCaseIgnored = UserModelator.getStringIgnoringCase(email);
		if (!isEmailOnDatabase(emailCaseIgnored)) {
			return UserModelator.getInexistingUser();	
		} else {
			return userMapper.userToUserDTO(findUserByEmail(emailCaseIgnored).get());
		}
	}
	
	public void createUser(UserDTO user) {
		user.setEmail(UserModelator.getStringIgnoringCase(user.getEmail()));
		if (isEmailOnDatabase(user.getEmail())) {
			throw new DuplicatedEmailException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_EMAIL));
		}
		if (isUsernameOnDatabase(user.getUsername())) {
			throw new DuplicatedUsernameException(new ErrorResponse(ErrorMessage.Resource.DUPLICATED_USERNAME));
		}
		insertUser(userMapper.userDTOToUser(user));
		tokenService.processToken(user.getEmail());
	}
	
	@Transactional
	public void registerUser(String email, String token) {
		String emailCaseIgnored = UserModelator.getStringIgnoringCase(email);
		if (!isEmailOnDatabase(emailCaseIgnored)) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		if (!isTokenAssociatedToEmail(emailCaseIgnored, token)) {
			throw new IncorrectAssociationBetweenTokenAndEmailException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_TOKEN));
		}
		setUserAsRegistered(emailCaseIgnored);
		tokenService.clearToken(emailCaseIgnored);
	}
	
	public void authenticateUser(UserDTO user) {
		user.setEmail(UserModelator.getStringIgnoringCase(user.getEmail()));
		if (!isEmailOnDatabase(user.getEmail())) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		String password = PasswordModelator.getEncryptedPassword(user.getPassword());
		if (!isPasswordAssociatedToEmail(user.getEmail(), password)) {
			throw new IncorrectAssociationBetweenEmailAndPasswordException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_PASSWORD));
		}
	}
	
	public void validateEmailAndUsername(UserDTO user) {
		user.setEmail(UserModelator.getStringIgnoringCase(user.getEmail()));
		if (!isEmailOnDatabase(user.getEmail())) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		if (!isUsernameOnDatabase(user.getUsername())) {
			throw new InexistentUsernameOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_USERNAME));
		}
		if (!isUsernameAssociatedToEmail(user.getEmail(), user.getUsername())) {
			throw new IncorrectAssociationBetweenEmailAndUsernameException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_USERNAME));
		}
	}
	
	@Transactional
	public void changeUserPassword(UserForPasswordChangeDTO user) {
		user.setEmail(UserModelator.getStringIgnoringCase(user.getEmail()));
		if (!isEmailOnDatabase(user.getEmail())) {
			throw new InexistentEmailOnDatabaseException(new ErrorResponse(ErrorMessage.Inexistent.INEXISTENT_EMAIL));
		}
		if (!isTokenAssociatedToEmail(user.getEmail(), user.getConfirmationToken())) {
			throw new IncorrectAssociationBetweenTokenAndEmailException(new ErrorResponse(ErrorMessage.Unauthenticated.INCORRECT_TOKEN));
		}
		user.setNewPassword(PasswordModelator.getEncryptedPassword(user.getNewPassword()));
		changeUserPassword(user.getNewPassword(), user.getEmail());
		tokenService.clearToken(user.getEmail());
	}
	
	public boolean isTokenAssociatedToEmail(String email, String token) {
		return findUserByEmail(email).get().getConfirmationToken().equals(token);
	}
	
	public boolean isPasswordAssociatedToEmail(String email, String password) {
		return findUserByEmail(email).get().getPassword().equals(password);
	}
	
	public boolean isUsernameAssociatedToEmail(String email, String username) {
		return findUserByEmail(email).get().getUsername().equals(username);
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
	
	@Transactional
	public void changeUserPassword(String newPassword, String email) {
		userRepository.setPasswordByEmail(newPassword, email);;
	}
	
	public boolean isEmailOnDatabase(String email) {
		return findUserByEmail(email).isPresent();
	}
	
	public boolean isUsernameOnDatabase(String username) {
		return findUserByUsername(username).isPresent();
	}
}