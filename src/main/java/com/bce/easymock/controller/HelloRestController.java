package com.bce.easymock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bce.easymock.dto.HelloDto;
import com.bce.easymock.service.HelloService;

@RestController
public class HelloRestController {

	@Autowired
	private HelloService helloService;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		HelloDto dto = new HelloDto();
		String test = helloService.hello(name, dto);
		String msg = dto.getMsg();
		if ("ok".equals(msg)) {
			return "ok";
		}
		return test;
	}

}
