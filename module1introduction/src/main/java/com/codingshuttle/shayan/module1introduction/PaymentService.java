package com.codingshuttle.shayan.module1introduction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

        public void pay() {
            System.out.println("paying...");
        }

        @PostConstruct
        public void afterInitialize() {
            System.out.println("Before paying...");
        }

        @PreDestroy
        public  void beforeDestroy() {
            System.out.println("After payment is done");
        }

}
