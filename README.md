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




