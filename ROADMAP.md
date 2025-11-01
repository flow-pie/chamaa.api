## `ROADMAP.md`

#  Chamaa.api Roadmap

> A transparent plan for building Africa’s next-generation decentralized savings platform.

---

### Phase 1 – Project Setup

* [ ] Initialize GitHub repo (mono-repo or separate frontend/backend)
* [ ] Set up Node.js backend (Express + TypeScript + ESLint + Prettier)
* [ ] Set up Android project (Java, MVVM, Room, Retrofit, Firebase Auth)
* [ ] Write project README + contribution guidelines
* [ ] Configure CI/CD (GitHub Actions: build + lint + tests)

---

### Phase 2 – Backend Foundation

* [ ] Implement Firebase-based authentication validation in backend
* [ ] Create **User model** (MongoDB/Postgres schema)
* [ ] Create **Wallet model** (on-chain + off-chain balance link)
* [ ] API: User registration/login
* [ ] Secure APIs with JWT middleware

---

### Phase 3 – Blockchain Integration

* [ ] Set up blockchain connection (Hyperledger Fabric OR Polygon testnet)
* [ ] Implement smart contract for contributions & withdrawals
* [ ] Deploy contract to testnet & connect backend
* [ ] Write backend service to record transactions on blockchain
* [ ] API: Submit contribution (on-chain + off-chain balance update)
* [ ] API: Withdraw funds (treasurer + member rules)

---

### Phase 4 – Mobile Money & Payments

* [ ] Integrate **M-Pesa Daraja API (sandbox)**
* [ ] Integrate **Airtel Money API (sandbox)**
* [ ] Integrate **PayPal REST API (sandbox)**
* [ ] Implement payment webhook handlers in backend
* [ ] Link wallet with mobile money transactions (credit/debit balance)
* [ ] Write reconciliation service (on-chain vs mobile money)

---

### Phase 5 – Group Management

* [ ] API: Create group (name, rules, members)
* [ ] API: Join group (with approval workflow)
* [ ] API: Define group rules (contribution minimum, loan deadlines, penalties)
* [ ] Smart contract: enforce group rules
* [ ] Backend service: enforce penalties for late payments

---

### Phase 6 – Loans & Lending

* [ ] API: Request loan (record on blockchain)
* [ ] API: Loan approval voting (members vote, contract records results)
* [ ] API: Disburse loan (mobile money + blockchain ledger update)
* [ ] API: Repay loan (update wallet + contract validation)

---

### Phase 7 – Voting & Governance

* [ ] Smart contract: voting logic (1-member-1-vote, weighted voting)
* [ ] API: Submit vote on proposal
* [ ] API: Fetch voting results from blockchain
* [ ] Implement governance rules (majority/min quorum)

---

### Phase 8 – Mobile App Features

* [ ] UI: Firebase login (phone/email)
* [ ] UI: Wallet dashboard (on-chain + off-chain balance)
* [ ] UI: Contribution screen (M-Pesa/Airtel/PayPal)
* [ ] UI: Withdraw screen
* [ ] UI: Group creation & joining flow
* [ ] UI: Loan request form
* [ ] UI: Voting screen (real-time results)
* [ ] UI: Transaction history (synced with blockchain)
* [ ] UI: Notifications (loan approvals, penalties, votes)

---

### Phase 9 – Security & Compliance

* [ ] Encrypt sensitive data at rest (DB encryption)
* [ ] Secure API calls (HTTPS + JWT validation)
* [ ] Implement audit logging (track actions)
* [ ] Add fraud detection rules (suspicious transactions)
* [ ] Compliance placeholder (GDPR/CBK)

---

### Phase 10 – Testing & Deployment

* [ ] Unit tests (backend + smart contracts)
* [ ] Integration tests (mobile money APIs + blockchain + backend)
* [ ] Android instrumentation tests (Room + Retrofit)
* [ ] Set up staging environment (Heroku/AWS backend, Firebase hosting APIs)
* [ ] Deploy smart contracts to production chain/testnet
* [ ] Beta release (Firebase App Distribution / Play Store internal track)



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



