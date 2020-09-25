package com.example.demo.Hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
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
	
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name="Accept-Langauge", required=false) String locale)
	{
		return messageSource.getMessage("label.hello",null, new Locale( locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2()
	{
		return messageSource.getMessage("label.hello",null, LocaleContextHolder.getLocale());
	}
}
