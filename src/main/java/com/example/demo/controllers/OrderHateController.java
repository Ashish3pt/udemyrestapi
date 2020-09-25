package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Order;
import com.example.demo.exceptions.UserNotFound;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping("/hateoas/users")
public class OrderHateController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrder(@PathVariable Long userid) throws UserNotFound	{
		Optional<com.example.demo.entities.User> userOptional = userRepository.findById(userid);
		if(!userOptional.isPresent())
			throw new UserNotFound("User Not Found");
		
		List<Order> allorders =  userOptional.get().getOrders();
		CollectionModel<Order> finalResources = new CollectionModel<Order>(allorders);
		
		return finalResources;
	}

}
