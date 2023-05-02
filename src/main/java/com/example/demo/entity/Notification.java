package com.example.demo.entity;

import lombok.*;
import springfox.documentation.spring.web.json.Json;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

  private long id;
  private String message;
  private Json payload;

}
