# Chamaa.API

<p align="center">
  <a href="https://github.com/flow-pie/chamaa.api/actions/workflows/build.yml">
    <img src="https://img.shields.io/github/actions/workflow/status/flow-pie/chamaa.api/build.yml?branch=main" alt="Build Status">
  </a>
  <a href="https://github.com/flow-pie/chamaa.api/issues">
    <img src="https://img.shields.io/github/issues/flow-pie/chamaa.api" alt="GitHub Issues">
  </a>
  <a href="https://github.com/flow-pie/chamaa.api/blob/main/LICENSE">
    <img src="https://img.shields.io/github/license/flow-pie/chamaa.api" alt="License">
  </a>
  <a href="https://discord.gg/chamaa">
    <img src="https://img.shields.io/discord/123456789" alt="Discord">
  </a>
</p>

> A community-based lending platform with blockchain integration for transparent and secure loan management in African communities.

## About

Chamaa.API is an open-source platform enabling community-driven savings groups (chamas) with transparent ledger systems, blockchain-secured transactions, and mobile-first access. The project implements a full-stack monorepo with a Spring Boot backend and Android mobile application.

## Features

- **User Management** - Registration, authentication, and profile management
- **Group Management** - Create and manage community lending groups (chamas)
- **Loan System** - Request, approve, and track loans within groups
- **Wallet & Transactions** - Deposit, withdraw, and transaction history
- **Blockchain Integration** - Polygon network integration for transparent transactions
- **Android Mobile App** - Native Android application with offline support
- **Payment Integration** - M-Pesa, Airtel Money, PayPal support (planned)

## Project Structure

```
chamaa.api/
├── apps/
│   ├── backend/                    # Spring Boot REST API
│   │   ├── src/main/java/com/chamaa/
│   │   │   ├── blockchain/         # Blockchain integration (Web3j)
│   │   │   ├── common/            # Shared utilities & exceptions
│   │   │   ├── config/            # Configuration classes
│   │   │   ├── controllers/       # REST API endpoints
│   │   │   ├── entities/          # JPA entities
│   │   │   ├── repositories/      # Data access layer
│   │   │   └── services/          # Business logic
│   │   └── pom.xml
│   │
│   └── android/
│       └── ChamaApp/              # Android mobile app
│           └── app/
│               └── src/main/java/com/example/chama/
│                   ├── network/   # Retrofit + OkHttp client
│                   ├── models/    # Data models
│                   └── ui/        # Jetpack Compose screens
│
├── docs/                          # Documentation
│   ├── api-spec.md               # API specification
│   ├── architecture.md           # Architecture overview
│   ├── blockchain.md             # Blockchain integration guide
│   ├── DATABASE_SETUP.md         # Database configuration
│   ├── DOCKER_GUIDE.md           # Docker deployment guide
│   └── CI_CD_PIPELINE.md         # CI/CD documentation
│
├── infra/                        # Infrastructure configurations
├── .github/                      # GitHub Actions workflows
├── README.md                     # This file
├── ROADMAP.md                    # Project roadmap
├── CONTRIBUTING.md               # Contribution guidelines
└── LICENSE                       # MIT License
```

## Tech Stack

### Backend
| Component | Technology |
|-----------|------------|
| Framework | Spring Boot 3.2 |
| Language | Java 17+ |
| Database | PostgreSQL (prod), H2 (dev) |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + Firebase Auth |
| Blockchain | Web3j (Polygon) |
| Build Tool | Maven |

### Android App
| Component | Technology |
|-----------|------------|
| Language | Kotlin |
| UI Framework | Jetpack Compose |
| Architecture | MVVM |
| Networking | Retrofit 2 + OkHttp |
| Local Database | Room |
| DI | Hilt (planned) |
| Build Tool | Gradle (Kotlin DSL) |

## Getting Started

### Prerequisites

| Tool | Version | Notes |
|------|---------|-------|
| Java | 17+ | Required for backend |
| Maven | 3.8+ | For backend build |
| Android Studio | Ladybug+ | For Android development |
| JDK | 11+ | For Android build |
| Git | 2.0+ | Version control |
| Docker | 24+ | Optional, for containerized deployment |

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/flow-pie/chamaa.api.git
   cd chamaa.api
   ```

2. **Configure environment variables**
   ```bash
   cp .env.example .env
   # Edit .env with your configuration
   ```

3. **Run the backend**
   ```bash
   cd apps/backend
   ./mvnw spring-boot:run
   ```

   The API will be available at `http://localhost:8080/api`

4. **Run tests**
   ```bash
   cd apps/backend
   ./mvnw clean test
   ```

### Android App Setup

1. **Open in Android Studio**
   ```
   apps/android/ChamaApp
   ```

2. **Build the debug APK**
   ```bash
   cd apps/android/ChamaApp
   ./gradlew assembleDebug
   ```

3. **Install on emulator/device**
   ```bash
   ./gradlew installDebug
   ```

   > **Note:** For emulator, the app connects to host machine at `10.0.2.2:8080`

### Running with Docker

```bash
# Backend only with H2 (development)
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up

# Full stack with PostgreSQL
docker-compose up
```

See [DOCKER_GUIDE.md](docs/DOCKER_GUIDE.md) for detailed Docker configuration.

## API Reference

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create new user |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users` | List all users |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

### Groups
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/groups` | Create new group |
| GET | `/api/groups/{id}` | Get group by ID |
| GET | `/api/groups` | List all groups |
| PUT | `/api/groups/{id}` | Update group |
| DELETE | `/api/groups/{id}` | Delete group |

### Loans
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/loans` | Request loan |
| GET | `/api/loans/{id}` | Get loan by ID |
| GET | `/api/loans` | List all loans |
| PUT | `/api/loans/{id}/approve` | Approve loan |
| PUT | `/api/loans/{id}/reject` | Reject loan |

### Wallets
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/wallets/{userId}` | Get wallet balance |
| POST | `/api/wallets/{userId}/deposit` | Deposit funds |
| POST | `/api/wallets/{userId}/withdraw` | Withdraw funds |
| GET | `/api/wallets/{userId}/transactions` | Transaction history |

### Request Example

```bash
# Create a user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "+254712345678"
  }'

# Create a group
curl -X POST http://localhost:8080/api/groups \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Community Savings",
    "description": "Monthly savings group",
    "creatorId": 1,
    "targetAmount": 10000.0
  }'

# Request a loan
curl -X POST http://localhost:8080/api/loans \
  -H "Content-Type: application/json" \
  -d '{
    "borrowerId": 1,
    "groupId": 1,
    "amount": 5000.0,
    "durationInMonths": 12,
    "purpose": "Business expansion"
  }'
```

## Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `PORT` | Server port | `8080` |
| `DB_URL` | PostgreSQL connection URL | - |
| `DB_USERNAME` | Database username | - |
| `DB_PASSWORD` | Database password | - |
| `JPA_DDL_AUTO` | JPA schema strategy | `create-drop` |
| `POLYGON_RPC_URL` | Polygon RPC endpoint | - |
| `POLYGON_CHAIN_ID` | Polygon chain ID | `80001` |
| `LOG_LEVEL_ROOT` | Root logging level | `INFO` |

### Profile-Specific Configuration

```bash
# Development (H2 in-memory)
./mvnw spring-boot:run

# Production (PostgreSQL)
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run
```

See [.env.example](.env.example) for all available configuration options.

## Development

### Code Style

- **Backend**: Follows Spring Boot conventions, Google Java Style
- **Android**: Kotlin coding conventions, Jetpack Compose guidelines

### Running Tests

```bash
# Backend unit tests
cd apps/backend
./mvnw test

# Backend tests with coverage report
./mvnw clean test jacoco:report

# Run specific test class
./mvnw test -Dtest=UserServiceTest

# Run specific test method
./mvnw test -Dtest=UserServiceTest#testCreateUser
```

### Database Schema

The application uses the following core entities:
- **User** - User accounts with authentication
- **Group** - Community lending groups
- **Loan** - Loan records within groups
- **Wallet** - User wallet with balance tracking
- **Transaction** - Transaction history

See [DATABASE_SETUP.md](docs/DATABASE_SETUP.md) for detailed schema documentation.

## Contributing

We welcome contributions! Please read our [Contributing Guidelines](CONTRIBUTING.md) before submitting PRs.

### Quick Start for Contributors

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Make your changes
4. Run tests and ensure code quality
5. Commit with conventional commits: `git commit -m "feat: add new feature"`
6. Push and create a Pull Request

### Issue Labels

| Label | Description |
|-------|-------------|
| `good first issue` | Beginner-friendly tasks |
| `help wanted` | Community assistance needed |
| `backend` | Backend/API work |
| `android` | Android app development |
| `blockchain` | Blockchain integration |
| `documentation` | Docs improvements |

### Commit Message Format

We follow [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>(<scope>): <description>

Types: feat, fix, docs, style, refactor, test, chore
```

Example:
```
feat(wallet): add transaction history endpoint
fix(api): resolve user authentication bug
docs(readme): update setup instructions
```

## Roadmap

The project is organized into 8 phases. See [ROADMAP.md](ROADMAP.md) for detailed progress and future plans.

| Phase | Focus | Status |
|-------|-------|--------|
| 1 | Project Setup | Complete |
| 2 | Infrastructure | In Progress |
| 3 | Core API Features | Planned |
| 4 | Blockchain Layer | Planned |
| 5 | Governance & Voting | Planned |
| 6 | Mobile App (Android) | In Progress |
| 7 | DevOps & Scaling | Planned |
| 8 | Long-term Goals | Planned |

## Resources

- 📖 [API Documentation](docs/api-spec.md)
- 🏗️ [Architecture Guide](docs/architecture.md)
- ⛓️ [Blockchain Integration](docs/blockchain.md)
- 🐳 [Docker Guide](docs/DOCKER_GUIDE.md)
- 📊 [Database Setup](docs/DATABASE_SETUP.md)
- 🔄 [CI/CD Pipeline](docs/CI_CD_PIPELINE.md)

## Community

- 💬 [Discord](https://discord.gg/chamaa)
- 💻 [GitHub Discussions](https://github.com/flow-pie/chamaa.api/discussions)
- 🐛 [Issue Tracker](https://github.com/flow-pie/chamaa.api/issues)

## License

This project is licensed under the MIT License - see [LICENSE](LICENSE) for details.

## Acknowledgments

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Web3j](https://web3j.io/)
- All contributors and community members

---

<p align="center">
  Built with ❤️ by the Chamaa community
</p>
