package com.example.demo.application.exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DomainException {

  @Override
  public Integer getCodeError() {
    return HttpStatus.NOT_FOUND.value();
  }

}
