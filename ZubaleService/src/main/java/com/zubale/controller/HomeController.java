package com.zubale.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	/*
	 * this is the endpoint to test the home interface
	 */
	@RequestMapping("/")
	public String home() {
		return "Zubale Quote and Vote service!";
	}

}