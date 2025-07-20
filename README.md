# 🔐 Secure Event Management API

A clean, containerized backend app built with **Java 21** and **Spring Boot 3.5.3**, featuring JWT-based security, MySQL persistence, Redis caching, RabbitMQ messaging, and performance logging via AOP.

---

## 🧩 Features

- **🔐 Auth**: User registration & login with JWT tokens  
- **🗓️ Events**: Creation and retrieval of user-specific events  
- **🐬 MySQL**: Persistent data storage managed with Spring Data JPA  
- **⚡ Redis**: Per-user events caching using `RedisTemplate`  
- **📬 RabbitMQ**: Asynchronous event notifications via message queue  
- **⏱️ AOP Logs**: Execution-time logging for service methods  
- **🐳 Dockerized**: Runs smoothly with Docker Compose (MySQL, Redis, RabbitMQ, App)

---

## 🚀 Quick Start

```bash
cd backend
mvn clean package -DskipTests
docker-compose up --build
