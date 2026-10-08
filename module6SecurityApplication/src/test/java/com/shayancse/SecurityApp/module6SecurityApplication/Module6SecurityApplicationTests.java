package com.shayancse.SecurityApp.module6SecurityApplication;

import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module6SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Module6SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user = new User(4L, "shayan@gmail.com", "shayan123", "shayan");

		String token = jwtService.generateAccessToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}

}
