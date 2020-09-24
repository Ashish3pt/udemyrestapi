package com.example.demo.Hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping("/helloo")
	public String helloWorld()
	{
		return "Hello World";
	}
	
	@GetMapping("/userbean")
	public UserDetails hellowWorldBean()
	{
		return new UserDetails("Kalyan","Reddy","Panvel");
	}
}
