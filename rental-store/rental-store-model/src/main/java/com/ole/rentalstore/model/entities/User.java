package com.ole.rentalstore.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ole.rentalstore.commons.utils.RegistrationStatus;

@Entity
@Table(name = "users")
public class User {

	@Id
	private String email;
	@Column
	//
	private String password;
	@Column(name = "complete_name")
	private String completeName;
	@Column
	private String username;
	@Enumerated(EnumType.STRING)
	@Column(name = "registration_status")
	private RegistrationStatus registrationStatus = RegistrationStatus.PENDING;
	@Column(name = "confirmation_token")
	//
	private String confirmationToken = null;

	public User() {

	}

	public User(String email, String password, String completeName, String username) {
		this.email = email;
		this.password = password;
		this.completeName = completeName;
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	//
	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
}