package com.example.demo.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UserMmDto;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) throws UserNotFound
	{
			Optional<User> userOptional =  userService.getUserById(id);
			if(!userOptional.isPresent())
			{
				throw new UserNotFound("User not found");
			}
			
			User user = userOptional.get();
			
		UserMmDto userDto = modelMapper.map(user, UserMmDto.class);
		return userDto;
	}
}
