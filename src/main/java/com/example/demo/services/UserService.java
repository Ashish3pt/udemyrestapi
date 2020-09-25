package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.UserExistsException;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.repositories.UserRepository;
import java.util.Optional;
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException
	{
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser!= null)
		{
			throw new UserExistsException("User already exists");
		}
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id) throws UserNotFound
	{
		Optional<User> user =  userRepository.findById(id);
		if(!user.isPresent())
		{
			throw new UserNotFound("User Not found in Repo");
		}
		return user;
	}
	
	public User updateUserById(Long id, User user) throws UserNotFound
	{
		Optional<User> optionalUser =  userRepository.findById(id);
		if(!optionalUser.isPresent())
		{
			throw new UserNotFound("User Not found in Repo. Provide Correct User ID");
		}
		
		user.setUserid(id);
		return userRepository.save(user);
		
	}
	/*public void delete(Long id)
	{
		Optional<User> optionalUser =  userRepository.findById(id);
		if(!optionalUser.isPresent())
		{
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in Repo. Provide Correct User ID");
		}
		
		userRepository.deleteById(id);
	}*/
	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
}
