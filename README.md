# Chamaa Api ( Decentralized Savings & Lending Circle App)

### Transparent. Borderless. Community-Driven.

---

## Overview

Traditional savings groups (*Chamas / Harambees*) are built on **trust** — but too often, funds get mismanaged or “lost.”  
**Chamaa app** solves this by combining **blockchain transparency** with **mobile money integration**, allowing trusted savings, loans, and investments directly from your phone.

---

## Problem

- Manual record-keeping and opaque treasurer systems lead to **fund mismanagement**.  
- Cross-network members (M-Pesa, Airtel, PayPal, etc.) face **payment friction**.  
- No immutable record of votes, loans, or repayments.  

---

## Solution

A **blockchain-powered mobile app** where:
- Every contribution, withdrawal, and loan repayment is recorded on-chain.  
- Members can **vote** on proposals and **view transparent ledgers**.  
- Funds flow via **M-Pesa**, **Airtel Money**, **PayPal**, or **bank accounts**.  
- Rules (loan limits, penalties, voting rights) are enforced via **smart contracts**.

---

## Tech Stack

| Layer | Technology |
|-------|-------------|
| Backend | Node.js (NestJS, TypeScript), PostgreSQL |
| Blockchain | Ethereum / Polygon Testnet via Ethers.js |
| Mobile Money APIs | M-Pesa Daraja, Airtel Money, PayPal REST |
| Frontend | Android (Java / Jetpack Compose) |
| Auth | Firebase Authentication (Phone / Email) |
| Hosting | Docker + Render (API) |

---

## Screenshots & Mockups

> *Figma mockups coming soon — see `/designs/` folder.*

---

## Roadmap
See [ROADMAP.md](./ROADMAP.md) for planned features and development phases.

---

## Contributing
We welcome contributions! Whether it's code, docs, or design ideas:
- Fork the repo
- Create a feature branch (`git checkout -b feature/amazing-thing`)
- Commit your changes
- Open a Pull Request 

See [CONTRIBUTING.md](./CONTRIBUTING.md) for detailed guidelines.

---

## Getting Started

### Prerequisites
- **Node.js** 18+ ([Download](https://nodejs.org/))
- **pnpm** 8+ (preferred for monorepo): `npm install -g pnpm`
- **Git**

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/flow-pie/chamaa.api.git
cd chamaa.api

# 2. Install dependencies
pnpm install

# 3. Set up environment variables
cp .env.example .env
# Edit .env with your configuration (API keys, RPC URLs, etc.)
```

### Development

#### Run all services
```bash
# Start all apps in watch mode
pnpm dev
```

#### Run specific app
```bash
# API server
pnpm dev --filter=api

# Blockchain integration
pnpm dev --filter=@apps/blockchain
```

#### Quality Checks
```bash
# Lint all code
pnpm lint

# Build all apps
pnpm build

# Run tests
pnpm test

# Run API tests in watch mode
pnpm test --filter=api --watch
```

### Project Structure

```
chamaa.api/
├── apps/
│   ├── api/          # NestJS REST API
│   └── blockchain/   # Ethers.js smart contract integration
├── packages/
│   └── shared/       # Shared utilities and types
├── .env.example      # Environment variables template
├── turbo.json        # Monorepo build configuration
└── tsconfig.base.json # Base TypeScript configuration
```

---

## 📚 Documentation

- [CONTRIBUTING.md](./CONTRIBUTING.md) - Contributing guidelines
- [ROADMAP.md](./ROADMAP.md) - Project roadmap and phases
- [docs/architecture.md](./docs/architecture.md) - Architecture overview
- [docs/blockchain.md](./docs/blockchain.md) - Blockchain integration guide

---

## 🚀 Deployment

See [ROADMAP Phase 1](./ROADMAP.md) for deployment instructions using Docker + Render.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.

---

## Support

- 📖 [Documentation](./docs)
- 💬 [Issues](https://github.com/flow-pie/chamaa.api/issues)
- 💡 [Discussions](https://github.com/flow-pie/chamaa.api/discussions)
