package com.example.demo.events.controller.notification;

import com.example.demo.events.message.notification.NewEmployeeEventMessage;
import com.example.demo.service.NotificationService;
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
