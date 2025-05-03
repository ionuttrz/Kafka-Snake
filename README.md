# 🐍 Kafka-Snake

Kafka-Snake is a fun and educational project that combines a classic Snake game with modern event-driven architecture using Apache Kafka. It demonstrates real-time event streaming from a game frontend to a Kafka backend, making it a great example of integrating interactive applications with streaming data pipelines.

# 🚀 Overview

This project consists of:

    A frontend Snake game where players can:

        Start a game session

        Accumulate points

        End the game with a final score

    A Kafka producer that sends gameplay events (start, score updates, end) to a Kafka topic.

    A Kafka broker that acts as the central event streaming platform.

    A Kafka consumer that listens to these events for further processing, such as logging, analytics, or leaderboard generation.
