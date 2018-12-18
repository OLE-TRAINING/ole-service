package com.ole.rentalstore.business.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.ole.rentalstore.business.mapper.UserMapper;
import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.commons.dto.UserForPasswordChangeDTO;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedEmailException;
import com.ole.rentalstore.commons.exceptions.conflict.DuplicatedUsernameException;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentEmailOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.not_found.InexistentUsernameOnDatabaseException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenEmailAndPasswordException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenEmailAndUsernameException;
import com.ole.rentalstore.commons.exceptions.unauthorized.IncorrectAssociationBetweenTokenAndEmailException;
import com.ole.rentalstore.commons.utils.RegistrationStatus;
import com.ole.rentalstore.model.entities.User;
import com.ole.rentalstore.model.repositories.UserRepository;

public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private TokenService tokenService;
	
	@Spy
	private UserMapper userMapper = Mappers.getMapper(UserMapper.class);
	
	private Optional<User> optionalUser = Optional.of(new User("foo@bar", "senha123", "Bruno Monteiro", "brunomonteiro1"));
	private UserDTO userDto = new UserDTO("foo@bar", "senha123", "Bruno Monteiro", "brunomonteiro1");
	private UserForPasswordChangeDTO userPasswordChange = new UserForPasswordChangeDTO("foo@bar", "123456", "newpass1", "newpass1");
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUserThroughEmailExistentEmail() {
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		UserDTO actualUserDTO = userService.getUserThroughEmail("foo@bar");
		
		assertThat(actualUserDTO.getEmail()).isEqualTo(userDto.getEmail());
		assertThat(actualUserDTO.getPassword()).isEqualTo(userDto.getPassword());
		assertThat(actualUserDTO.getCompleteName()).isEqualTo(userDto.getCompleteName());
		assertThat(actualUserDTO.getUsername()).isEqualTo(userDto.getUsername());
	}
	
	@Test
	public void testGetUserThroughEmailInexistentEmail() {
		when(userRepo.findById(anyString())).thenReturn(Optional.empty());
		UserDTO actualUserDTO = userService.getUserThroughEmail("foo@bar");
		assertThat(actualUserDTO.getRegistrationStatus()).isEqualTo(RegistrationStatus.INEXISTENT);
	}
	
	@Test(expected = DuplicatedEmailException.class)
	public void testCreateUserEmailAlreadyExists() {
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.createUser(userDto);
	}
	
	@Test(expected = DuplicatedUsernameException.class)
	public void testCreateUserUsernameAlreadyExists() {
		when(userRepo.findByUsername(anyString())).thenReturn(optionalUser);
		userService.createUser(userDto);
	}
	
	@Test(expected = None.class)
	public void testCreateUserSuccess() {
		when(userRepo.findById(anyString())).thenReturn(Optional.empty());
		when(userRepo.findByUsername(anyString())).thenReturn(Optional.empty());
		userService.createUser(userDto);
	}
	
	@Test(expected = InexistentEmailOnDatabaseException.class)
	public void testRegisterUserInexistentEmail() {
		when(userRepo.findById(anyString())).thenReturn(Optional.empty());
		userService.registerUser("foo@bar", "123456");
	}
	
	@Test(expected = IncorrectAssociationBetweenTokenAndEmailException.class)
	public void testRegisterUserIncorrectToken() {
		optionalUser.get().setConfirmationToken("abcdef");
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.registerUser("foo@bar", "123456"); // token 123456 != abcdef
	}
	
	@Test(expected = None.class)
	public void testRegisterUserSuccess() {
		optionalUser.get().setConfirmationToken("abcdef");
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.registerUser("foo@bar", "abcdef");
	}
	
	@Test(expected = InexistentEmailOnDatabaseException.class)
	public void testAuthenticateUserInexistentEmail() {
		when(userRepo.findById(anyString())).thenReturn(Optional.empty());
		userService.authenticateUser(userDto);
	}
	
	@Test(expected = IncorrectAssociationBetweenEmailAndPasswordException.class)
	public void testAuthenticateUserIncorrectPassword() {
		userDto.setPassword("pass123"); // original password: senha123
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.authenticateUser(userDto);
	}
	
	@Test(expected = None.class)
	public void testAuthenticateUserSuccess() {
		optionalUser.get().setPassword("e7d80ffeefa212b7c5c55700e4f7193e"); //md5 convertion from senha123
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.authenticateUser(userDto);
	}
	
	@Test(expected = InexistentEmailOnDatabaseException.class)
	public void testValidateEmailAndUsernameInexistentEmail() {
		when(userRepo.findById(anyString())).thenReturn(Optional.empty());
		userService.validateEmailAndUsername(userDto);
	}
	
	@Test(expected = InexistentUsernameOnDatabaseException.class)
	public void testValidateEmailAndUsernameInexistentUsername() {
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		when(userRepo.findByUsername(anyString())).thenReturn(Optional.empty());
		userService.validateEmailAndUsername(userDto);
	}
	
	@Test(expected = IncorrectAssociationBetweenEmailAndUsernameException.class)
	public void testValidateEmailAndUsernameIncorrectData() {
		optionalUser.get().setUsername("username"); // original username: brunomonteiro1 
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		when(userRepo.findByUsername(anyString())).thenReturn(optionalUser);
		userService.validateEmailAndUsername(userDto);
	}
	
	@Test(expected = None.class)
	public void testValidateEmailAndUsernameSuccess() {
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		when(userRepo.findByUsername(anyString())).thenReturn(optionalUser);
		userService.validateEmailAndUsername(userDto);
	}
	
	@Test(expected = InexistentEmailOnDatabaseException.class)
	public void testChangeUserPasswordInexistentEmail() {
		when(userRepo.findById(anyString())).thenReturn(Optional.empty());
		userService.changeUserPassword(userPasswordChange);
	}
	
	@Test(expected = IncorrectAssociationBetweenTokenAndEmailException.class)
	public void testChangeUserPasswordIncorrectToken() {
		optionalUser.get().setConfirmationToken("abcdef"); // original is 123456
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.changeUserPassword(userPasswordChange);
	}
	
	@Test(expected = None.class)
	public void testChangeUserPasswordSuccess() {
		optionalUser.get().setConfirmationToken("123456");
		when(userRepo.findById(anyString())).thenReturn(optionalUser);
		userService.changeUserPassword(userPasswordChange);
	}
}