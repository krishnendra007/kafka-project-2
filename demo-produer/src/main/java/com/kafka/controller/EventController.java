package com.kafka.controller;

import com.kafka.dto.Customer;
import com.kafka.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try {
            publisher.sendMessageToTopic(message);
            return ResponseEntity.ok("message Published successfully");

        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody Customer customer){
        try {
            publisher.sendEventToTopic(customer);
            return ResponseEntity.ok("message Published successfully");

        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
