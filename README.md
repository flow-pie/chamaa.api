# Chamaa API

A community-based lending platform REST API built with Spring Boot, featuring blockchain integration for secure loan management.

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL 12+

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd chamaa-api
```

2. Configure database in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/chamaa_db
spring.datasource.username=postgres
spring.datasource.password=your_password
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

## 📚 Documentation

- [API Specification](docs/api-spec.md) - Complete API endpoints documentation
- [Architecture](docs/architecture.md) - Project structure and design patterns
- [Blockchain Integration](docs/blockchain.md) - Blockchain service details

## 🏗️ Project Structure

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

### Application Properties
```properties
# Server
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/chamaa_db
spring.datasource.username=root
spring.datasource.password=

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Blockchain
blockchain.polygon.rpc-url=https://polygon-rpc.com
blockchain.polygon.chain-id=137
```

## 📊 Database

The application uses PostgreSQL with the following tables:
- **users** - User accounts
- **groups** - Community lending groups
- **wallets** - User wallets
- **transactions** - Transaction history
- **loans** - Loan records

Database schema is auto-initialized on startup.

## 🔐 Security

- Passwords encrypted using BCrypt
- Spring Security integration
- JWT token support (to be implemented)
- API request validation

## 🧪 Testing

Run unit tests:
```bash
./mvnw test
```

Run all tests with coverage:
```bash
./mvnw clean test jacoco:report
```

## 📝 API Examples

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

## 🔄 CI/CD

GitHub Actions workflow for:
- Build verification
- Unit tests
- Code quality checks
- Automated deployment

## 🌐 Blockchain Integration

Built with Polygon network support via Web3j:
- Smart contract deployment
- Transaction recording
- Loan agreement execution
- Payment verification

## 📦 Dependencies

- Spring Boot 3.2.0
- Spring Data JPA
- Spring Security
- MySQL Connector
- Lombok
- Web3j
- JUnit 5
- Springdoc OpenAPI (Swagger)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see LICENSE file for details.

## 📧 Contact

For questions or support, reach out to the development team.

## 🗺️ Roadmap

- [ ] JWT Authentication
- [ ] Role-based Access Control
- [ ] API Documentation (Swagger UI)
- [ ] Payment gateway integration
- [ ] Mobile app API
- [ ] Advanced analytics
- [ ] Loan calculator service
- [ ] Automated loan approval

---

**Status**: Under Development 🚧
