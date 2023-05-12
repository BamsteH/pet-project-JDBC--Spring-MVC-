package com.example.demo.application.config;

import org.springframework.amqp.core.*;
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
  @Value("${rabbitmq.queue.for-binding-1}")
  private String QUEUE_FANOUT_1;
  @Value("${rabbitmq.queue.for-binding-2}")
  private String QUEUE_FANOUT_2;
  @Value("${rabbitmq.queue.for-binding-3}")
  private String QUEUE_FANOUT_3;
  @Value("${rabbitmq.queue.for-binding-4}")
  private String QUEUE_FANOUT_4;
  @Value("${rabbitmq.topic-exchange}")
  private String TOPIC_EXCHANGE;
  @Value("${rabbitmq.fanount-exchange}")
  private String FANOUT_EXCHANGE;
  @Value("${rabbitmq.routing-key}")
  private String ROUTING_KEY;

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
  public Queue myQueueFanout1() {
    return new Queue(QUEUE_FANOUT_1);
  }

  @Bean
  public Queue myQueueFanout2() {
    return new Queue(QUEUE_FANOUT_2);
  }

  @Bean
  public Queue myQueueFanout3() {
    return new Queue(QUEUE_FANOUT_3);
  }

  @Bean
  public Queue myQueueFanout4() {
    return new Queue(QUEUE_FANOUT_4);
  }

  @Bean
  public Queue myQueue() {
    return new Queue(QUEUE);
  }

  @Bean
  public TopicExchange topicExchange() {
    return new TopicExchange(TOPIC_EXCHANGE);
  }

  @Bean
  public FanoutExchange fanoutExchange() {
    return new FanoutExchange(FANOUT_EXCHANGE);
  }

  @Bean
  public Binding bindingToFanout1() {
    return BindingBuilder.bind(myQueueFanout1()).to(fanoutExchange());
  }

  @Bean
  public Binding bindingToFanout2() {
    return BindingBuilder.bind(myQueueFanout2()).to(fanoutExchange());
  }

  @Bean
  public Binding bindingToFanout3() {
    return BindingBuilder.bind(myQueueFanout3()).to(fanoutExchange());
  }

  @Bean
  public Binding bindingToFanout4() {
    return BindingBuilder.bind(myQueueFanout4()).to(fanoutExchange());
  }

  @Bean
  public Binding binding2() {
    return BindingBuilder.bind(myQueue()).to(topicExchange()).with(ROUTING_KEY);
  }

}
