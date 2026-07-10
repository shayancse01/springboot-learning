package com.codingshuttle.shayan.module2restApi.advices;

import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponceHandler implements ResponseBodyAdvice<Object> {

    //The supports() method is actually a filter.
    // It tells Spring whether your beforeBodyWrite() method should run for a particular controller response or not.
    //return true; → Apply beforeBodyWrite() to every API response.
    //return false; → Skip beforeBodyWrite() for the API response.
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    //Here we can tranform the object body to anything we want
    //beforeBodyWrite() is used to modify or wrap every API response just before it is sent to the client.
    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResponce<?>) { //If responce is already in ApiResponce / Already wrapped?
            return body; //send it without any changes / Return as it is
        }
        return new ApiResponce<>(body); //Put the responce inside ApiResponce / wrap the responce

        //Now it will return each and every responce encapsulated by ApiResponce only
    }
}
