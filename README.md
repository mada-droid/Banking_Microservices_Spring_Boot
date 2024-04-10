# Banking Microservices - Spring Boot Web App

This GitHub repository hosts a banking microservices web application developed using Spring Boot. The project comprises several microservices that collectively manage various aspects of a banking system, including account management, authentication, transactions, SQL script creation and manipulation, Eureka Server for API development, and an API Gateway for routing requests.

## Microservices:

### 1. Account Microservice:
The Account microservice handles all functionalities related to bank accounts. It includes features such as creating new accounts, retrieving account details, updating account information, and managing transactions associated with each account.

### 2. Authentication Microservice:
The Authentication microservice is responsible for handling user authentication and signup processes. It ensures secure authentication of clients/users accessing the banking system and manages user registration.

### 3. Transaction Microservice:
The Transaction microservice manages all financial transactions within the banking system. It handles processes such as transferring funds between accounts, depositing and withdrawing money, and maintaining transaction history.

## 4. SQL Script Management Microservice:
The SQL Script Management microservice is responsible for the creation and manipulation of SQL scripts. It provides functionality to generate and modify SQL scripts for database operations, ensuring efficient management of the banking system's data.

## 5. Eureka Server:
The Eureka Server is used for service registration and discovery. It improves the development process by allowing microservices to locate and communicate with each other easily, facilitating seamless integration and scalability.

## 6. API Gateway:
The API Gateway acts as a single entry point for all client requests. It handles routing and load balancing across microservices, providing a unified interface for clients to interact with the banking system's functionalities.

## Installation and Setup:

1. Clone the repository:
   ```
   git clone https://github.com/mada-driod/Banking_Microservices_Spring_Boot.git
   ```

2. Configure and run each microservice individually by following their respective setup instructions provided in their directories.

3. Start the Eureka Server and API Gateway microservices first to enable service registration and request routing.

4. Deploy the remaining microservices (Account, Authentication, Transaction, SQL Script Management) and ensure they register with the Eureka Server.

5. Access the web application's features through the API Gateway at the designated endpoint.

