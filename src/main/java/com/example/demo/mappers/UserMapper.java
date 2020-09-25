package com.example.demo.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.dtos.UserMsDto;
import com.example.demo.entities.User;

@Mapper(componentModel="Spring")
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	@Mapping(source = "email", target="emailaddress")
	UserMsDto userToUserMsDto(User user);
	
	List<UserMsDto> usersToUserDtos(List<User> users);
}
