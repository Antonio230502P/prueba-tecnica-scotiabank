package com.scotiabank.technical_test.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.scotiabank.technical_test.dto.CustomErrorResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomGlobalExceptionController {
    private String obtainCurrentDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<CustomErrorResponse> handleFeignStatusException(FeignException ex, WebRequest request) {
        String path = request.getDescription(false).replace("uri=", "");
        int status;
        String message;
        CustomErrorResponse error;

        if (ex.status() == 404) {
            message = "No existe ninguna mascota con ese id";
            status = HttpStatus.NOT_FOUND.value();
            error = new CustomErrorResponse(message, obtainCurrentDateTime(), status, path);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        } else {
            message = ex.contentUTF8();
            status = HttpStatus.valueOf(ex.status()).value();
            error = new CustomErrorResponse(message, obtainCurrentDateTime(), status, path);
            return new ResponseEntity<>(error, HttpStatus.valueOf(ex.status()));
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        int status = HttpStatus.BAD_REQUEST.value();
        String path = request.getDescription(false).replace("uri=", "");

        CustomErrorResponse errors = new CustomErrorResponse(message, obtainCurrentDateTime(), status, path);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
        String fieldName = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        String message = switch (fieldName) {
            case "id" -> "El valor del campo 'id' tiene que ser un número entero";
            case "status" -> "El valor del campo 'status' tiene que ser un String, por ejemplo, 'Disponible' o 'No disponible'";
            case "name" -> "El valor del campo 'name' tiene que ser un String, por ejemplo, 'Solovino' o 'Firulais'";
            default -> "";
        };

        int status = HttpStatus.BAD_REQUEST.value();
        String path = request.getDescription(false).replace("uri=", "");

        CustomErrorResponse errors = new CustomErrorResponse(message, obtainCurrentDateTime(), status, path);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
}