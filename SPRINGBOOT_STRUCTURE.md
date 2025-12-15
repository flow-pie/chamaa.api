# Chamaa API - Spring Boot Structure

## Project Structure

```
chamaa.api/
├── modules/
│   ├── core/                    # Shared core module
│   │   ├── src/main/java/com/chamaa/core/
│   │   │   ├── config/         # Core configurations
│   │   │   ├── domain/         # Domain models & entities
│   │   │   ├── exception/      # Custom exceptions
│   │   │   └── util/           # Utilities
│   │   └── pom.xml
│   │
│   ├── api/                     # REST API module (main application)
│   │   ├── src/main/java/com/chamaa/api/
│   │   │   ├── controller/     # REST controllers
│   │   │   ├── service/        # Business logic
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   └── config/         # API configurations
│   │   ├── src/main/resources/
│   │   │   └── application.yml # Configuration
│   │   └── pom.xml
│   │
│   └── blockchain/             # Blockchain integration module
│       ├── src/main/java/com/chamaa/blockchain/
│       │   ├── service/        # Blockchain services
│       │   ├── contract/       # Smart contract interfaces
│       │   └── util/           # Blockchain utilities
│       └── pom.xml
│
├── docs/                        # Documentation
├── pom.xml                      # Root parent POM
└── .gitignore

```

## Build & Run

### Prerequisites
- Java 17+
- Maven 3.8+

### Build
```bash
mvn clean install
```

### Run API
```bash
mvn spring-boot:run -pl modules/api
```

### Run Tests
```bash
mvn test
```

## Module Dependencies

- **core**: No dependencies on other modules (foundation)
- **api**: Depends on core
- **blockchain**: Depends on core

## Configuration

See `modules/api/src/main/resources/application.yml` for application configuration.

## Next Steps

1. Migrate TypeScript models from `packages/shared` to `modules/core`
2. Migrate NestJS API routes to `modules/api/controller`
3. Migrate blockchain logic to `modules/blockchain`
4. Update database configurations
5. Set up proper logging and error handling

