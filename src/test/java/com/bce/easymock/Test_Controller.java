package com.bce.easymock;

import java.lang.reflect.Field;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bce.easymock.controller.HelloRestController;
import com.bce.easymock.dto.HelloDto;
import com.bce.easymock.service.HelloService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_Controller {

	private HelloRestController controller;
	private HelloService helloService;

	@BeforeEach
	public void setUp() throws NoSuchFieldException, IllegalAccessException {
		// Instantiate the controller and mock service
		controller = new HelloRestController();
		helloService = EasyMock.createMock(HelloService.class);

		// Use reflection to inject the mock into the private field
		Field field = HelloRestController.class.getDeclaredField("helloService");
		field.setAccessible(true);
		field.set(controller, helloService);
	}

	@Test
	public void testGreeting() {
		// Define the behavior of the mocked service
		EasyMock.expect(helloService.hello(EasyMock.anyString(), EasyMock.anyObject(HelloDto.class))).andAnswer(() -> {
			// Simulate setting the message in the DTO
			HelloDto dto = (HelloDto) EasyMock.getCurrentArguments()[1];
			dto.setMsg("ok");
			return "test";
		});

		EasyMock.replay(helloService);

		// Call the controller method
		String result = controller.greeting("Narottam");

		// Assert the result
		assertEquals("ok", result, "The response should be 'ok' when the DTO message is set to 'ok'.");

		// Verify the mock interactions
		EasyMock.verify(helloService);
	}
}
