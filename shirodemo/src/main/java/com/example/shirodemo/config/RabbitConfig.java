package com.example.shirodemo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    final static String hello = "hello_q";
    final static String hello2 = "hello2_q";

    @Bean
    public Queue queue(){
        return new Queue(RabbitConfig.hello);
    }

    @Bean
    public Queue queue2(){
        return new Queue(RabbitConfig.hello2);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    FanoutExchange exchange2(){
        return new FanoutExchange("exchange2");
    }

    /**参数名要跟前面的方法名一致才有效，否则报错*/
    @Bean
    Binding bindingExchangeHello(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("*.*");
    }

    @Bean
    Binding bindingExchangeHello2(Queue queue2, TopicExchange exchange){
        return BindingBuilder.bind(queue2).to(exchange).with("#");
    }

    @Bean
    Binding bindingExchange2(Queue queue2, FanoutExchange exchang2){
        return BindingBuilder.bind(queue2).to(exchang2);
    }
}
