package com.codingshuttle.shayan.module2restApi.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    //@JsonFormat(pattern = "hh:mm:ss yyyy-MM-dd")
    private LocalDateTime timeStamp;

    // In the Api Responce either Data will be there or error will be there
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();
        this.error = error;
    }


}
