# Chamaa.API - Product Roadmap

> A transparent plan for building Africa's next-generation decentralized savings platform.

---

## Overview

Chamaa.API is organized into **8 phases**, each with specific deliverables and timelines. This roadmap aligns with our GitHub Milestones and Issues.

---

## Phase 1: Project Setup (Q4 2025)

**Foundation** - Project structure and basic configurations

### Objectives
- [x] Initialize monorepo with Turbo and pnpm workspaces
- [x] Setup NestJS backend with TypeScript and ESLint
- [x] Create comprehensive README with setup instructions
- [x] Write CONTRIBUTING.md with guidelines and workflows
- [x] Setup environment configuration (.env.example)
- [ ] Initialize Git workflow and branch protection rules
- [ ] Create full documentation structure (docs/ folder)

### Deliverables
- ✅ Monorepo structure ready for development
- ✅ Development environment setup documented
- ✅ Contribution guidelines established
- ⏳ Git workflow configured
- ⏳ Documentation templates created

### Issues
- [ ] Initialize Git workflow and branch protection
- [ ] Create project documentation structure
- [ ] Setup TypeScript base configuration

---

## Phase 2: Infrastructure & Observability

**Setup infrastructure and install required tools for monitoring**

### Objectives
- [ ] Setup PostgreSQL database with Docker
- [ ] Implement User model and schema
- [ ] Implement Group model and schema
- [ ] Implement Transaction model and schema
- [ ] Setup ELK stack (Elasticsearch, Logstash, Kibana)
- [ ] Implement health check and metrics endpoints
- [ ] Configure Sentry for error tracking
- [ ] Setup database backup and recovery
- [ ] Implement API rate limiting

### Deliverables
- Database layer with migrations
- User, Group, and Transaction schemas
- Centralized logging and monitoring
- Performance metrics and health checks
- Error tracking and alerting
- Database backup automation
- Rate limiting middleware

### Key Technologies
- PostgreSQL with connection pooling
- Prisma ORM for database management
- ELK Stack for log aggregation
- Prometheus for metrics
- Sentry for error tracking

### Issues (9 Total)
- [ ] Setup database (PostgreSQL)
- [ ] Implement user model and schema
- [ ] Implement group model and schema
- [ ] Implement transaction model and schema
- [ ] Setup monitoring and logging (ELK stack)
- [ ] Setup health check and metrics endpoints
- [ ] Configure error tracking (Sentry)
- [ ] Setup database backup strategy
- [ ] Setup API rate limiting and throttling

---

## Phase 3: Core API Features

**Backend API endpoints and core business logic**

### Objectives
- [ ] User registration and authentication (Firebase)
- [ ] Group creation, joining, and management
- [ ] Contribution recording (on-chain + off-chain)
- [ ] Loan request and management system
- [ ] Fund withdrawal with approvals
- [ ] Transaction history and filtering
- [ ] Wallet balance queries and syncing

### Deliverables
- 7+ REST API endpoints
- Authentication middleware
- Contribution and withdrawal flows
- Transaction ledger
- Real-time balance synchronization

### Key Features
- Email/phone authentication via Firebase
- JWT token management
- Group-based access control
- On-chain and off-chain record keeping
- Transaction filtering and pagination

### Issues (7 Total)
- [ ] API - User registration and authentication
- [ ] API - Create and manage groups
- [ ] API - Make contributions
- [ ] API - Request and manage loans
- [ ] API - Withdraw funds
- [ ] API - Fetch transaction history
- [ ] API - Wallet balance queries

---

## Phase 4: Blockchain Layer

**Connects backend to blockchain and NFTs**

### Objectives
- [x] Setup blockchain connection (Polygon Testnet with Ethers.js)
- [ ] Write Solidity smart contract for group ledger
- [ ] Deploy contract to Polygon Mumbai testnet
- [ ] Integrate backend with smart contract

### Deliverables
- Smart contract for contribution and withdrawal tracking
- Contract deployment on testnet
- Backend service for contract interactions
- Contract ABI and documentation

### Smart Contract Features
- Contribution ledger
- Withdrawal approval logic
- Basic loan enforcement
- Transaction immutability

### Issues (3 Total)
- [ ] Setup blockchain connection (Polygon Testnet)
- [ ] Write basic smart contract (Solidity)
- [ ] Deploy smart contract on testnet

---

## Phase 5: Governance & Voting

**Voting system and governance features**

### Objectives
- [ ] Implement smart contract voting logic
- [ ] Create voting endpoints in API
- [ ] Support 1-member-1-vote and weighted voting
- [ ] Record and display voting results

### Deliverables
- Voting mechanism in smart contract
- Vote submission and retrieval APIs
- Voting results calculation
- Immutable voting history

### Key Features
- Democratic decision-making for group actions
- Loan approval voting
- Group rule changes via voting
- Transparent voting records

### Issues (2 Total)
- [ ] Smart contract - voting logic (1-member-1-vote)
- [ ] API - Submit and fetch votes on proposals

---

## Phase 6: Mobile App (Android)

**Building a mobile app**

### Objectives
- [ ] Setup Android project with MVVM architecture
- [ ] Implement user authentication UI
- [ ] Create wallet dashboard screen
- [ ] Build group management UI
- [ ] Create contribution screen with payment gateways
- [ ] Implement loan request interface
- [ ] Build voting screen with real-time results
- [ ] Create transaction history with caching
- [ ] Setup push notifications
- [ ] Build profile and settings UI
- [ ] Implement offline sync with Room DB

### Deliverables
- Fully functional mobile application
- Complete user flows for all features
- Offline-first experience with sync
- Real-time notifications
- Cached transaction history

### Technologies
- Kotlin/Jetpack Compose for UI
- Firebase Authentication
- Room DB for offline caching
- Firebase Cloud Messaging for notifications
- Retrofit for API calls
- MVVM architecture pattern

### Issues (11 Total)
- [ ] Android: Project setup and architecture
- [ ] Android: User registration and login UI
- [ ] Android: Wallet dashboard screen
- [ ] Android: Create and join group UI
- [ ] Android: Contribution screen
- [ ] Android: Loan request form
- [ ] Android: Voting screen
- [ ] Android: Transaction history screen
- [ ] Android: Push notifications
- [ ] Android: Profile and settings UI
- [ ] Android: Offline sync and data caching

---

## Phase 7: DevOps & Scaling (Continuous)

**Ongoing DevOps, infrastructure, and scaling**

### Objectives
- [ ] Setup GitHub Actions CI/CD pipeline
- [ ] Dockerize NestJS backend API
- [ ] Create docker-compose for full stack
- [ ] Deploy backend to cloud (AWS/GCP/Render)
- [ ] Setup Redis for caching and sessions
- [ ] Implement API gateway and load balancing
- [ ] Configure auto-scaling
- [ ] Implement background jobs and cron tasks

### Deliverables
- Automated CI/CD pipeline
- Containerized backend and database
- Cloud deployment with auto-scaling
- Distributed caching layer
- Load balancing and CDN setup
- Automated background processing

### Key Infrastructure
- Docker and Kubernetes (optional)
- GitHub Actions for CI/CD
- AWS/GCP/Render for hosting
- Redis for caching
- Nginx or Cloudflare for API Gateway

### Issues (8 Total)
- [ ] Setup GitHub Actions CI/CD pipeline
- [ ] Dockerize backend API (NestJS)
- [ ] Setup docker-compose for local development
- [ ] Deploy backend to cloud (AWS/GCP/Render)
- [ ] Setup Redis for caching and sessions
- [ ] Implement API gateway and load balancing
- [ ] Setup auto-scaling and horizontal pod autoscaling
- [ ] Implement cron jobs for background tasks

---

## Phase 8: Long Term Goals

**Advanced features and future roadmap**

### Objectives
- [ ] Integrate M-Pesa Daraja API (sandbox)
- [ ] Integrate Airtel Money API (sandbox)
- [ ] Integrate PayPal REST API (sandbox)
- [ ] Build investment pooling feature
- [ ] Issue NFT member IDs
- [ ] Create analytics dashboard

### Future Enhancements
- Multi-currency support
- AI-powered credit scoring
- Advanced analytics with charts
- Multi-language support (English, Swahili, French)
- Web dashboard for administrators
- API rate limiting per group
- Compliance reporting

### Issues (3 Total)
- [ ] Integrate M-Pesa, Airtel Money, and PayPal
- [ ] Investment pooling and group investment voting
- [ ] NFT-based member IDs and analytics dashboard

---

## Cross-Cutting Concerns

### Testing (4 Issues)
- [ ] Write backend unit tests (API, services)
- [ ] Write integration tests (API + DB + Blockchain)
- [ ] Write smart contract tests (Hardhat)
- [ ] Write Android unit and UI tests

### Security & Compliance (5 Issues)
- [ ] Implement encryption for sensitive data
- [ ] Role-based access control (RBAC)
- [ ] Fraud detection and prevention
- [ ] Audit logging for all transactions
- [ ] GDPR and data privacy compliance

---

## Timeline Summary

| Phase | Status | Timeline | Issues |
|-------|--------|----------|--------|
| Phase 1: Project Setup | 🟢 ~70% | Q4 2025 | 7 |
| Phase 2: Infrastructure | 🔴 0% | Q1 2026 | 9 |
| Phase 3: Core API | 🔴 0% | Q1 2026 | 7 |
| Phase 4: Blockchain | 🟡 33% | Q2 2026 | 3 |
| Phase 5: Governance | 🔴 0% | Q2 2026 | 2 |
| Phase 6: Mobile App | 🔴 0% | Q2-Q3 2026 | 11 |
| Phase 7: DevOps | 🔴 0% | Continuous | 8 |
| Phase 8: Long Term | 🔴 0% | Q3+ 2026 | 3 |

**Total: 59+ Issues tracked in GitHub Milestones**

---

## Getting Involved

We welcome contributions at all phases! Here's how to get started:

1. **Review [CONTRIBUTING.md](./CONTRIBUTING.md)** for guidelines
2. **Check [GitHub Issues](https://github.com/flow-pie/chamaa.api/issues)** filtered by phase
3. **Pick an issue** that matches your skills and interests
4. **Comment "I'd like to work on this"** to claim it
5. **Follow the development setup** in [README.md](./README.md)
6. **Submit a Pull Request** with your changes

### Recommended Starting Issues
- Phase 1: Documentation and setup tasks
- Phase 2: Database schema implementation
- Phase 3: API endpoint development
- Phase 7: CI/CD and DevOps (continuous)

---

## Notes

- This roadmap is living and may be updated based on feedback and priorities
- Each phase builds on the previous one—maintain dependency order
- See [CONTRIBUTING.md](./CONTRIBUTING.md) for detailed development guidelines
- See [docs/architecture.md](./docs/architecture.md) for technical architecture
- All commits should follow [Conventional Commits](https://www.conventionalcommits.org/) standard

---

## Contact & Questions

- 💬 [GitHub Discussions](https://github.com/flow-pie/chamaa.api/discussions)
- 🐛 [Report Issues](https://github.com/flow-pie/chamaa.api/issues)
- 📧 See CONTRIBUTING.md for more contact options

---

**Last Updated:** November 22, 2025

**Status:** Ready for Contributors ✅
