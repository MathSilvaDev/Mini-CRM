# Mini CRM - Customer & Contact Management API

This project is a Spring Boot application developed as a simple 
CRM (Customer Relationship Management) system. 
It provides RESTful APIs to manage customers and their contacts,
following good backend practices such as layered architecture, 
DTO usage, validation, and unit testing.

## Technologies

* **Spring Boot 3**
* **Java 21**
* **Spring Data JPA**
* **H2 Database (in-memory)**
* **Spring Validation**
* **Lombok**
* **Springdoc OpenAPI (Swagger)**
* **JUnit & Mockito (Unit Tests)**

## Features

* Create and manage customers
* Add contacts to customers
* Retrieve customers with their contacts
* Input validation
* Exception handling with proper HTTP status codes
* Layered architecture (Controller → Service → Repository → Entity)
* DTO pattern for request/response isolation
* Unit tests using Mockito and JUnit

## ▶️ Running the Application

Clone the repository:

```
git clone https://github.com/MathSilvaDev/Mini-CRM.git
```

Navigate to the project folder:

```
cd Mini-CRM
```

Build the project:

```
mvn clean install
```

Run the application:

```
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

## API Documentation (Swagger)

Access the interactive API documentation:

```
http://localhost:8080/swagger-ui/index.html#/
```

## H2 Database Console

Access the in-memory database:

```
http://localhost:8080/h2-console
```

**JDBC URL:**

```
jdbc:h2:mem:testdb
```

**User:**

```
sa
```

**Password:**

```
(empty)
```

## 🔗 API Endpoints

### Customer Endpoints

* **POST** `/api/customers`
  Create a new customer

* **GET** `/api/customers`
  Retrieve all customers with their contacts

* **GET** `/api/customers/{id}`
  Retrieve a customer by ID (with contacts)

* **POST** `/api/customers/{id}/add-contact`
  Add a new contact to a customer

## Architecture

The project follows a clean layered structure:

* **Controller** → Handles HTTP requests
* **Service** → Business logic
* **Repository** → Data access (JPA)
* **Entity** → Database models
* **DTOs** → Request and response separation

## Testing

Unit tests were implemented using:

* **JUnit**
* **Mockito**

Covering service layer logic and ensuring business rules behave as expected.

## Author

Developed by Matheus Silva as part of backend studies and portfolio projects.
