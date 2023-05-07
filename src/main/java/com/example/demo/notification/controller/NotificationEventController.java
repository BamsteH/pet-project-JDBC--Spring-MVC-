package com.example.demo.notification.controller;

import com.example.demo.application.notification.NewEmployeeEventMessage;
import com.example.demo.notification.service.NotificationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("notificationEventController")
public class NotificationEventController {

  private final NotificationService notificationEventService;

  public NotificationEventController(NotificationService notificationEventService) {
    this.notificationEventService = notificationEventService;
  }

  @EventListener
  public void createOnNewEmployee(NewEmployeeEventMessage message) {
    this.notificationEventService.createNotification("NEW_EMPLOYEE", message);
  }


}
