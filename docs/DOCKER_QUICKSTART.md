# Docker Compose Quick Start

Get the API running with all services in 5 minutes.

## Prerequisites

```bash
# Check Docker installation
docker --version  # 20.10+
docker-compose --version  # 2.0+
```

## 5-Minute Setup

### 1. Configure Environment

```bash
# Copy environment template
cp .env.docker .env

# (Optional) Edit configuration
nano .env
```

### 2. Start Services

```bash
# Build and start all services
docker-compose up -d

# Wait for services to be healthy
docker-compose ps

# Check API health
curl http://localhost:8080/api/health
```

### 3. Verify All Services

```bash
# View logs
docker-compose logs

# Check database
docker exec -it chamaa-postgres psql -U postgres -d chamaa_db -c "\dt"

# Check redis
docker exec -it chamaa-redis redis-cli ping
```

## Common Commands

```bash
# Start services
docker-compose up -d

# Stop services (keeps data)
docker-compose stop

# Start again
docker-compose start

# View logs
docker-compose logs -f

# Stop and remove (keeps volumes)
docker-compose down

# Stop and remove everything
docker-compose down -v

# Rebuild services
docker-compose build --no-cache
docker-compose up -d
```

## Access Services

### API
- URL: http://localhost:8080
- Health: http://localhost:8080/api/health
- Swagger: http://localhost:8080/api/swagger-ui.html (if enabled)

### PostgreSQL
```bash
docker exec -it chamaa-postgres psql -U postgres -d chamaa_db
```

### Redis
```bash
docker exec -it chamaa-redis redis-cli -a redis_password
```

## Development Workflow

### Use H2 In-Memory Database (Fastest)

```bash
# Start only API (no PostgreSQL)
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up api
```

### With PostgreSQL for Testing

```bash
# Start API + PostgreSQL
docker-compose -f docker-compose.yml -f docker-compose.dev.yml --profile postgres up
```

### Debug Mode

```bash
# Start with debug port on 5005
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up api

# Connect debugger to localhost:5005
```

## Troubleshooting

### Services not starting

```bash
# Check logs
docker-compose logs

# Remove and restart
docker-compose down -v
docker-compose up -d --build
```

### Port already in use

```bash
# Change PORT in .env
PORT=8081
docker-compose restart api
```

### Database connection issues

```bash
# Check database is running
docker-compose ps postgres

# View logs
docker-compose logs postgres

# Test connection
docker exec chamaa-api curl http://postgres:5432
```

## Next Steps

- Read `DOCKER_GUIDE.md` for detailed documentation
- Read `CI_CD_PIPELINE.md` for deployment workflow
- Check `.env.docker` for available configurations

That's it! Your containerized environment is ready! 🚀
