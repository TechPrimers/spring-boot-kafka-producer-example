package com.techprimers.kafka.springbootkafkaproducerexample.resource;

import com.techprimers.kafka.springbootkafkaproducerexample.config.KafkaConfiguration;
import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class UserResource {

    @Autowired
    @Qualifier("kafkaSimpleMessageTemplate")
    private KafkaTemplate<String, String> kafkaSimpleMessageTemplate;

    @Autowired
    @Qualifier("kafkaObjectTemplate")
    private KafkaTemplate<String, User> kafkaObjectTemplate;

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable final String message) {
        kafkaSimpleMessageTemplate.send(KafkaConfiguration.KAFKA_EXAMPLE, message);
        return "Message : " + message + " published successfully";
    }

    @PostMapping("/publish")
    public String publishUser(@RequestBody final User user) {
        kafkaObjectTemplate.send(KafkaConfiguration.KAFKA_EXAMPLE_JSON, user);
        return "User : " + user + "published successfully";
    }

}