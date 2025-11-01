## `ROADMAP.md`

#  Chamaa.api Roadmap

> A transparent plan for building Africa’s next-generation decentralized savings platform.

---

##  Phase 1: Backend & Core API (Q4 2025)

- [x] Setup Node.js (NestJS) backend
- [x] Configure PostgreSQL + TypeORM
- [x] Implement Auth (Firebase)
- [ ] Wallet & transaction endpoints
- [ ] M-Pesa + PayPal API integration
- [ ] Docker + CI/CD setup

---

## Phase 2: Blockchain Layer

- [ ] Integrate Ethereum (Polygon testnet)
- [ ] Write smart contracts for:
  - Savings rules
  - Loan approvals
  - Penalties
- [ ] Add Nethereum or Ethers.js integration
- [ ] Store on-chain transaction hashes

---

## Phase 3: Mobile App (Android)

- [ ] Build UI (MVVM + Room DB)
- [ ] Add Firebase login
- [ ] Implement API calls (Retrofit)
- [ ] Add offline mode caching
- [ ] Display blockchain ledger and voting features

---

## Phase 4: Governance & Voting

- [ ] Implement DAO-style voting system
- [ ] Smart contract-based proposals
- [ ] Add real-time voting (Socket.io / SignalR)
- [ ] Show vote results on-chain

---

## Phase 5: Expansion & Partnerships

- [ ] Add bank APIs (Open Banking)
- [ ] Add Flutterwave / Chipper Cash support
- [ ] Build web dashboard (Next.js or Blazor)
- [ ] Explore cross-border remittances

---

## Long-Term Goals

- Multi-group support (one user, multiple chamas)
- AI-based loan scoring
- Tokenized group savings (DeFi integration)
- KYC verification integration





##  Development Setup

1. Fork the repository  
2. Clone your fork:
   ```bash
   git clone https://github.com/flow-pie/chamaa.api.git


3. Create a new branch:

   ```bash
   git checkout -b feature/your-feature-name
   ```
4. Make your changes
5. Run tests and lint:

   ```bash
   npm run lint && npm test
   ```
6. Commit your changes:

   ```bash
   git commit -m "feat: add blockchain ledger module"
   ```
7. Push and open a PR:

   ```bash
   git push origin feature/your-feature-name
   ```

---

## Branching Strategy

* `main` — stable production code
* `dev` — active development branch
* `feature/*` — new features
* `bugfix/*` — patches
* `docs/*` — documentation updates

---

## Issue Labels

| Label                 | Meaning                |
| --------------------- | ---------------------- |
| 🟢 `good first issue` | Great for newcomers    |
| 🟡 `help wanted`      | Needs community help   |
| 🔴 `bug`              | Requires fixing        |
| 🔵 `feature`          | New feature request    |
| 🟣 `blockchain`       | Smart contract related |



