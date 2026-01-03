# PostgreSQL + Spring Data JPA (Hibernate) Setup Guide

This document provides comprehensive guidance on the database configuration, entity modeling, and migration strategy implemented for the Chamaa API.

## Overview

The Chamaa API uses a robust, production-ready database setup with:

- **PostgreSQL** as the primary relational database for production and staging
- **H2** for development and testing (in-memory, PostgreSQL-compatible mode)
- **Spring Data JPA** with Hibernate ORM for object-relational mapping
- **HikariCP** connection pooling for efficient resource management
- **Flyway** for versioned database migrations

## Architecture

### Database Profiles

The application supports three Spring profiles for environment-specific configuration:

#### 1. **Development (`dev` profile)**
- **Database**: H2 (in-memory)
- **DDL Auto**: `validate` (validates schema exists)
- **Flyway**: Disabled (H2 handles schema in startup)
- **Configuration**: `application-dev.properties`
- **Connection Pool**: 5 max connections, 2 minimum idle
- **SQL Logging**: DEBUG level for development debugging

#### 2. **Production (`prod` profile)**
- **Database**: PostgreSQL
- **DDL Auto**: `validate` (no auto schema changes)
- **Flyway**: Enabled (applies versioned migrations)
- **Configuration**: `application-prod.properties`
- **Connection Pool**: 20 max connections, 10 minimum idle
- **SQL Logging**: WARN level for performance

#### 3. **Testing**
- Uses H2 in-memory database
- Spring Test Context handles schema setup

## Configuration

### Environment Variables

Key environment variables for database configuration:

```bash
# Database Connection
DB_URL=jdbc:postgresql://localhost:5432/chamaa_db
DB_USERNAME=postgres
DB_PASSWORD=your_secure_password

# Connection Pool (HikariCP)
DB_POOL_SIZE=20           # Maximum pool size
DB_MIN_IDLE=10            # Minimum idle connections

# JPA/Hibernate
JPA_DDL_AUTO=validate     # validate | update | create | create-drop
JPA_DIALECT=org.hibernate.dialect.PostgreSQLDialect
JPA_SHOW_SQL=false        # Enable SQL logging

# Flyway Migrations
FLYWAY_ENABLED=true       # Enable/disable migration runner

# Logging
LOG_LEVEL_HIBERNATE=WARN  # Hibernate logging level
```

### Application Properties

#### application.properties (Default - H2)
```properties
# Uses H2 in-memory database
# Configured with PostgreSQL mode for compatibility testing
spring.datasource.url=jdbc:h2:mem:chamaadb;MODE=PostgreSQL;...
spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=false
```

#### application-dev.properties (Development)
```properties
# H2 in-memory with H2 console enabled
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.show-sql=false
logging.level.org.hibernate.SQL=DEBUG
```

#### application-prod.properties (Production)
```properties
# PostgreSQL with Flyway migrations
spring.datasource.url=jdbc:postgresql://...
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.flyway.enabled=true
```

## Entity Model

### JPA Entities

All entities inherit from `BaseEntity` which provides:
- Auto-generated Long ID (IDENTITY strategy)
- Audit timestamps (`createdAt`, `updatedAt`)
- JPA Auditing integration

### Entities Overview

#### 1. **User**
Primary user entity for the platform.

```java
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_users_email", columnList = "email", unique = true),
    @Index(name = "idx_users_phone_number", columnList = "phone_number", unique = true),
    @Index(name = "idx_users_wallet_address", columnList = "wallet_address"),
    @Index(name = "idx_users_is_active", columnList = "is_active")
})
public class User extends BaseEntity {
    @Column(nullable = false, unique = true, length = 255)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @Column(nullable = false, length = 100)
    private String firstName;
    
    @Column(nullable = false, length = 100)
    private String lastName;
    
    @Column(unique = true, length = 20)
    private String phoneNumber;
    
    @Column(length = 500)
    private String profileImageUrl;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    @Column(length = 255)
    private String walletAddress;
}
```

**Relationships**:
- One-to-One: User ↔ Wallet (inverse side)
- One-to-Many: User → Groups (as creator)
- One-to-Many: User → Loans (as borrower)

**Constraints**:
- Unique: email, phoneNumber
- Unique constraint indexed: `uc_users_email`, `uc_users_phone_number`

---

#### 2. **Wallet**
Represents user's balance and transaction account.

```java
@Entity
@Table(name = "wallets", indexes = {
    @Index(name = "idx_wallets_user_id", columnList = "user_id", unique = true),
    @Index(name = "idx_wallets_wallet_address", columnList = "wallet_address", unique = true),
    @Index(name = "idx_wallets_is_active", columnList = "is_active")
})
public class Wallet extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
    
    @Column(nullable = false)
    private Double balance = 0.0;
    
    @Column(unique = true, length = 255)
    private String walletAddress;
    
    @Column(nullable = false, length = 10)
    private String currency = "USD";
    
    @Column(nullable = false)
    private Boolean isActive = true;
}
```

**Relationships**:
- One-to-One: Wallet ← User (owning side)
- One-to-Many: Wallet → Transactions (as fromWallet)
- One-to-Many: Wallet → Transactions (as toWallet)

**Constraints**:
- Foreign Key: `user_id` (UNIQUE, NOT NULL)
- Unique: walletAddress

---

#### 3. **Group**
Represents a lending group with members.

```java
@Entity
@Table(name = "groups", indexes = {
    @Index(name = "idx_groups_creator_id", columnList = "creator_id"),
    @Index(name = "idx_groups_is_active", columnList = "is_active"),
    @Index(name = "idx_groups_name", columnList = "name")
})
public class Group extends BaseEntity {
    @Column(nullable = false, length = 255)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;
    
    @Column(nullable = false)
    private Integer memberCount = 0;
    
    @Column(nullable = false)
    private Boolean isActive = true;
    
    @Column(nullable = false)
    private Double targetAmount = 0.0;
    
    @Column(length = 500)
    private String groupImageUrl;
}
```

**Relationships**:
- Many-to-One: Group ← User (creator, owning side)
- One-to-Many: Group → Loans
- One-to-Many: Group → Transactions

**Constraints**:
- Foreign Key: `creator_id` (NOT NULL, CASCADE delete)
- Index: `idx_groups_name`

---

#### 4. **Transaction**
Records all fund transfers between wallets.

```java
@Entity
@Table(name = "transactions", indexes = {
    @Index(name = "idx_transactions_from_wallet", columnList = "from_wallet_id"),
    @Index(name = "idx_transactions_to_wallet", columnList = "to_wallet_id"),
    @Index(name = "idx_transactions_group", columnList = "group_id"),
    @Index(name = "idx_transactions_status", columnList = "status"),
    @Index(name = "idx_transactions_created_at", columnList = "created_at")
})
public class Transaction extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet_id", nullable = false)
    private Wallet fromWallet;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet_id", nullable = false)
    private Wallet toWallet;
    
    @Column(nullable = false)
    private Double amount;
    
    @Column(nullable = false, length = 50)
    private String status = "PENDING";
    
    @Column(length = 255)
    private String transactionHash;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
}
```

**Relationships**:
- Many-to-One: Transaction → Wallet (fromWallet, owning side)
- Many-to-One: Transaction → Wallet (toWallet, owning side)
- Many-to-One: Transaction → Group (optional, owning side)

**Constraints**:
- Foreign Keys: `from_wallet_id`, `to_wallet_id` (RESTRICT delete - prevent removing wallets in use)
- Foreign Key: `group_id` (SET NULL on cascade - optional relationship)
- Status values: PENDING, COMPLETED, FAILED

---

#### 5. **Loan**
Represents a loan request within a group.

```java
@Entity
@Table(name = "loans", indexes = {
    @Index(name = "idx_loans_borrower_id", columnList = "borrower_id"),
    @Index(name = "idx_loans_group_id", columnList = "group_id"),
    @Index(name = "idx_loans_status", columnList = "status"),
    @Index(name = "idx_loans_created_at", columnList = "created_at")
})
public class Loan extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id", nullable = false)
    private User borrower;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
    
    @Column(nullable = false)
    private Double amount;
    
    @Column(nullable = false)
    private Double interestRate = 0.0;
    
    @Column(nullable = false, length = 50)
    private String status = "PENDING";
    
    @Column(columnDefinition = "TEXT")
    private String purpose;
    
    @Column(nullable = false)
    private Integer durationInMonths;
    
    @Column(nullable = false)
    private Double paidAmount = 0.0;
}
```

**Relationships**:
- Many-to-One: Loan → User (borrower, owning side)
- Many-to-One: Loan → Group (owning side)

**Constraints**:
- Foreign Keys: `borrower_id`, `group_id` (CASCADE delete)
- Status values: PENDING, APPROVED, ACTIVE, COMPLETED, REJECTED, DEFAULTED

---

## Flyway Database Migrations

### Migration File Structure

Flyway migrations are located in `src/main/resources/db/migration/`.

**Naming Convention**: `V{VERSION}__{DESCRIPTION}.sql`

Examples:
- `V1__Initial_schema.sql` - Core tables and relationships
- `V2__Add_audit_columns.sql` - Additional audit fields
- `V3__Create_views.sql` - Database views

### V1__Initial_schema.sql

The initial migration creates:

1. **Users Table**
   ```sql
   CREATE TABLE users (
       id BIGSERIAL PRIMARY KEY,
       email VARCHAR(255) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       first_name VARCHAR(100) NOT NULL,
       last_name VARCHAR(100) NOT NULL,
       phone_number VARCHAR(20) UNIQUE,
       profile_image_url VARCHAR(500),
       is_active BOOLEAN DEFAULT true NOT NULL,
       wallet_address VARCHAR(255),
       created_at TIMESTAMP NOT NULL,
       updated_at TIMESTAMP NOT NULL
   );
   ```

2. **Groups Table** (Foreign Key to Users)
3. **Wallets Table** (One-to-One with Users)
4. **Transactions Table** (Foreign Keys to Wallets, optional Group)
5. **Loans Table** (Foreign Keys to Users, Groups)

### Indexes and Constraints

The migration includes:
- **Primary Indexes**: On all ID fields
- **Unique Indexes**: Email, phone number, wallet address
- **Performance Indexes**: On frequently queried columns
- **Foreign Key Constraints**: With appropriate cascade/restrict policies

### Running Migrations

**Automatically (Production)**:
```bash
# Flyway runs on application startup
SPRING_PROFILES_ACTIVE=prod mvn spring-boot:run
```

**Manually**:
```bash
# Show migration status
mvn flyway:info

# Validate migrations
mvn flyway:validate

# Repair (remove failed migrations)
mvn flyway:repair

# Reset (DESTRUCTIVE - use only in dev!)
mvn flyway:clean
```

## Hibernate Configuration

### Key Hibernate Properties

```properties
# Dialect - tells Hibernate how to generate SQL for PostgreSQL
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# DDL Auto - prevent automatic schema changes in production
spring.jpa.hibernate.ddl-auto=validate

# Batch Processing - improves performance for bulk operations
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.jdbc.fetch_size=50

# Query Optimization
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

# Connection Management
spring.jpa.open-in-view=false  # Prevent N+1 query problems
```

### Lazy Loading Strategy

All associations use `FetchType.LAZY` to prevent unnecessary data loading:

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "creator_id")
private User creator;
```

**Benefits**:
- Reduces memory footprint
- Prevents N+1 query problems
- Improves response time

**Usage**:
```java
// Load group without creator
Group group = groupRepository.findById(1L).get();

// Lazy loading happens here (separate query)
User creator = group.getCreator();
```

## Connection Pooling (HikariCP)

### Configuration

```properties
# Pool sizing for production
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=10

# Timeouts and cleanup
spring.datasource.hikari.idle-timeout=600000          # 10 minutes
spring.datasource.hikari.max-lifetime=1800000         # 30 minutes
spring.datasource.hikari.connection-timeout=30000     # 30 seconds
```

### Monitoring

Check pool status:
```java
@GetMapping("/health/db-pool")
public ResponseEntity<Map<String, Object>> checkPool() {
    // Pool statistics available via Spring Boot Actuator
    return ResponseEntity.ok(Map.of(
        "active_connections", hikariPool.getActiveConnections(),
        "idle_connections", hikariPool.getIdleConnections(),
        "pending_threads", hikariPool.getPendingThreads()
    ));
}
```

## Best Practices

### 1. Always Use Lazy Loading
```java
// ✅ Good - Lazy loaded
@ManyToOne(fetch = FetchType.LAZY)
private User creator;

// ❌ Avoid - Eager loading
@ManyToOne(fetch = FetchType.EAGER)
private User creator;
```

### 2. Use Proper Column Constraints
```java
// ✅ Good - Clear constraints
@Column(nullable = false, length = 100)
private String firstName;

// ❌ Avoid - No constraints
private String firstName;
```

### 3. Index Frequently Queried Columns
```java
// ✅ Good - Indexed for searches
@Index(name = "idx_users_email", columnList = "email", unique = true)

// ❌ Avoid - Missing indexes on WHERE clauses
```

### 4. Use Proper Delete Strategies
```java
// ✅ Good - Cascades: Delete user → deletes wallet
@JoinColumn(name = "user_id", nullable = false)
private User user;

// ✅ Good - Restricts: Can't delete wallet in transaction
@JoinColumn(name = "from_wallet_id")
private Wallet fromWallet;

// ✅ Good - Nullifies: Delete group → nulls group_id in transaction
@JoinColumn(name = "group_id")
private Group group;
```

### 5. Optimize N+1 Queries

Use JPA projections or explicit joins:

```java
// ❌ N+1 Problem
List<Group> groups = groupRepository.findAll();
for (Group group : groups) {
    User creator = group.getCreator(); // Separate query per group!
}

// ✅ Solution: Use EntityGraph
@EntityGraph(attributePaths = {"creator"})
List<Group> findAll();
```

## Deployment

### Development Setup

```bash
# Start with H2 (default)
./mvnw spring-boot:run

# Or explicitly:
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
```

### Production Setup

1. **Provision PostgreSQL**:
   ```bash
   docker run -d \
     -e POSTGRES_DB=chamaa_db \
     -e POSTGRES_PASSWORD=secure_password \
     -p 5432:5432 \
     postgres:16-alpine
   ```

2. **Set Environment Variables**:
   ```bash
   export SPRING_PROFILES_ACTIVE=prod
   export DB_URL=jdbc:postgresql://localhost:5432/chamaa_db
   export DB_USERNAME=postgres
   export DB_PASSWORD=secure_password
   export DB_POOL_SIZE=20
   export FLYWAY_ENABLED=true
   ```

3. **Start Application**:
   ```bash
   ./mvnw spring-boot:run
   # Flyway automatically runs V1 migration on first startup
   ```

### Docker Deployment

The application includes Docker Compose configurations:

```bash
# Production deployment
docker-compose up -d

# Development deployment (with hot-reload)
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d
```

## Troubleshooting

### Migration Failed

```bash
# Check migration history
mvn flyway:info

# Repair broken migration
mvn flyway:repair

# Validate all migrations
mvn flyway:validate
```

### Connection Issues

```bash
# Verify PostgreSQL is running
psql -h localhost -U postgres -d chamaa_db

# Check connection in logs
tail -f logs/application.log | grep "Connection"
```

### Lazy Loading Issues

```
LazyInitializationException: could not initialize proxy
```

**Solution**: Load related entities within transaction:
```java
@Transactional
public GroupDTO getGroupWithCreator(Long id) {
    Group group = groupRepository.findById(id).get();
    group.getCreator().getName(); // Initialize within transaction
    return mapper.toDTO(group);
}
```

## Resources

- [Flyway Documentation](https://flywaydb.org/documentation/)
- [Hibernate ORM Guide](https://hibernate.org/orm/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/reference/)
- [HikariCP Configuration](https://github.com/brettwooldridge/HikariCP#configuration)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
