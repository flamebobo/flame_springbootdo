package com.flame_springbootdo.common.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;


/***
 * 消息队列，生产者
 * @author Administrator
 *
 */
@Service
public class RabbitSend implements RabbitTemplate.ConfirmCallback, ReturnCallback {
    public final Logger log = LoggerFactory.getLogger(RabbitSend.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功【" + cause + "】correlationData【" + correlationData.getId() + "】");
        } else {
            log.info("消息发送失败【" + cause + "】");
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }

    //发送消息，不需要实现任何接口，供外部调用。
    public void send(String msg) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.ROUTINGKEY, msg, correlationId);

        // rabbitTemplate.send(message);  //发消息，参数类型为org.springframework.amqp.core.Message
        //rabbitTemplate.convertAndSend(object); //转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
        //rabbitTemplate.convertSendAndReceive(message) //转换并发送消息,且等待消息者返回响应消息。
        //  String response = rabbitTemplate.convertSendAndReceive(RabbitMqConfig.EXCHANGE,RabbitMqConfig.ROUTINGKEY, msg, correlationId).toString();
    }

}
