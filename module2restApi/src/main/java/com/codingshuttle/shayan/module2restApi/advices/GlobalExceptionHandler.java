package com.codingshuttle.shayan.module2restApi.advices;

import com.codingshuttle.shayan.module2restApi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Whenever a NoSuchElementException happens inside The Employee controller, don't crash. Instead, call this method.

//    @ExceptionHandler(ResourceNotFoundException.class)  //Handles the Resource not found exception
//    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception) {
//        ApiError apiError = ApiError.builder()
//                .status(HttpStatus.NOT_FOUND)
//                .message(exception.getMessage())
//                .build();
//        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//    }


    //Now instead of returning ApiError we will return ApiResponce and inside the ApiResponce there is ApiError
    @ExceptionHandler(ResourceNotFoundException.class)  //Handles the Resource not found exception
    public ResponseEntity<ApiResponce<?>> handleResourceNotFound(ResourceNotFoundException exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage())
                .build();
        return buildErrorResponceEntity(apiError);
    }

    @ExceptionHandler(Exception.class) //handles all type of exception as it is the parent class
    public ResponseEntity<ApiResponce<?>> handleInternalServerError(Exception exception) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build();
        return buildErrorResponceEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponce<?>> handleInputValidationErrors(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage()) //getting default message from all the errors
                .collect(Collectors.toList()); //collecting them as single list of String

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Input validations failed")
                .subErrors(errors)
                .build();
        return buildErrorResponceEntity(apiError);
    }

    private ResponseEntity<ApiResponce<?>> buildErrorResponceEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponce<>(apiError), apiError.getStatus());
    }
}
