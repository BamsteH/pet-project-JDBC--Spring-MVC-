package com.example.demo.exceptions;

import com.example.demo.dto.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ControllerAdvisor(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(DomainException e) {
        return ResponseEntity.status(e.getCodeError()).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
