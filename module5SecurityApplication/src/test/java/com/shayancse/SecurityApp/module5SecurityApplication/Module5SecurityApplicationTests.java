package com.shayancse.SecurityApp.module5SecurityApplication;

import com.shayancse.SecurityApp.module5SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module5SecurityApplication.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLOutput;

@SpringBootTest
class Module5SecurityApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {

		User user = new User(4L, "shayan@gmail.com", "shayan123", "shayan");

		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		System.out.println(id);
	}

}
