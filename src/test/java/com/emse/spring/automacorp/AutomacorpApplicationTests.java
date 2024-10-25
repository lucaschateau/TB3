package com.emse.spring.automacorp;

import com.emse.spring.automacorp.hello.DummyUserService;
import com.emse.spring.automacorp.hello.GreetingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.List;

@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class GreetingServiceTest {

	@Autowired
	private GreetingService greetingService;

	@Autowired
	public DummyUserService dummyUserService;

	@Test
	public void testGreeting(CapturedOutput output) {
		greetingService.greet("Spring");
		Assertions.assertThat(output.getAll()).contains("Bonjour, Spring!");
	}

	@Test
	public void testGreetingAll(CapturedOutput output) {
		dummyUserService.greetAll(List.of("Elodie", "Charles"));
		Assertions.assertThat(output).contains("Hello, Elodie!", "Hello, Charles!");
	}
}