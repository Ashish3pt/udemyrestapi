package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.exceptions.UserNameNotFoundException;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="User Management restful Services", value="UserController")
@RestController
@Validated
@RequestMapping(value="/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="Retrieve List of users")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<Void> createUser( @ApiParam("User information for a new user to be created.") @Valid @RequestBody User user, UriComponentsBuilder builder)
	{
		try {
		userService.createUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getUserid()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		catch(UserExistsException ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id)
	{
		try
		{
			return userService.getUserById(id);
		}
		catch(UserNotFound ex)
		{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user)
	{
		try {
			
		return userService.updateUserById(id, user);
		}
		catch(UserNotFound ex)
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping("/getuser/{username}")
	public User getUserByUsername(@PathVariable("username") String name) throws UserNameNotFoundException
	{
		User user = userService.getUserByUsername(name);
		if(user==null)
		{
			throw new UserNameNotFoundException("Username: "+name+"Not Found in us1er repo");
			
		}
		return user;
	}
}


