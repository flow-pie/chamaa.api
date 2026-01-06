# Chamaa API

A community-based lending platform REST API built with Spring Boot, featuring blockchain integration for secure loan management.

## Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 12+

### Installation

1. Clone the repository:
```bash
git clone https://github.com/flow-pie/chamaa.api
cd chamaa.api
```

2. Configure environment variables:
```bash
# Copy the example environment file
cp .env.example .env

# Edit .env with your local configuration
nano .env
```

3. Build the project:
```bash
./mvnw clean install
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080/api`

## Documentation

- [API Specification](docs/api-spec.md) - Complete API endpoints documentation
- [Architecture](docs/architecture.md) - Project structure and design patterns
- [Blockchain Integration](docs/blockchain.md) - Blockchain service details

## Project Structure

```
chamaa-api/
├── pom.xml                                    # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/chamaa/
│   │   │   ├── ChamaaApplication.java        # Main application entry
│   │   │   ├── config/                       # Configuration classes
│   │   │   ├── common/                       # Shared utilities
│   │   │   ├── entities/                     # JPA entities
│   │   │   ├── repositories/                 # Data access layer
│   │   │   ├── services/                     # Business logic
│   │   │   ├── controllers/                  # REST endpoints
│   │   │   └── blockchain/                   # Blockchain services
│   │   └── resources/
│   │       ├── application.properties        # Configuration
│   │       └── db/schema.sql                 # Database schema
│   └── test/
│       └── java/com/chamaa/                  # Unit tests
├── docs/
│   ├── api-spec.md                          # API documentation
│   ├── architecture.md                      # Architecture guide
│   └── blockchain.md                        # Blockchain integration
└── README.md
```

## 🔧 Configuration

### Environment Variables
All sensitive configuration is managed through environment variables. Copy `.env.example` to `.env` and configure your values:

```bash
cp .env.example .env
```

**Core Environment Variables:**
- `PORT` - Server port (default: 8080)
- `DB_URL` - PostgreSQL connection URL
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `JPA_DDL_AUTO` - JPA DDL strategy (validate, update, create, create-drop)
- `POLYGON_RPC_URL` - Polygon RPC endpoint
- `POLYGON_CHAIN_ID` - Polygon chain ID

**Logging Levels:**
- `LOG_LEVEL_ROOT` - Root logging level
- `LOG_LEVEL_CHAMAA` - Chamaa application logging level
- `LOG_LEVEL_SPRING_WEB` - Spring Web logging level
- `LOG_LEVEL_HIBERNATE` - Hibernate logging level

**⚠️ Security Notes:**
- **Never commit `.env` file** - it's excluded in `.gitignore`
- **Always use `.env.example`** for defaults and documentation
- In production, use a secrets manager (Vault, AWS Secrets Manager, etc.)
- Environment variables override `.env` file values

## Database

The application uses a sophisticated database setup with support for both development and production environments:

### Databases Supported
- **PostgreSQL** (Production & Staging) - Relational database with full ACID compliance
- **H2** (Development & Testing) - In-memory database with PostgreSQL mode for compatibility

### Key Features
- **Spring Data JPA** with Hibernate ORM for object-relational mapping
- **HikariCP** connection pooling for efficient resource management
- **Flyway** database migrations for versioned schema changes
- **Lazy loading** on all relationships to prevent N+1 queries
- **Strategic indexes** on frequently queried columns for performance
- **Cascade/Restrict delete** policies for data integrity

### Tables
- **users** - User accounts with validation
- **groups** - Community lending groups
- **wallets** - User wallets with balance tracking
- **transactions** - Transaction history between wallets
- **loans** - Loan records within groups

### Running with Different Databases

**Development (H2 - Default)**
```bash
./mvnw spring-boot:run
# Uses H2 in-memory database
# Schema auto-created with create-drop strategy
```

**Development (with Docker PostgreSQL)**
```bash
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up
# Starts PostgreSQL in Docker for realistic testing
```

**Production (PostgreSQL)**
```bash
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run
# Requires FLYWAY_ENABLED=true for migration execution
# Uses validate strategy (no auto schema changes)
```

### Database Configuration
- See [DATABASE_SETUP.md](docs/DATABASE_SETUP.md) for comprehensive database documentation
- Configure via environment variables: `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`
- Connection pool settings: `DB_POOL_SIZE`, `DB_MIN_IDLE`

## Security

- Passwords encrypted using BCrypt
- Spring Security integration
- JWT token support (to be implemented)
- API request validation

## Testing

Run unit tests:
```bash
./mvnw test
```

Run all tests with coverage:
```bash
./mvnw clean test jacoco:report
```

### Database Testing
The application includes comprehensive tests with:
- H2 in-memory database for unit tests
- JPA entity mapping validation
- Service layer business logic tests
- All tests use PostgreSQL-compatible H2 mode

## API Examples

### Create User
```bash
POST /api/users
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+254712345678"
}
```

### Create Group
```bash
POST /api/groups
Content-Type: application/json

{
  "name": "Community Savings",
  "description": "Group for community savings",
  "creatorId": 1,
  "targetAmount": 10000.0
}
```

### Request Loan
```bash
POST /api/loans
Content-Type: application/json

{
  "borrowerId": 1,
  "groupId": 1,
  "amount": 5000.0,
  "durationInMonths": 12,
  "purpose": "Business expansion"
}
```

## CI/CD

GitHub Actions workflow for:
- Build verification
- Unit tests
- Code quality checks
- Automated deployment

## Blockchain Integration

Built with Polygon network support via Web3j:
- Smart contract deployment
- Transaction recording
- Loan agreement execution
- Payment verification

## Dependencies

- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- MySQL Connector
- Lombok
- Web3j
- JUnit 5
- Springdoc OpenAPI (Swagger)

