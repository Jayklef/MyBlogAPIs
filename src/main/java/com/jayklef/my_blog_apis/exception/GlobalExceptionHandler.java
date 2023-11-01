package com.jayklef.my_blog_apis.exception;

import com.jayklef.my_blog_apis.dto.ErrorDetailsDto;
import jakarta.servlet.http.PushBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> ResourceNotFoundException(ResourceNotFoundException exception,
                                                                     WebRequest webRequest){
            ErrorDetailsDto error = new ErrorDetailsDto(
                    HttpStatus.NOT_FOUND.value(),
                    exception.getMessage(),
                    webRequest.getDescription(false),
                    new Date()
            );
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetailsDto> BlogAPIException(BlogAPIException exception,
                                                                     WebRequest webRequest) {
        ErrorDetailsDto error = new ErrorDetailsDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> GlobalException(Exception exception,
                                                           WebRequest webRequest) {
        ErrorDetailsDto error = new ErrorDetailsDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                webRequest.getDescription(false),
                new Date()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ErrorDetailsDto> MethodArgumentMismatchException(MethodArgumentTypeMismatchException exception,
                                                                                WebRequest request){
            ErrorDetailsDto error = new ErrorDetailsDto(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    exception.getMessage(),
                    request.getDescription(false),
                    new Date()
            );
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

