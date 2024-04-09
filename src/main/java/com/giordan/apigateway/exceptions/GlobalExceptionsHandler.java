package com.giordan.apigateway.exceptions;

import com.giordan.apigateway.services.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

    @Value("${server.error.include-exception}")
    private boolean printStackTrace;


    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleObjectNotFoundException(
            ObjectNotFoundException objectNotFoundException) {
        log.error("Failed to find the requested element", objectNotFoundException);
        ErrorResponse errorResponse = new ErrorResponse(
                 HttpStatus.NOT_FOUND.value(),
                 objectNotFoundException.getMessage()
          );
        if (printStackTrace) {
            errorResponse.setStackTrace(Arrays.toString(objectNotFoundException.getStackTrace()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}