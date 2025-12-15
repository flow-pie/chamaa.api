# Chamaa API Architecture

## Overview
Chamaa API is a Spring Boot REST API for a community-based lending platform built with:
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- PostgreSQL Database
- Blockchain Integration (Polygon)

## Project Structure

```
src/
├── main/
│   ├── java/com/chamaa/
│   │   ├── ChamaaApplication.java           # Entry point
│   │   ├── config/                          # Configuration classes
│   │   ├── common/                          # Shared utilities
│   │   ├── entities/                        # JPA entities
│   │   ├── repositories/                    # Data access layer
│   │   ├── services/                        # Business logic
│   │   ├── controllers/                     # REST endpoints
│   │   └── blockchain/                      # Blockchain services
│   └── resources/
│       ├── application.properties           # App configuration
│       └── db/schema.sql                    # Database schema
└── test/
    └── java/com/chamaa/                     # Unit tests
```

## Design Patterns

### Layered Architecture
- **Controllers**: Handle HTTP requests/responses
- **Services**: Implement business logic
- **Repositories**: Manage database access
- **Entities**: Represent domain models

### Key Features
- JPA Auditing (createdAt, updatedAt timestamps)
- Password encryption using BCrypt
- RESTful API design
- Transactional operations
- Database relationships with proper foreign keys

## Technology Stack
- **Framework**: Spring Boot 3.2.0
- **ORM**: Hibernate/JPA
- **Database**: PostgreSQL 12+
- **Authentication**: Spring Security (BCrypt)
- **Blockchain**: Web3j (Polygon integration)
- **Testing**: JUnit 5
- **Build**: Maven 3.8+

## Database Schema

### Tables
- **users**: User accounts and profiles
- **groups**: Community groups for lending
- **wallets**: User wallet balances
- **transactions**: Transaction history
- **loans**: Loan records with status tracking

## API Response Format

### Success Response
```json
{
  "id": 1,
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### Error Response
```json
{
  "error_code": "VALIDATION_ERROR",
  "message": "Invalid request",
  "status_code": 400,
  "timestamp": "2024-01-01T10:00:00"
}
```
