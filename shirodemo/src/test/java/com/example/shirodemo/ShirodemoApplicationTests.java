package com.example.shirodemo;

import com.example.shirodemo.rabbitmq.HelloSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShirodemoApplicationTests {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void test1(){
        helloSender.sendFanout("you are so handsome!");
    }

    @Test
    public void oneToMany() throws Exception{
        for(int i = 0; i < 60000; i++){
            helloSender.sendTopic(i);
            Thread.sleep(30);
        }
    }
}
