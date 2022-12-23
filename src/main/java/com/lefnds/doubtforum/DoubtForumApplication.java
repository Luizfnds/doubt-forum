package com.lefnds.doubtforum;

import com.lefnds.doubtforum.config.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DoubtForumApplication {
	public static void main(String[] args) {
		SpringApplication.run(DoubtForumApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("0000"));
	}

	@GetMapping
	@RequestMapping(value = "/test")
	String test() {
		return "Test successful!";
	}
}
