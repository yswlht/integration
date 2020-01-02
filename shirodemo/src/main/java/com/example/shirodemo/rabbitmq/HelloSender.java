package com.example.shirodemo.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendTopic(Object msg){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = msg + " " + date;
        System.out.println("SenderTopic: " + context);
        this.rabbitTemplate.convertAndSend("exchange", "topic.message", context);
    }

    public void sendFanout(Object msg){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = msg + " " + date;
        System.out.println("SenderFanout: " + context);
        this.rabbitTemplate.convertAndSend("exchange2", "topic.message", context);
    }
}
