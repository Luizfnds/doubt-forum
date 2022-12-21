package com.lefnds.doubtforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DoubtForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoubtForumApplication.class, args);
	}

	@GetMapping
	@RequestMapping(value = "/test")
	String test() {
		return "Test successful!";
	}

}
