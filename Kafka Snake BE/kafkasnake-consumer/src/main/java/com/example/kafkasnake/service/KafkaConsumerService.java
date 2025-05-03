package com.example.kafkasnake.service;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.ConsumerFactory;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
public class KafkaConsumerService {

    private final ConsumerFactory<String, String> consumerFactory;

    public KafkaConsumerService(ConsumerFactory<String, String> consumerFactory) {
        this.consumerFactory = consumerFactory;
    }

    // KafkaListener to consume messages normally
    @KafkaListener(topics = "${snake.kafka.topic.name}", groupId = "snake-game-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    // Method to read all messages from Kafka and save them to a file
    public void readAllMessagesAndSaveToFile() throws IOException {
        // Create a new KafkaConsumer
        KafkaConsumer<String, String> consumer = (KafkaConsumer<String, String>) consumerFactory.createConsumer();
        consumer.subscribe(Collections.singletonList("snake-game"));

        // Move the consumer to the beginning of the topic
        consumer.seekToBeginning(consumer.assignment());

        // Create the output file with the current date and time in the filename
        String fileName = generateFileName();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Poll messages from Kafka
            while (true) {
                var records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) {
                    break;  // Stop if no more messages
                }

                // Write each message to the file
                for (var record : records) {
                    writer.write("Consumed message: " + record.value() + "\n");
                }
            }
        }
    }

    // Utility method to generate filename based on current date and time
    private String generateFileName() {
        // Get the current date and time in the format: DD.MM.YYYY-HH.MM.SS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm.ss");
        String formattedDate = LocalDateTime.now().format(formatter);

        // Return the filename with "Kafka History" as suffix
        return formattedDate + " Kafka History.txt";
    }
}
