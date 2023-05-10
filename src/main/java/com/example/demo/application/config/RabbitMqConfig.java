package com.example.demo.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

  @Value("${rabbitmq.queue}")
  private String QUEUE;

  private static final String EXCHANGE = "example-demo-exchange";
  private static final String ROUTING_KEY = "example-demo-routingKey";

  @Bean
  public CachingConnectionFactory connectionFactory() {
    return new CachingConnectionFactory("localhost");
  }

  @Bean
  public RabbitAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    return new RabbitTemplate(connectionFactory());
  }

  @Bean
  public Queue myQueue() {
    return new Queue(QUEUE);
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(EXCHANGE);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder.bind(myQueue()).to(topicExchange()).with(ROUTING_KEY);
  }

}
