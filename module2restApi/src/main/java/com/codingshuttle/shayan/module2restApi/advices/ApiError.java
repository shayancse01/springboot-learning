package com.codingshuttle.shayan.module2restApi.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data //@Data is a Lombok annotation that automatically generates getters, setters, toString(), equals(), hashCode(), and a required-arguments constructor for a class.
@Builder //@Builder is a Lombok annotation that implements the Builder Design Pattern, allowing objects to be created in a readable and flexible way using method chaining.
public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> subErrors; //to see the errors as a list

}
