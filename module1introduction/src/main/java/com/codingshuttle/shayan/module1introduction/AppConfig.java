package com.codingshuttle.shayan.module1introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class AppConfig {

    //By using @Bean annotation, we are taleing springboot how to create this bean.
    @Bean
    //@Scope("prototype")
    PaymentService paymentService() {
        // Here You can add some more logic for bean creation
        return new PaymentService();
    }
}
