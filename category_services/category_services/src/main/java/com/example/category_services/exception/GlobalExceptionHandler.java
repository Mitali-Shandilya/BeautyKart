package com.example.category_services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(NotFoundException ex){
        ErrorResponse error=new ErrorResponse(
                404,
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
        ErrorResponse err=new ErrorResponse(
                400,
                ex.getBindingResult().getFieldError().getDefaultMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFound(NoHandlerFoundException ex){
        ErrorResponse err=new ErrorResponse(
                404,
                "Invalid API URL",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(err);
    }

}
