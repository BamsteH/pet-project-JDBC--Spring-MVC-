package com.example.demo.service;


import com.example.demo.entity.Notification;
import com.example.demo.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void createNotification(String type, Object payload) {
    Json jsonPayload = parseJson(payload);

    Notification model = new Notification();
    model.setMessage(type);
    model.setPayload(jsonPayload);

    this.notificationRepository.create(model);
  }

  private Json parseJson(Object payload) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      String jsonPayload = mapper.writeValueAsString(payload);
      return new Json(jsonPayload);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


}
