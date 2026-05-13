# Mini CRM

Simple REST API for managing customers and contacts. The project was built as a backend study with Spring Boot, validation, DTOs, in-memory persistence and unit tests.

## Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Validation
- H2 Database
- Lombok
- Springdoc OpenAPI / Swagger
- JUnit and Mockito

## Features

- Customer creation and listing
- Contact creation linked to customers
- Customer lookup with related contacts
- Input validation
- Exception handling with appropriate HTTP status codes
- Unit tests for the service layer

## API Docs

With the application running:

```text
http://localhost:8080/swagger-ui/index.html#/
```

## Running Locally

```bash
git clone https://github.com/MathSilvaDev/Mini-CRM.git
cd Mini-CRM
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

API:

```text
http://localhost:8080
```

H2 Console:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User: sa
Password: empty
```

## Testing

```bash
./mvnw test
```

## Project Structure

```text
src/main/java
`-- com/matheus/minicrm
    |-- customer
    |   |-- controller
    |   |-- service
    |   |-- repository
    |   |-- entity
    |   `-- dto
    `-- contact
        |-- repository
        |-- entity
        |-- enums
        `-- dto
```

## Author

Matheus Silva  
GitHub: [MathSilvaDev](https://github.com/MathSilvaDev)
