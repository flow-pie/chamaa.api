# Architecture

## Overview

Chamaa.API is a monorepo containing a Spring Boot backend and Android mobile application. The project uses a modular architecture with clear separation of concerns.

## Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                        Android App                          │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────────────┐ │
│  │   UI Layer  │  │ ViewModel   │  │   Network Layer   │ │
│  │  (Compose)  │  │   (MVVM)    │  │  (Retrofit+OkHttp) │ │
│  └─────────────┘  └─────────────┘  └─────────────────────┘ │
└─────────────────────────────────────────────────────────────┘
                              │
                              │ HTTP/REST
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      Spring Boot Backend                    │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                   Controllers                        │   │
│  │  User │ Group │ Loan │ Wallet │ Admin               │   │
│  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                    Services                           │   │
│  │  UserService │ GroupService │ LoanService │ ...     │   │
│  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                 Repositories (JPA)                    │   │
│  └─────────────────────────────────────────────────────┘   │
│  ┌─────────────┐  ┌─────────────┐  ┌──────────────────┐  │
│  │  PostgreSQL │  │   Firebase  │  │    Polygon        │  │
│  │  Database  │  │  Authentication│  │  Blockchain     │  │
│  └─────────────┘  └─────────────┘  └──────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

## Project Structure

### Backend (`apps/backend/`)

```
apps/backend/
├── src/main/java/com/chamaa/
│   ├── ChamaaApplication.java         # Application entry point
│   ├── config/                        # Configuration classes
│   │   ├── DatabaseConfig.java        # Database configuration
│   │   ├── SecurityConfig.java        # Security & CORS config
│   │   ├── FirebaseConfig.java        # Firebase setup
│   │   └── AppProperties.java         # App properties
│   ├── controllers/                   # REST API controllers
│   │   ├── UserController.java
│   │   ├── GroupController.java
│   │   ├── LoanController.java
│   │   └── WalletController.java
│   ├── services/                      # Business logic
│   │   ├── UserService.java
│   │   ├── GroupService.java
│   │   ├── LoanService.java
│   │   ├── WalletService.java
│   │   └── FirebaseTokenVerificationService.java
│   ├── repositories/                  # Data access layer
│   ├── entities/                      # JPA entities
│   │   ├── User.java
│   │   ├── Group.java
│   │   ├── Loan.java
│   │   ├── Wallet.java
│   │   └── Transaction.java
│   ├── blockchain/                    # Blockchain integration
│   │   ├── BlockchainService.java
│   │   ├── ContractService.java
│   │   └── PolygonService.java
│   └── common/                        # Shared utilities
│       ├── exceptions/
│       └── utils/
└── src/main/resources/
    └── application.properties
```

### Android App (`apps/android/ChamaApp/`)

```
apps/android/ChamaApp/app/
├── src/main/java/com/example/chama/
│   ├── MainActivity.kt                # Entry point
│   ├── network/                       # Networking
│   │   ├── RetrofitInstance.kt       # Retrofit setup
│   │   ├── ApiService.kt              # API interface
│   │   ├── AuthInterceptor.kt        # Auth token interceptor
│   │   ├── TokenManager.kt            # Token storage
│   │   └── ErrorHandler.kt           # Error handling
│   ├── models/                        # Data models
│   │   ├── BaseResponse.kt
│   │   ├── UserModel.kt
│   │   ├── GroupModel.kt
│   │   └── LoanModel.kt
│   └── ui/                            # UI screens
│       └── theme/
└── build.gradle.kts                   # Dependencies
```

## Design Patterns

### Backend

| Pattern | Usage |
|---------|-------|
| **Layered Architecture** | Controllers → Services → Repositories → Entities |
| **Dependency Injection** | Spring IoC container |
| **Repository Pattern** | Data access abstraction |
| **Service Layer** | Business logic encapsulation |

### Android

| Pattern | Usage |
|---------|-------|
| **MVVM** | Model-View-ViewModel |
| **Repository Pattern** | Data abstraction layer |
| **Interceptor** | Network request/response handling |
| **Lazy Initialization** | Retrofit singleton |

## Security

### Authentication Flow
1. User authenticates via Firebase (Android app)
2. Firebase returns ID token
3. App includes token in API requests: `Authorization: Bearer <token>`
4. Backend validates token via Firebase Admin SDK
5. Request processed with authenticated user context

### Security Configuration
- CORS enabled for mobile app origins
- Firebase Authentication required for all endpoints
- BCrypt password encryption (where applicable)
- Input validation on all endpoints

## Database Schema

### Entity Relationships

```
User (1) ─────< (N) Group
User (1) ─────< (N) Loan
User (1) ──1:1< Wallet
Group (1) ───< (N) Loan
Wallet (1) ──< (N) Transaction
```

### Tables
- **users**: User accounts
- **groups**: Community lending groups
- **loans**: Loan records with status tracking
- **wallets**: User wallet balances
- **transactions**: Transaction history

## Technology Stack

| Layer | Technology |
|-------|------------|
| Backend Framework | Spring Boot 3.2 |
| Language | Java 17+ |
| Database | PostgreSQL / H2 (dev) |
| ORM | Spring Data JPA / Hibernate |
| Authentication | Firebase Auth |
| Blockchain | Web3j (Polygon) |
| Android UI | Jetpack Compose |
| Android Networking | Retrofit 2 + OkHttp |
| Android Database | Room |
| Build (Backend) | Maven |
| Build (Android) | Gradle (Kotlin DSL) |

## Configuration

### Backend
Configuration via `application.properties` and environment variables:
- Database connection (JDBC URL, credentials)
- Server port
- Firebase project settings
- Blockchain RPC endpoints
- Logging levels

### Android
- BASE_URL points to backend (`http://10.0.2.2:8080/api`)
- Gson for JSON serialization
- SharedPreferences for token storage
