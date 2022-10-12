package com.example.demo.exceptions;

public class DomainException extends RuntimeException{

    public DomainException(){

    }

    public DomainException(String message) {
        super(message);
    }
}
