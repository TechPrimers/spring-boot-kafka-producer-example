# Spring Boot with Kafka Producer Example

This Project covers how to use Spring Boot with Spring Kafka to Publish JSON/String message to a Kafka topic
## Start Zookeeper
- `bin/zookeeper-server-start.sh config/zookeeper.properties`

## Start Kafka Server
- `bin/kafka-server-start.sh config/server.properties`

## Create Kafka Topic
- `bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Kafka_Example --from-beginning`

## Consume from the Kafka Topic via Console
- `bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic Kafka_Example --from-beginning`

## Publish message via WebService
- `http://localhost:8081/kafka/publish/Sam`
- `http://localhost:8081/kafka/publish/Peter`