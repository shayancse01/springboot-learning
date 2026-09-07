package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private String BASE_URL;

    @Bean
    RestClient getEmployeeServiceRestClient() {
        return RestClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                //Now handling the server error here itself
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new  RuntimeException("Server error occurred");
                })
                .build();
    }


}
