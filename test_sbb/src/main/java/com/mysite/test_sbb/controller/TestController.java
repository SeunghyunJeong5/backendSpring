package com.mysite.test_sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@GetMapping("/board")
	@ResponseBody
	public String clientRequest() {
		
		return "Welcome to My Test Spring Boot22222222";
	}

}