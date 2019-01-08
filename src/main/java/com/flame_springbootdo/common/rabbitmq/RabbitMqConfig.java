package com.flame_springbootdo.common.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RabbitMqConfig {
	 public static final String EXCHANGE   = "flame-exchange";
	 public static final String ROUTINGKEY = "flame-routingKey";
	 public static final String QUEUE_NAME = "flame-queue";
	  
	 //声明队列
	  @Bean
	  public Queue queue() {
	    return new Queue(QUEUE_NAME, true); // true表示持久化该队列
	  }
		 
	  //声明交互器
	  @Bean
      TopicExchange topicExchange() {
	    return new TopicExchange(EXCHANGE);
	  }
	 
	  //绑定
	  @Bean
	  public Binding binding() {
	    return BindingBuilder.bind(queue()).to(topicExchange()).with(ROUTINGKEY);
	  }
}
