package com.codingshuttle.shayan.module2restApi.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponce<T> {

    @JsonFormat(pattern = "hh:mm:ss yyyy-MM-dd")
    private LocalDateTime timeStamp;

    // In the Api Responce either Data will be there or error will be there
    private T data;
    private ApiError error;

    public ApiResponce() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponce(T data) {
        this();
        this.data = data;
    }

    public ApiResponce(ApiError error) {
        this();
        this.error = error;
    }


}
