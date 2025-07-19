# FinTrack API

A RESTful API for managing personal financial accounts and transactions.

## Technologies

- Java 21+
- Spring Boot
- Spring Data JPA
- Maven
- H2/MySQL (configurable)
- Lombok
- Spring Security (with password encryption)

## Features

- CRUD operations for user-linked accounts
- Transaction management per account
- Secure password storage using BCrypt (`PasswordEncoder`)
- DTOs for safe data exposure
- REST endpoints ready for frontend integration