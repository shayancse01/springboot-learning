package com.codingshuttle.shayan.module1introduction;

import com.codingshuttle.shayan.module1introduction.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
	//final NotificationService notificationServiceObj; //if we put @Autowired here itself, then it would become feild Dependecy injection

//	public Module1introductionApplication(NotificationService notificationServiceObj) {
//		this.notificationServiceObj = notificationServiceObj;  //Constructor Dependecy injection.
//	}

	public static void main(String[] args) {

		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Autowired
	Map<String, NotificationService> notificationServiceMap = new HashMap<>();
	//class name and class object pair

	@Override
	public void run(String... args) throws Exception { //passing the same arguments as in the main method.
//		//NotificationService notificationService = new EmailNotificationService();
//		notificationServiceObj.send("hello");

		for (var notificationService: notificationServiceMap.entrySet()) {
			System.out.println(notificationService.getKey());
			notificationService.getValue().send("hello my name is Shayan");
		}
	}
}
