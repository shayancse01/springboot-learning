package com.codingshuttle.shayan.module1introduction;

import com.codingshuttle.shayan.module1introduction.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
	@Autowired
	NotificationService notificationServiceObj;

	public static void main(String[] args) {

		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { //passing the same arguments as in the main method.
		//NotificationService notificationService = new EmailNotificationService();
		notificationServiceObj.send("hello");
	}
}
