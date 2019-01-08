package com.flame_springbootdo.common.rabbitmq;

import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * @Author Flame
 * @Date 2019/1/4 9:39
 * @Version 1.0
 */
//@Service
public class RabbitMqReceiver {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiver(String msg){
        logger.info("msg:"+msg);
    }
}
