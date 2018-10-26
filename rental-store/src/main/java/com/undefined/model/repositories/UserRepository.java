package com.undefined.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.undefined.commons.utils.RegistrationStatus;
import com.undefined.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query("update User u set u.confirmationToken = ?1 where u.email = ?2")
	void setConfirmationTokenByEmail(String confirmationToken, String email);
	
	@Modifying
	@Query("update User u set u.registrationStatus = ?1 where u.email = ?2")
	void setRegistrationStatusByEmail(RegistrationStatus registrationStatus, String email);
}