# 🚗 Smart Toll Plaza Automation System

### A Spring Boot Microservices-Based Toll Collection Platform

Automating toll collection using **FASTag**, **REST APIs**, **Spring Boot Microservices**, and an **API Gateway**.

![Java](https://img.shields.io/badge/Java-26-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge\&logo=springboot)
![Spring Cloud Gateway](https://img.shields.io/badge/API_Gateway-Spring_Cloud-success?style=for-the-badge)
![REST API](https://img.shields.io/badge/REST-API-blue?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/H2-Database-1E88E5?style=for-the-badge)

---

# 📖 Overview

The **Smart Toll Plaza Automation System** is a microservices-based Spring Boot application developed as a capstone project to automate highway toll collection using FASTag technology.

The system validates registered vehicles, verifies FASTag wallet balances, deducts toll charges, records journey history, and routes every request through a centralized API Gateway.

Each service is independently developed following a layered architecture and communicates using synchronous REST APIs with **RestTemplate**.

---

# 🎯 Objectives

* Automate toll payment
* Register and manage vehicles
* Maintain FASTag wallets
* Store journey history
* Enable microservice communication
* Route all requests through an API Gateway

---

# 🏗️ System Architecture

```text
                    Client (Postman)
                           │
                           ▼
                  API Gateway (8080)
                           │
        ┌────────────┬────────────┬────────────┬────────────┐
        ▼            ▼            ▼            ▼
 Vehicle API    Wallet API    Toll API    Journey API
   (8081)         (8082)        (8084)       (8083)
                           │
                           ▼
                 REST Communication
                     (RestTemplate)
```

---

# 🧩 Microservices

| Service             | Description                                                      |
| ------------------- | ---------------------------------------------------------------- |
| 🚗 Vehicle Service  | Registers and manages vehicle information                        |
| 💳 Wallet Service   | Manages FASTag wallet balance and recharge                       |
| 🚧 Toll Service     | Validates vehicles, deducts toll amount, and coordinates payment |
| 🛣️ Journey Service | Stores and retrieves journey history                             |
| 🌐 API Gateway      | Routes all client requests to the appropriate microservice       |

---

# 🔄 Toll Payment Flow

```text
Client
   │
   ▼
API Gateway
   │
   ▼
Toll Service
   │
   ├────────► Vehicle Service
   │            │
   │            ▼
   │      Get FASTag ID
   │
   ├────────► Wallet Service
   │            │
   │            ▼
   │      Deduct Balance
   │
   ├────────► Journey Service
   │            │
   │            ▼
   │      Save Journey
   │
   ▼
Return Payment Response
```

---

# 📦 Repository Structure

```text
Smart-Toll-Plaza-Automation-System
│
├── APIGateway
├── VehicleAPI
├── WalletAPI
├── JourneyAPI
├── TollAPI
└── README.md
```

---

# ✨ Features

## 🚗 Vehicle Service

* Register Vehicle
* View Vehicles
* Search by Vehicle Number
* Update Vehicle
* Delete Vehicle
* Unique Vehicle Number Validation
* Unique FASTag Validation

## 💳 Wallet Service

* Create Wallet
* Recharge Wallet
* Deduct Wallet Balance
* View Wallet Details
* Prevent Negative Balance

## 🚧 Toll Service

* Validate Vehicle
* Retrieve FASTag Details
* Deduct Wallet Balance
* Create Journey Record
* Return Payment Status

## 🛣️ Journey Service

* Store Journey History
* View All Journeys
* Search Journey by Vehicle Number

## 🌐 API Gateway

* Centralized Entry Point
* Route Requests to Microservices

---

# 🛠️ Tech Stack

## Backend

* Java 26
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Validation
* Spring Cloud Gateway

## Database

* H2 Database

## Communication

* REST APIs
* RestTemplate

## Tools

* IntelliJ IDEA
* Maven
* Postman
* Git
* GitHub

---

# 📁 Layered Architecture

```text
controller
service
repository
entity
dto
exception
advice
config
```

---

# 🚀 Running the Project

Start the services in the following order:

```text
Vehicle API
      ↓
Wallet API
      ↓
Journey API
      ↓
Toll API
      ↓
API Gateway
```

---

# 🌐 Default Ports

| Service     | Port |
| ----------- | ---- |
| API Gateway | 8080 |
| Vehicle API | 8081 |
| Wallet API  | 8082 |
| Journey API | 8083 |
| Toll API    | 8084 |

---

# 🔄 End-to-End Workflow

1. Register Vehicle
2. Create Wallet
3. Recharge Wallet
4. Pay Toll
5. Deduct Wallet Balance
6. Save Journey
7. Return Payment Response

---

# 📚 Key Concepts

* Spring Boot
* REST APIs
* Microservices
* Layered Architecture
* DTO Pattern
* Bean Validation
* Global Exception Handling
* RestTemplate
* API Gateway
* H2 Database
* CRUD Operations

---

# 📌 Future Enhancements

* Notification Service
* Eureka Service Discovery
* OpenFeign Client
* MySQL / PostgreSQL
* Docker
* Spring Security
* JWT Authentication
* Swagger / OpenAPI

---

# 👨‍💻 Author

**Kishorekumar S**

B.E. Computer Science and Engineering

Saveetha Engineering College

GitHub: https://github.com/Kishorekumar-03-sec
