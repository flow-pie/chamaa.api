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

## Getting Started

### 1️Clone the repo
```bash
git clone https://github.com/<flow-pie>/chamaa.api.git
cd chamaa.api
