package com.mysite.sbb.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	//http://localhost:9696/test
	@GetMapping("/test3")
	@ResponseBody
	public String testGet() {
		return "Hello World";
	}
}
