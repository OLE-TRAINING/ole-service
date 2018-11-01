package com.ole.rentalstore.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ole.rentalstore.commons.utils.RegistrationStatus;
import com.ole.rentalstore.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query("update User u set u.confirmationToken = ?1 where u.email = ?2")
	void setConfirmationTokenByEmail(String confirmationToken, String email);
	
	@Modifying
	@Query("update User u set u.registrationStatus = ?1 where u.email = ?2")
	void setRegistrationStatusByEmail(RegistrationStatus registrationStatus, String email);
	
	@Modifying
	@Query("update User u set u.password = ?1 where u.email = ?2")
	void setPasswordByEmail(String password, String email);
	
	@Modifying
	@Query("update User u set u.confirmationToken = null where u.email = ?1")
	void setConfirmationTokenNullByEmail(String email);
}