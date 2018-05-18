package com.techprimers.kafka.springbootkafkaproducerexample.resource;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class UserResource {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping("/publish/{name}")
    public String post(@PathVariable("name") final String name) {

        kafkaTemplate.send(TOPIC, new User(name, "Technology", 12000L));

        return "Published successfully";
    }
}
