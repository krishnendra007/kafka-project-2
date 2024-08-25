package com.kafka.service;

import com.kafka.config.KafkaProducerConfig;
import com.kafka.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;
    @Autowired
    private KafkaProducerConfig config;


    public  void sendMessageToTopic(String message){
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send(config.createTopic().name(), message);
            future.whenComplete((res, ex) -> {
                if (ex == null) {
                    System.out.println("Message is sent");
                } else {
                    System.out.println(ex.getMessage());
                }
            });
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public  void sendEventToTopic(Customer customer){
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send(config.createTopic().name(), customer);
            future.whenComplete((res, ex) -> {
                if (ex == null) {
                    System.out.println("Message is sent");
                } else {
                    System.out.println(ex.getMessage());
                }
            });
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
