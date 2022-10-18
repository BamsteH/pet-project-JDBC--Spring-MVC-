package com.example.demo.exceptions;

public class DomainException extends RuntimeException{

    private Integer codeError;

    public Integer getCodeError() {
        return codeError;
    }

    public DomainException(){
    }

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String message,Integer codeError, Throwable cause) {
        super(message, cause);
        this.codeError = codeError;
    }
}
