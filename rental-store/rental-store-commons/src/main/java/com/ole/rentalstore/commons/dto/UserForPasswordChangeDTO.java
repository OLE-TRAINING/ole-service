package com.ole.rentalstore.commons.dto;

public class UserForPasswordChangeDTO {

	private String email;
	private String confirmationToken;
	private String newPassword;
	private String newPasswordConfirmation;

	public UserForPasswordChangeDTO() {
		
	}
	
	public UserForPasswordChangeDTO(String email, String confirmationToken, String newPassword,
			String newPasswordConfirmation) {
		this.email = email;
		this.confirmationToken = confirmationToken;
		this.newPassword = newPassword;
		this.newPasswordConfirmation = newPasswordConfirmation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordConfirmation() {
		return newPasswordConfirmation;
	}

	public void setNewPasswordConfirmation(String newPasswordConfirmation) {
		this.newPasswordConfirmation = newPasswordConfirmation;
	}
}