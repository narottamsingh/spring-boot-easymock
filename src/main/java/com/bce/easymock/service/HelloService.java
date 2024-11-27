package com.bce.easymock.service;

import org.springframework.stereotype.Service;

import com.bce.easymock.dto.HelloDto;

@Service
public class HelloService {

	
	public String hello(String msg,HelloDto helloDto) {
		helloDto.setMsg("ok");
		return msg;
		
	}
	
}
