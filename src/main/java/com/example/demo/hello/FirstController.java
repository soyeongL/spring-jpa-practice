package com.example.demo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
	
	@RequestMapping(method= RequestMethod.GET,value="/first-url")
	public void first() {
		
	}
	@ResponseBody
	@RequestMapping(method= RequestMethod.GET,value="/hello_world")
	public String helloWorld() {
		return "hello world";
	}
	
}
