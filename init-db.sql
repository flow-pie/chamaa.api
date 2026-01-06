-- Initialize Chamaa API Database
-- This script runs automatically when the PostgreSQL container starts

-- Create extensions
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- Create database (if not exists)
-- Note: Database is already created via POSTGRES_DB env variable

-- Create schemas
CREATE SCHEMA IF NOT EXISTS chamaa;
GRANT USAGE ON SCHEMA chamaa TO postgres;
ALTER DEFAULT PRIVILEGES IN SCHEMA chamaa GRANT ALL ON TABLES TO postgres;

-- Log initialization
SELECT now() as initialization_time, 'Chamaa API database initialized' as message;
