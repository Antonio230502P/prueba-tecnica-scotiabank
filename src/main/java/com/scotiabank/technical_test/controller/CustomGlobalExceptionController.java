package com.scotiabank.technical_test.controller;

import com.scotiabank.technical_test.dto.CustomErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class CustomGlobalExceptionController {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<CustomErrorResponse> handleFeignStatusException(FeignException ex, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        int status;
        String message;
        CustomErrorResponse error;

        if (ex.status() == 404) {
            message = "No existe ninguna mascota con ese id";
            status = HttpStatus.NOT_FOUND.value();
            error = new CustomErrorResponse(message, timestamp, status, path);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            message = ex.contentUTF8();
            status = HttpStatus.valueOf(ex.status()).value();
            error = new CustomErrorResponse(message, timestamp, status, path);
            return new ResponseEntity<>(error, HttpStatus.valueOf(ex.status()));
        }
    }
}