package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
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
