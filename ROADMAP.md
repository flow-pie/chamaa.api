# Chamaa.API - Product Roadmap

> A transparent plan for building Africa's next-generation decentralized savings platform.
> **Last Updated:** November 25, 2025

---

## Overview

Chamaa.API is organized into **8 phases**, each with specific deliverables and timelines. This roadmap is synced with GitHub Issues and project milestones.

**Total Issues:** 102 | **Status:** Open to Contributors ✅

---

## Phase 1: Project Setup (Q4 2025)

**Foundation** - Project structure and basic configurations

### Status: 🟢 Complete

### Issues (3)
- [x] #6 - Initialize Node.js backend project (Turbo-repo)
- [ ] Comprehensive documentation (README, CONTRIBUTING) 
- [ ] Environment Variables (.env) + secrets management

### Deliverables
- ✅ Monorepo structure ready for development
- ✅ Development environment setup documented
- ✅ Contribution guidelines established

---

## Phase 2: Infrastructure & Observability

**Setup infrastructure and install required tools for monitoring**

### Database Setup
- [ ] #14 - Setup database (MongoDB/Postgres)
- [ ] #15 - Implement user model
- [ ] #21 - Implement group model
- [ ] #22 - Implement transaction model
- [ ] #63 - Configure PostgreSQL + TypeORM

### API Middleware & Validation
- [ ] #68 - API rate limiting & Validation middleware

### Monitoring & Observability Stack
- [ ] #73 - Monitoring stack
- [ ] #74 - Prometheus (metrics collection)
- [ ] #75 - Grafana (Visual dashboards)
- [ ] #76 - Loki (centralized logging)
- [ ] #77 - Health checks & uptime alerts
- [ ] #78 - System metrics dashboard
- [ ] #79 - Error tracking (Sentry / OpenTelemetry)

### Caching & Message Queues
- [ ] #71 - Add redis (Cache + queue)
- [ ] #72 - Integrate RabbitMQ for background jobs

### Deployment & Containerization
- [ ] #69 - Container orchestration (Docker Compose)
- [ ] #70 - Nginx reverse proxy + HTTPS (Lets encrypt / Traefik)

### Issues (15)
**Total issues in this phase: 15**

---

## Phase 3: Core API Features

**Backend API endpoints and core business logic**

### Authentication & User Management
- [ ] #16 - Create Member Registration Endpoint
- [ ] #17 - Implement Member Login Endpoint
- [ ] #23 - API: User registration & login
- [ ] #64 - Implement Auth (Firebase)

### Group Management
- [ ] #24 - API: Create group + join group

### Contributions & Transactions
- [ ] #18 - Create Contributions Endpoint
- [ ] #19 - Fetch Member Contributions
- [ ] #28 - API: Transaction history
- [ ] #33 - Sync wallet balances
- [ ] #66 - Wallet and Transactions endpoints

### Loan Management
- [ ] #20 - Create Loan Request Endpoint
- [ ] #25 - API: Request loan
- [ ] #26 - API: Approve/reject loan (voting system)
- [ ] #27 - API: Loan repayment + penalties

### Payment Integration
- [ ] #29 - Integrate M-Pesa Daraja API
- [ ] #30 - Integrate Airtel Money API
- [ ] #31 - Integrate PayPal REST API
- [ ] #32 - Abstract payment service layer
- [ ] #65 - Mpesa + Paypal API integration

### Issues (20+)
**Total issues in this phase: 20+**

---

## Phase 4: Blockchain Layer

**Smart contracts and blockchain connectivity**

### Blockchain Setup
- [ ] #9 - Research & choose blockchain framework
- [ ] #10 - Setup blockchain node connection
- [ ] #81 - Integrate Ethereum (Polygon testnet)

### Smart Contracts - Core
- [ ] #11 - Write basic smart contract for Chama
- [ ] #82 - Smart contract for saving rules
- [ ] #83 - Smart contract for Loan approvals

### Smart Contract Deployment & Integration
- [ ] #12 - Deploy smart contract on testnet
- [ ] #13 - Integrate backend with smart contract
- [ ] #84 - Add Nethereum or Ether.js integration
- [ ] #85 - Store on-chain transaction hashes

### Smart Contract Enforcement
- [ ] #43 - Smart contract: enforce contribution rules
- [ ] #44 - Smart contract: repayment deadlines & penalties
- [ ] #45 - Backend: cron job for overdue loans

### Issues (14)
**Total issues in this phase: 14**

---

## Phase 5: Governance & Voting

**Voting system and governance features**

### DAO & Voting
- [ ] #91 - Implement DAO-style voting system
- [ ] #92 - Smart contracts based proposal
- [ ] #93 - Real-time voting (socket.io / SignalR)

### Governance & Monitoring
- [ ] #46 - Record, display voting history and onchain results dashboard
- [ ] #90 - Display blockchain ledger + voting feed

### Admin Tools
- [ ] #106 - Admin Dashboard (`GET /api/admin/overview`)
- [ ] #107 - Group Management (`PATCH /api/admin/groups/:id/status`)
- [ ] #108 - Member management (`PATCH /api/admin/members/:id/role`)
- [ ] #109 - System settings (`PUT /api/admin/settings`)

### Analytics & Audit
- [ ] #59 - Analytics dashboard
- [ ] #50 - Audit logging (`GET /api/admin/audit`)

### Issues (11)
**Total issues in this phase: 11**

---

## Phase 6: Mobile App (Android)

**Building a fully functional Android application**

### Project Setup
- [ ] #7 - Initialize Android project (Kotlin, MVVM, Room DB)

### Authentication & Core UI
- [ ] #34 - Android: User registration & login
- [ ] #35 - Android: Create/join chama group UI

### Wallet & Transactions
- [ ] #36 - Android: Wallet balance screen
- [ ] #40 - Android: Transaction history screen

### Contributions & Payments
- [ ] #37 - Android: Contribution screen

### Loans & Governance
- [ ] #38 - Android: Loan request screen
- [ ] #39 - Android: Loan approval voting screen

### Real-time Features & Settings
- [ ] #41 - Android: Push notifications
- [ ] #42 - Android: Profile & settings UI

### Offline Support & API Integration
- [ ] #87 - Add firebase login
- [ ] #88 - Implement API calls (Retrofit)
- [ ] #89 - Add offline caching

### Issues (14)
**Total issues in this phase: 14**

---

## Phase 7: DevOps & Scaling (Continuous)

**Automated deployment and infrastructure scaling**

### CI/CD Pipeline
- [ ] #8 - CI/CD pipelines (GitHub Actions / Render / Fly.io)
- [ ] #94 - CI/CD automation (build → test → deploy)

### Deployment & Cloud
- [ ] #51 - Docker + Docker compose environment
- [ ] #52 - Deploy backend to cloud

### Testing & Quality
- [ ] #53 - Deploy contracts to testnet
- [ ] #54 - Distribute Android APK
- [ ] #55 - Write unit tests
- [ ] #56 - Write integration tests

### Infrastructure & Scaling
- [ ] #80 - Auto-scaling and worker management
- [ ] #95 - Versioned API releases
- [ ] #96 - Database migrations and rollback strategy
- [ ] #98 - Daily backups (DB + wallets)
- [ ] #99 - Performance profiling (APM tools)
- [ ] #100 - Load testing (k6 / locust)
- [ ] #101 - Blue-green deployment or canary releases
- [ ] #102 - Cost monitoring (cloud budgets, resource optimization)

### Issues (16)
**Total issues in this phase: 16**

---

## Phase 8: Long Term Goals

**Advanced features and future roadmap**

### Security & Compliance
- [ ] #47 - Encrypt sensitive data
- [ ] #48 - Role-based access control
- [ ] #49 - Fraud prevention
- [ ] #97 - Security hardening (rate limits, CSP headers, owasp scan)
- [ ] #104 - KYC verification (identity API)

### Blockchain Monitoring & Explorer
- [ ] #86 - Blockchain explorer-style dashboard (Grafana or custom UI)

### Advanced Features (DeFi & NFTs)
- [ ] #57 - Investment pooling feature
- [ ] #58 - NFT-based member IDs
- [ ] #60 - Multi-language support
- [ ] #61 - AI-powered credit scoring
- [ ] #103 - Tokenized group savings (DeFi integration)
- [ ] #105 - Governance token for DAO participation

### Issues (12)
**Total issues in this phase: 12**

---

## Quick Reference: Issue Labels

- 🟢 `good first issue` - Perfect for newcomers
- 🟡 `help wanted` - More complex, needs collaboration
- 🟣 `realtime` - Real-time features (Socket.io, WebSockets)
- 🟠 `feature` - New functionality
- 🔵 `backend` - Backend/API work
- 🟤 `blockchain` - Blockchain & smart contracts
- 🟪 `smart-contracts` - Solidity contracts
- 🟣 `governance` - Voting & DAO systems
- 🟢 `testing` - Tests & QA

---

## Getting Involved

We welcome contributions at all phases! Here's how to get started:

1. **Review [CONTRIBUTING.md](./CONTRIBUTING.md)** for guidelines
2. **Check [GitHub Issues](https://github.com/flow-pie/chamaa.api/issues)** and use filters
3. **Look for `good first issue` label** if you're new to the project
4. **Comment "I'd like to work on this"** to claim an issue
5. **Follow the development setup** in [README.md](./README.md)
6. **Submit a Pull Request** with your changes

### Recommended Starting Points

**New Contributors:**
- Phase 1: Documentation tasks
- Phase 2: Database schema and model implementation
- Phase 3: Simple API endpoints (user, group management)

**Intermediate Contributors:**
- Phase 4: Blockchain integration
- Phase 5: Governance & Admin features
- Phase 8: Security & compliance

**Advanced Contributors:**
- Phase 6: Android app development
- Phase 7: DevOps & infrastructure
- Full end-to-end feature implementation

---

## Timeline & Progress

| Phase | Status | Timeline | Issues |
|-------|--------|----------|--------|
| Phase 1: Project Setup | 🟢 Complete | Q4 2025 | 3 |
| Phase 2: Infrastructure | 🔴 0% | Q1 2026 | 15 |
| Phase 3: Core API | 🔴 0% | Q1 2026 | 20+ |
| Phase 4: Blockchain | 🔴 0% | Q2 2026 | 14 |
| Phase 5: Governance | 🔴 0% | Q2 2026 | 11 |
| Phase 6: Mobile App | 🔴 0% | Q2-Q3 2026 | 14 |
| Phase 7: DevOps | 🔴 0% | Continuous | 16 |
| Phase 8: Long Term | 🔴 0% | Q3+ 2026 | 12 |

**Total: 102 Issues** | [View on GitHub](https://github.com/flow-pie/chamaa.api/issues)

---

## Architecture Overview

```
chamaa.api/
├── apps/
│   ├── api/              # NestJS REST API
│   │   ├── modules/
│   │   │   ├── auth/     # Authentication (Phase 3)
│   │   │   ├── user/     # User management (Phase 3)
│   │   │   ├── group/    # Group management (Phase 3)
│   │   │   ├── wallet/   # Wallet & transactions (Phase 3)
│   │   │   ├── loan/     # Loan system (Phase 3)
│   │   │   ├── payment/  # Payment integration (Phase 3)
│   │   │   ├── proposal/ # Governance (Phase 5)
│   │   │   ├── admin/    # Admin dashboard (Phase 5)
│   │   │   └── notification/ # Real-time updates
│   │   ├── common/       # Filters, interceptors, pipes
│   │   └── config/       # Database, environment
│   └── blockchain/       # Smart contract integration (Phase 4)
│       ├── services/
│       │   ├── polygon.service.ts
│       │   ├── ethers.service.ts
│       │   └── contract.service.ts
│       └── contracts/    # Solidity contracts
├── packages/
│   ├── shared/           # Types & interfaces
│   └── config/           # ESLint, Prettier configs
├── infra/
│   ├── docker/          # Docker & Compose configs
│   ├── ci-cd/           # GitHub Actions workflows
│   └── scripts/         # Migration & setup scripts
├── docs/
│   ├── architecture.md  # System design
│   ├── api-spec.md      # API documentation
│   └── blockchain.md    # Blockchain guide
└── android/             # Android app (Phase 6)
    ├── app/
    ├── data/            # Room DB, APIs, Repos
    ├── ui/              # Activities, Fragments
    └── viewmodel/       # ViewModels
```

---

## Tech Stack Summary

| Layer | Technology |
|-------|------------|
| **Backend** | NestJS, TypeScript, Node.js 18+ |
| **Database** | PostgreSQL, TypeORM |
| **Caching** | Redis |
| **Message Queue** | RabbitMQ |
| **Blockchain** | Polygon (Mumbai Testnet), Ethers.js, Solidity |
| **Authentication** | Firebase Authentication, JWT |
| **Payments** | M-Pesa Daraja, Airtel Money, PayPal |
| **Mobile** | Android, Kotlin, MVVM, Room DB, Retrofit |
| **Monitoring** | Prometheus, Grafana, Loki, Sentry |
| **DevOps** | Docker, GitHub Actions, Render/Fly.io |
| **Testing** | Jest, Hardhat |

---

## Notes

- This roadmap is living and continuously updated based on community feedback
- Issues are designed to be completed in phases—maintain dependency order
- See [CONTRIBUTING.md](./CONTRIBUTING.md) for detailed development guidelines
- All commits must follow [Conventional Commits](https://www.conventionalcommits.org/) standard
- Issues are tagged with difficulty levels and category labels for easy discovery

---

## Resources

- 📖 [Documentation](./docs)
- 💬 [GitHub Discussions](https://github.com/flow-pie/chamaa.api/discussions)
- 🐛 [Report Issues](https://github.com/flow-pie/chamaa.api/issues)
- 📋 [Project Board](https://github.com/users/flow-pie/projects/7)
- 🤝 [Contributing Guide](./CONTRIBUTING.md)

---

**Mission:** Build a transparent, blockchain-powered savings platform for African communities.

**Vision:** Enable financial inclusion through decentralized, community-driven technology.
