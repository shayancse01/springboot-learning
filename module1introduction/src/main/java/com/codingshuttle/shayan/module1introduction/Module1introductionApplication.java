package com.codingshuttle.shayan.module1introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
	@Autowired
	PaymentService paymentServiceObj1;

	@Autowired
	PaymentService paymentServiceObj2;

	public static void main(String[] args) {

		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { //passing the same arguments as in the main method.
		System.out.println(paymentServiceObj1.hashCode());
		System.out.println(paymentServiceObj2.hashCode());

		paymentServiceObj1.pay();
		paymentServiceObj2.pay();
	}
}
