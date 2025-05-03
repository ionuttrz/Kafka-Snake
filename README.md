# 🐍 Kafka-Snake

Kafka-Snake is a fun and educational project that combines a classic Snake game with modern event-driven architecture using Apache Kafka. It demonstrates real-time event streaming from a game frontend to a Kafka backend, making it a great example of integrating interactive applications with streaming data pipelines.

## 🚀 Overview

This project consists of:
- A **frontend** Snake game built with Angular where players can:
  - Start a game session
  - Accumulate points
  - End the game with a final score
- A **Kafka producer** implemented in Spring Boot that sends gameplay events (start, score updates, end) to a Kafka topic.
- A **Kafka broker** that acts as the central event streaming platform.
- A **Kafka consumer**, also built with Spring Boot, that listens to these events and processes them.

## 📌 Features

- 🎮 Real-time gameplay event streaming
- 🧩 Microservice-friendly and decoupled architecture
- ⚙️ Built with industry-standard tools: Angular + Spring Boot + Kafka
- 💡 Easy to extend for analytics, leaderboards, or session replays

## 🛠️ Tech Stack

- **Frontend**: Angular (TypeScript)
- **Backend**: Java Spring Boot (Producer & Consumer)
- **Messaging Platform**: Apache Kafka

- ## 🧰 Getting Started

Follow these steps to run the Kafka-Snake project locally on your machine.

### 📦 Prerequisites

- [Apache Kafka (download)](https://kafka.apache.org/)
- Java 17+ (for Spring Boot services)
- Node.js + Angular CLI
- Maven
- A modern browser (for the game)

---

### 🪛 Step-by-Step Setup

#### 1. Download and Start Kafka (Windows)

- Download Kafka from [https://kafka.apache.org/](https://kafka.apache.org/)
- Extract it and navigate to the Kafka directory in your terminal
- Start the Kafka server with:

```bash
.\bin\windows\kafka-server-start.bat .\config\server.properties
```

#### 2. Create a Kafka Topic 
- In a new terminal, run:
```bash
.\bin\windows\kafka-topics.bat --create --topic <topic_name> --bootstrap-server <kafka_broker>:9092 --partitions 1 --replication-factor 1
```
- Replace <topic_name> with your desired topic (e.g., snake-game-events)
- Replace <kafka_broker> with your Kafka host (commonly localhost)

  🔍 Useful Kafka CLI Commands:
- List active topics:
```bash
.\bin\windows\kafka-topics.bat --list --bootstrap-server <kafka_broker>:9092
```
- View all messages from a topic (for testing):
```bash
.\bin\windows\kafka-console-consumer.bat --bootstrap-server <kafka_broker>:9092 --topic <topic_name> --from-beginning
```

#### 3. Start the Kafka Consumer (Spring Boot)
- This service will listen for game events and log them.

#### 4. Start the Kafka Producer (Spring Boot)
- This service sends gameplay events (start, score update, end) to Kafka.
- 
#### 5. Start the Angular Frontend
- Go to the frontend directory:
```bash
npm install
ng serve --open
```
- The game will open automatically in your default browser at http://localhost:4200.

#### 6. Play the Game!
  - Enter a username
  - Press Start
  - Control the snake and earn points
  - Press End to finish the session

All gameplay events will be streamed to Kafka and logged in real-time by the backend consumer.



