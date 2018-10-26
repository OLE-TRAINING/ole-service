package com.undefined.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.undefined.commons.utils.PasswordModelator;
import com.undefined.commons.utils.RegistrationStatus;
import com.undefined.model.entities.User;
import com.undefined.model.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

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
	public void registerUser(String email) {
		userRepository.setRegistrationStatusByEmail(RegistrationStatus.REGISTERED, email);
	}
}