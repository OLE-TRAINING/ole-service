package com.ole.rentalstore.business.mapper;

import org.mapstruct.Mapper;

import com.ole.rentalstore.commons.dto.UserDTO;
import com.ole.rentalstore.model.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO userToUserDTO(User user);
	
	User userDTOToUser(UserDTO userDTO);
}