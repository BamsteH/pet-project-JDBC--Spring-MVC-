package com.example.demo.application.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewEmployeeEventMessage {

  private final long userId;
  private final String departmentName;

}
