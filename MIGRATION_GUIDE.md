# Spring Boot Migration Guide

## What's Been Done

✅ Created Spring Boot multi-module Maven structure:
- **Root pom.xml**: Parent POM with Spring Boot 3.2.0, Java 17
- **modules/core**: Shared domain models, base exceptions, utilities
- **modules/api**: REST API controllers, services, DTOs
- **modules/blockchain**: Blockchain integration services

✅ Package structure:
- Core: `com.chamaa.core.{config,domain,exception,util}`
- API: `com.chamaa.api.{controller,service,dto,config}`
- Blockchain: `com.chamaa.blockchain.{service,contract,util}`

✅ Generated files:
- Spring Boot application entry point
- Application configuration (application.yml)
- Base entity class with JPA auditing
- Custom exception handling
- Health check endpoint
- Maven wrapper configuration

## Migration Steps

### Step 1: Database Setup
Update `modules/api/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/chamaa_db
    username: your_user
    password: your_password
```

### Step 2: Migrate Domain Models
Move/convert TypeScript models from `packages/shared` to:
- `modules/core/src/main/java/com/chamaa/core/domain/`

Example pattern:
```java
@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {
    @Column(nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String name;
}
```

### Step 3: Migrate API Endpoints
Convert NestJS controllers to Spring Boot:
- `modules/api/src/main/java/com/chamaa/api/controller/`
- Services: `modules/api/src/main/java/com/chamaa/api/service/`
- DTOs: `modules/api/src/main/java/com/chamaa/api/dto/`

### Step 4: Migrate Blockchain Logic
Move blockchain utilities to:
- `modules/blockchain/src/main/java/com/chamaa/blockchain/`

### Step 5: Dependencies
Add to module pom.xml as needed:
```xml
<!-- Database -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<!-- Web3j for blockchain -->
<dependency>
    <groupId>org.web3j</groupId>
    <artifactId>core</artifactId>
    <version>4.10.0</version>
</dependency>
```

## Build & Deploy

```bash
# Clean build
mvn clean install

# Run API server
mvn spring-boot:run -pl modules/api

# Run tests
mvn test

# Package
mvn package -DskipTests
```

## Legacy Files

The following can be removed once migration is complete:
- `apps/` (old NestJS structure)
- `packages/` (old TypeScript utilities)
- `pnpm-lock.yaml`
- `pnpm-workspace.yaml`
- `tsconfig.base.json`
- `turbo.json`

For now, they're kept for reference during transition.

## Current Branch

You're on branch: `springboot-restructure`
Release branch is untouched: `release`

