package com.rpEmpresa.web_app.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

     public static ResponseEntity<Object> build(
             HttpStatus status,
             Object responseBody,
             String Message
     ) {

         Map<String, Object> response = new HashMap<>();

         response.put("status", status.value());
         response.put("data", responseBody);
         response.put("message", Message);

         return new ResponseEntity<>(response, status);
    }


    public static  ResponseEntity<Object> buildPaged(
            HttpStatus status,
            Object responseBody,
            String Message,
            int page,
            int pageSize
    ) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", status.value());
        response.put("data", responseBody);
        response.put("message", Message);
        response.put("page", page);
        response.put("pageSize", pageSize);

        return new ResponseEntity<>(response, status);
    }
}
