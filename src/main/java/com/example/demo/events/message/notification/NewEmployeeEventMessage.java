package com.example.demo.events.message.notification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewEmployeeEventMessage {

  private final long userId;
  private final String departmentName;

}
