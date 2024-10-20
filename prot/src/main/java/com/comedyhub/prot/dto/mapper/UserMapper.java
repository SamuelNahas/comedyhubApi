package com.comedyhub.prot.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.comedyhub.prot.dto.UserDtoCreate;
import com.comedyhub.prot.dto.UserDtoResponse;
import com.comedyhub.prot.model.User;

@Component
public class UserMapper {

    public static User toEntity(UserDtoCreate dto) {
		User user = new User();
		user.setPassword(dto.getPassword());
		user.setUsername(dto.getUsername().toUpperCase());
		return user;
	}
	
	public static User toEntity(UserDtoResponse dto) {
		User user = new User();
		user.setUsername(dto.getUsername().toUpperCase());
		user.setId(dto.getId());
		return user;
	}
	
	public static UserDtoResponse toDTO(User user) {
		UserDtoResponse dto = new UserDtoResponse();
		dto.setId(user.getId());
		dto.setPassword(user.getPassword());
		dto.setUsername(user.getUsername().toUpperCase());
		return dto;
	}

	public static List<UserDtoResponse> toDTO(List<User> users) {
		return users.stream().map(user -> toDTO(user)).collect(Collectors.toList());
	}
}
