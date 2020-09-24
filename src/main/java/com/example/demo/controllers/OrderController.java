package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;



@RestController
@RequestMapping(value="/users")
public class OrderController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrder(@PathVariable Long userid) throws UserNotFound	{
		Optional<com.example.demo.entities.User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFound("User Not Found");
		
		return userOptional.get().getOrders();
		
	}
	
	@PostMapping("{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFound
	{
		Optional<User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFound("User Not Found");
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
		
	}
	
	@GetMapping("/order/{id}")
	public Optional<Order> orberby(@PathVariable Long id)
	{
		return orderRepository.findById(id);
	}
	
}
