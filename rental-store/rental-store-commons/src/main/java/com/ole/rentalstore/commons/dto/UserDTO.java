package com.ole.rentalstore.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ole.rentalstore.commons.utils.RegistrationStatus;

public class UserDTO {

	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private String completeName;
	private String username;
	private RegistrationStatus registrationStatus = RegistrationStatus.PENDING;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String confirmationToken;
	
	public UserDTO() {}
	
	public UserDTO(String email, String password, String completeName, String username) {
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
	
	@JsonIgnore
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

	@JsonIgnore
	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
}