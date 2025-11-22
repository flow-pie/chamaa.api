# Chamaa.API - Architecture Overview

## Project Overview

Chamaa.API is a **monorepo** that powers a decentralized savings & lending circle platform. It combines blockchain technology with traditional mobile money integration to provide transparent, immutable financial management.

---

## Monorepo Structure

This project uses **Turbo** as the build orchestrator and **pnpm workspaces** for package management.

```
chamaa.api/
├── apps/
│   ├── api/                          # NestJS REST API backend
│   │   ├── src/
│   │   │   ├── main.ts               # Application entry point
│   │   │   ├── app.module.ts         # Root module
│   │   │   ├── config/               # Configuration services
│   │   │   ├── common/               # Global pipes, filters, interceptors
│   │   │   └── modules/              # Feature modules
│   │   │       ├── auth/             # Authentication (Firebase)
│   │   │       ├── group/            # Group management
│   │   │       └── transaction/      # Transaction ledger
│   │   └── package.json
│   │
│   └── blockchain/                   # Ethers.js smart contract integration
│       ├── src/
│       │   ├── index.ts              # Entry point
│       │   ├── contracts/            # Smart contract interactions
│       │   └── services/             # Blockchain services
│       └── package.json
│
├── packages/
│   └── shared/                       # Shared utilities and types
│       ├── src/
│       │   ├── types/                # Shared TypeScript interfaces
│       │   └── utils/                # Common utilities
│       └── package.json
│
├── .env.example                      # Environment variables template
├── turbo.json                        # Turbo build pipeline configuration
├── tsconfig.base.json                # Base TypeScript configuration
├── pnpm-workspace.yaml               # pnpm workspace definition
└── package.json                      # Root package.json
```

---

## Technology Stack

| Layer | Technology | Version |
|-------|-----------|---------|
| **Build Tool** | Turbo | ^1.10 |
| **Package Manager** | pnpm | ^8.0 |
| **Language** | TypeScript | ^5.5 |
| **API Framework** | NestJS | ^11.0 |
| **Blockchain** | Ethers.js | ^6.8 |
| **Validation** | class-validator | ^0.14 |
| **Transformation** | class-transformer | ^0.5 |
| **Code Quality** | ESLint | ^8.40 |
| **Formatting** | Prettier | ^2.8 |
| **Runtime** | Node.js | 18+ |

---

## Module Descriptions

### 🔌 `apps/api` - NestJS REST API

The main API server handling all business logic and external integrations.

**Key Components:**
- **main.ts** - Bootstrap NestJS application
- **app.module.ts** - Root module with all imports
- **config/** - ConfigService for environment variable management
- **common/** - Global pipes, filters, interceptors
  - `HttpExceptionFilter` - Centralized error handling
  - `ResponseInterceptor` - Standardized response format
  - `ValidationPipe` - Request validation
- **modules/**
  - `auth/` - Firebase authentication, JWT tokens
  - `group/` - Group creation, member management
  - `transaction/` - On-chain and off-chain ledger

**How to work on API:**
```bash
cd apps/api
pnpm install
pnpm start:dev    # Watch mode with hot reload
pnpm lint         # Check code style
pnpm test         # Run Jest tests
```

### ⛓️ `apps/blockchain` - Smart Contract Integration

Handles interaction with blockchain networks (Ethereum, Polygon) using Ethers.js.

**Key Components:**
- **index.ts** - Entry point
- **contracts/** - Smart contract interfaces and ABIs
- **services/** - Services for transaction management, wallet interactions

**How to work on Blockchain:**
```bash
cd apps/blockchain
pnpm install
pnpm dev    # Watch mode
pnpm build  # Compile TypeScript
```

### 📦 `packages/shared` - Shared Utilities

Common types, interfaces, and utility functions used across all apps.

**Examples:**
- `types/` - TypeScript interfaces (User, Wallet, Transaction)
- `utils/` - Validation helpers, formatters

---

## Build Pipeline (Turbo)

The project uses **Turbo** for intelligent build orchestration. See `turbo.json` for pipeline definition.

**Available tasks:**
```bash
pnpm dev      # Run dev servers (api, blockchain)
pnpm build    # Build all apps
pnpm lint     # Lint all code
pnpm test     # Run all tests
```

**Task dependencies:**
- `build` depends on `^build` (builds dependencies first)
- `dev` has no dependencies (parallel execution)
- `lint` and `test` are independent

---

## TypeScript Configuration

- **tsconfig.base.json** - Base configuration for all apps
  - Target: ES2021
  - Strict mode enabled
  - Path aliases: `@shared/*` → `packages/shared/src/*`

Each app can override with their own `tsconfig.json`.

---

## Code Quality

### ESLint Configuration
- Enforces strict TypeScript type safety
- No `any` types without explicit cast
- All promises must be handled
- Unused variables not allowed

### Prettier Configuration
- 2-space indentation
- Single quotes
- Print width: 80

Run both:
```bash
pnpm lint     # Check & fix styles
pnpm format   # Format with Prettier
```

---

## Environment Variables

See `.env.example` for all available configuration options.

**Required for development:**
```bash
NEST_PORT=3000
NEST_ENV=development
DATABASE_URL=postgres://...
JWT_SECRET=your-secret-key
ETHEREUM_RPC_URL=https://sepolia.infura.io/v3/...
```

---

## Development Workflow

### 1. Start Development Servers
```bash
pnpm dev
# Runs all apps in watch mode
# API: http://localhost:3000
# Blockchain: localhost
```

### 2. Make Changes
- Edit code in `apps/api/src` or `apps/blockchain/src`
- Changes auto-reload in watch mode
- Shared types in `packages/shared/src` apply to all apps

### 3. Run Quality Checks
```bash
pnpm lint   # Check code quality
pnpm test   # Run unit tests
pnpm build  # Build for production
```

### 4. Commit Changes
Follow [Conventional Commits](https://www.conventionalcommits.org/):
```
feat(api): add transaction endpoint
fix(blockchain): handle contract reverts
docs(readme): update setup instructions
```

---

## Testing Strategy

### API Tests (`apps/api`)
```bash
cd apps/api
pnpm test              # Run once
pnpm test --watch      # Watch mode
pnpm test:cov          # Coverage report
pnpm test:e2e          # End-to-end tests
```

### Blockchain Tests
```bash
cd apps/blockchain
pnpm test              # Run tests
```

---

## Deployment

### Development
```bash
pnpm dev              # Local development servers
```

### Production Build
```bash
pnpm build            # Creates dist/ in each app
pnpm start:prod       # Run production build
```

### Docker Deployment
(Coming soon - See ROADMAP Phase 1)

---

## Adding New Features

### Adding a new module to the API
```bash
# 1. Create module structure
cd apps/api/src/modules
mkdir wallet
cd wallet

# 2. Create files
echo "import { Module } from '@nestjs/common';" > wallet.module.ts
echo "import { WalletController } from './wallet.controller';" >> wallet.module.ts
echo "import { WalletService } from './wallet.service';" >> wallet.module.ts

# 3. Import in app.module.ts
# Add WalletModule to imports

# 4. Run lint & test
cd ../..
pnpm lint
pnpm test
```

### Adding shared types
```bash
# 1. Create type file
echo "export interface Wallet { id: string; balance: number; }" > packages/shared/src/types/wallet.ts

# 2. Export from index
echo "export * from './wallet';" >> packages/shared/src/types/index.ts

# 3. Use in API
import { Wallet } from '@shared/types';
```

---

## Troubleshooting

### Linting errors after changes
```bash
# Run eslint with fix
cd apps/api && pnpm exec eslint "src/**/*.ts" --fix
```

### Build failures
```bash
# Clear turbo cache
rm -rf .turbo

# Reinstall dependencies
pnpm install

# Rebuild
pnpm build
```

### Module resolution errors
Check `tsconfig.base.json` for correct path aliases.

---

## Resources

- [NestJS Documentation](https://docs.nestjs.com/)
- [Ethers.js Documentation](https://docs.ethers.org/)
- [Turbo Documentation](https://turbo.build/)
- [TypeScript Documentation](https://www.typescriptlang.org/)

---

## Contributing

See [CONTRIBUTING.md](../CONTRIBUTING.md) for guidelines on:
- Commit message format
- Pull request process
- Code review expectations
