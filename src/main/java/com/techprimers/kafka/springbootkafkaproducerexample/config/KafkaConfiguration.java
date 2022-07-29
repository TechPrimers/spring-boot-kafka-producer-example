package com.techprimers.kafka.springbootkafkaproducerexample.config;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    public static final String KAFKA_EXAMPLE = "Kafka_Example";
    public static final String KAFKA_EXAMPLE_JSON = "Kafka_Example_User";

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    // Create related kafka topics
    @Bean
    public NewTopic testTopic() {
        return new NewTopic(KAFKA_EXAMPLE, 1, (short) 1);
    }

    @Bean
    public NewTopic testJsonTopic() {
        return new NewTopic(KAFKA_EXAMPLE_JSON, 1, (short) 1);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaSimpleMessageTemplate() {
        return new KafkaTemplate<>(simpleMessageProducerFactory());
    }

    // Default Factory is to send String messages
    private ProducerFactory<String, String> simpleMessageProducerFactory() {
        final Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Object producer factory & template
    @Bean
    public KafkaTemplate<String, User> kafkaObjectTemplate() {
        return new KafkaTemplate<>(objectProducerFactory());
    }

    private ProducerFactory<String, User> objectProducerFactory() {
        final Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
}