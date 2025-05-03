package com.example.kafkasnake.controller;
import com.example.kafkasnake.service.KafkaProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
@CrossOrigin(origins = "*") // allow requests from Angular
public class KafkaMessageController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaMessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody MessageRequest request) {
        kafkaProducerService.sendMessage(request.getMessage());
        return ResponseEntity.ok("Message sent to Kafka topic.");
    }

    // DTO class inside controller
    public static class MessageRequest {
        private String message;

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}

