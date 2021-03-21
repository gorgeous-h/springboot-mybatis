package com.example.springbootmybatis.controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloSpringBoot {
	
	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!"+LocalDate.now();
	}
	
}
