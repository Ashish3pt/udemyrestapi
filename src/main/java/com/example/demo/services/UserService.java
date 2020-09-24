package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
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
	
	public User createUser(User user)
	{
		return userRepository.save(user);
	}
	
	public Optional<User> getUserById(Long id)
	{
		return userRepository.findById(id);
	}
	
	public User updateUserById(Long id, User user)
	{
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
}
