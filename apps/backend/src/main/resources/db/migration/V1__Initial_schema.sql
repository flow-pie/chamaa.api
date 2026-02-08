-- V1__Initial_schema.sql
-- Initial database schema for Chamaa API
-- Creates all core tables with proper relationships, constraints, and indexes

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) UNIQUE,
    profile_image_url VARCHAR(500),
    is_active BOOLEAN DEFAULT true NOT NULL,
    wallet_address VARCHAR(255),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

-- Groups Table
CREATE TABLE IF NOT EXISTS groups (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    creator_id BIGINT NOT NULL,
    member_count INTEGER DEFAULT 0 NOT NULL,
    is_active BOOLEAN DEFAULT true NOT NULL,
    target_amount DOUBLE PRECISION DEFAULT 0.0 NOT NULL,
    group_image_url VARCHAR(500),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_groups_creator_id
        FOREIGN KEY (creator_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Wallets Table (One-to-One with Users)
CREATE TABLE IF NOT EXISTS wallets (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT UNIQUE NOT NULL,
    balance DOUBLE PRECISION DEFAULT 0.0 NOT NULL,
    wallet_address VARCHAR(255) UNIQUE,
    currency VARCHAR(10) DEFAULT 'USD' NOT NULL,
    is_active BOOLEAN DEFAULT true NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_wallets_user_id
        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Transactions Table
CREATE TABLE IF NOT EXISTS transactions (
    id BIGSERIAL PRIMARY KEY,
    from_wallet_id BIGINT NOT NULL,
    to_wallet_id BIGINT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING' NOT NULL,
    transaction_hash VARCHAR(255),
    description TEXT,
    group_id BIGINT,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_transactions_from_wallet_id
        FOREIGN KEY (from_wallet_id) REFERENCES wallets(id) ON DELETE RESTRICT,
    CONSTRAINT fk_transactions_to_wallet_id
        FOREIGN KEY (to_wallet_id) REFERENCES wallets(id) ON DELETE RESTRICT,
    CONSTRAINT fk_transactions_group_id
        FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE SET NULL
);

-- Loans Table
CREATE TABLE IF NOT EXISTS loans (
    id BIGSERIAL PRIMARY KEY,
    borrower_id BIGINT NOT NULL,
    group_id BIGINT NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    interest_rate DOUBLE PRECISION DEFAULT 0.0 NOT NULL,
    status VARCHAR(50) DEFAULT 'PENDING' NOT NULL,
    purpose TEXT,
    duration_in_months INTEGER NOT NULL,
    paid_amount DOUBLE PRECISION DEFAULT 0.0 NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_loans_borrower_id
        FOREIGN KEY (borrower_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_loans_group_id
        FOREIGN KEY (group_id) REFERENCES groups(id) ON DELETE CASCADE
);

-- Indexes for Performance Optimization
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_users_wallet_address ON users(wallet_address);
CREATE INDEX IF NOT EXISTS idx_users_is_active ON users(is_active);

CREATE INDEX IF NOT EXISTS idx_groups_creator_id ON groups(creator_id);
CREATE INDEX IF NOT EXISTS idx_groups_is_active ON groups(is_active);
CREATE INDEX IF NOT EXISTS idx_groups_name ON groups(name);

CREATE INDEX IF NOT EXISTS idx_wallets_user_id ON wallets(user_id);
CREATE INDEX IF NOT EXISTS idx_wallets_wallet_address ON wallets(wallet_address);
CREATE INDEX IF NOT EXISTS idx_wallets_is_active ON wallets(is_active);

CREATE INDEX IF NOT EXISTS idx_transactions_from_wallet ON transactions(from_wallet_id);
CREATE INDEX IF NOT EXISTS idx_transactions_to_wallet ON transactions(to_wallet_id);
CREATE INDEX IF NOT EXISTS idx_transactions_group ON transactions(group_id);
CREATE INDEX IF NOT EXISTS idx_transactions_status ON transactions(status);
CREATE INDEX IF NOT EXISTS idx_transactions_created_at ON transactions(created_at);

CREATE INDEX IF NOT EXISTS idx_loans_borrower_id ON loans(borrower_id);
CREATE INDEX IF NOT EXISTS idx_loans_group_id ON loans(group_id);
CREATE INDEX IF NOT EXISTS idx_loans_status ON loans(status);
CREATE INDEX IF NOT EXISTS idx_loans_created_at ON loans(created_at);

-- Unique Constraints for Data Integrity
ALTER TABLE users ADD CONSTRAINT uc_users_email UNIQUE (email);
ALTER TABLE users ADD CONSTRAINT uc_users_phone_number UNIQUE (phone_number);
ALTER TABLE wallets ADD CONSTRAINT uc_wallets_user_id UNIQUE (user_id);
ALTER TABLE wallets ADD CONSTRAINT uc_wallets_wallet_address UNIQUE (wallet_address);
