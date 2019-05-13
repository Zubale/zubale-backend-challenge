package com.zuballe.challenge.zuballechallenge.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {
	
	@GetMapping
	public String welcome() {
		return "Welcome to Quota Application a Zubale Challenge";
	}
}
