package com.kafka.service;


import com.kafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListner {

    Logger logger = LoggerFactory.getLogger(KafkaMessageListner.class);

    @KafkaListener(topics = "msg-topic-5", groupId = "group-1")
    public  void  consume (Customer customer){
        logger.info("consumer consumed the message"+ customer.toString());
        System.out.println(customer);
    }
}
