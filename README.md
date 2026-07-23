# Smart Toll Plaza Automation System

![Java](https://img.shields.io/badge/Java-26-orange?style=for-the-badge\&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge\&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge\&logo=apachemaven)
![REST API](https://img.shields.io/badge/REST-API-005571?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/H2-Database-1E88E5?style=for-the-badge)
![API Gateway](https://img.shields.io/badge/API-Gateway-FF6F00?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

---

## Overview

A microservices-based Smart Toll Plaza Automation System built with Spring Boot. The application automates FASTag-based toll collection by validating vehicles, managing wallet balances, processing toll payments, and maintaining journey history through a centralized API Gateway.

---

## Architecture

```text
                Client (Postman)
                       │
                       ▼
              API Gateway (8080)
                       │
      ┌────────┬────────┬────────┬────────┐
      ▼        ▼        ▼        ▼
 Vehicle    Wallet     Toll    Journey
  API        API       API       API
 (8081)     (8082)    (8084)    (8083)
```

---

## Tech Stack

* Java 26
* Spring Boot
* Spring Data JPA
* REST APIs
* Spring Cloud API Gateway
* RestTemplate
* H2 Database
* Maven
* Postman

---

## Microservices

| Service     | Port |
| ----------- | ---- |
| API Gateway | 8080 |
| Vehicle API | 8081 |
| Wallet API  | 8082 |
| Journey API | 8083 |
| Toll API    | 8084 |

---

## Features

* Vehicle Registration
* FASTag Wallet Management
* Wallet Recharge
* Toll Payment Processing
* Journey History
* API Gateway Routing
* Bean Validation
* Global Exception Handling
* Inter-service Communication using RestTemplate

---

## Project Structure

```text
Smart-Toll-Plaza-Automation-System
├── APIGateway
├── VehicleAPI
├── WalletAPI
├── JourneyAPI
└── TollAPI
```

---

## Author

**Kishorekumar Saravanan**
