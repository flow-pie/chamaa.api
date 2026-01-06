# Docker and Docker Compose Guide

This guide explains how to use Docker and Docker Compose for Chamaa API development and deployment.

## Prerequisites

- Docker 20.10+
- Docker Compose 2.0+
- 4GB RAM minimum
- 2GB free disk space

## Quick Start

### 1. Production Environment

```bash
# Copy environment configuration
cp .env.docker .env

# Start all services (API, PostgreSQL, Redis)
docker-compose up -d

# View logs
docker-compose logs -f api

# Stop services
docker-compose down

# Remove volumes (cleanup)
docker-compose down -v
```

### 2. Development Environment

```bash
# Start with development overrides and H2 database
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up api

# With PostgreSQL for testing
docker-compose -f docker-compose.yml -f docker-compose.dev.yml --profile postgres up

# View logs
docker-compose logs -f api

# Stop services
docker-compose down
```

## Services

### PostgreSQL Database

- **Port**: 5432 (configurable via POSTGRES_PORT)
- **Username**: postgres (configurable via DB_USERNAME)
- **Password**: postgres (configurable via DB_PASSWORD)
- **Database**: chamaa_db
- **Volume**: postgres_data (persists data)
- **Health Check**: Automatic

```bash
# Connect to PostgreSQL
docker exec -it chamaa-postgres psql -U postgres -d chamaa_db

# View database
docker exec -it chamaa-postgres psql -U postgres -d chamaa_db -c "\dt"

# Backup database
docker exec chamaa-postgres pg_dump -U postgres chamaa_db > backup.sql

# Restore database
docker exec -i chamaa-postgres psql -U postgres chamaa_db < backup.sql
```

### Redis Cache

- **Port**: 6379 (configurable via REDIS_PORT)
- **Password**: redis_password (configurable via REDIS_PASSWORD)
- **Volume**: redis_data (persists data)
- **Health Check**: Automatic

```bash
# Connect to Redis
docker exec -it chamaa-redis redis-cli

# With password auth
docker exec -it chamaa-redis redis-cli -a redis_password

# Check Redis stats
docker exec -it chamaa-redis redis-cli INFO

# Clear all data
docker exec -it chamaa-redis redis-cli FLUSHALL
```

### Spring Boot API

- **Port**: 8080 (configurable via PORT)
- **Health Check**: GET /api/health
- **Debug Port**: 5005 (development only)
- **Volumes**:
  - Source code (read-only)
  - Maven cache (for faster builds)

```bash
# View API logs
docker-compose logs -f api

# Execute command in running container
docker exec chamaa-api curl http://localhost:8080/api/health

# View API container info
docker inspect chamaa-api

# Execute bash in container
docker exec -it chamaa-api bash
```

## Configuration

### Environment Variables

All configuration is done via `.env` file:

```bash
# Application
PORT=8080
SPRING_PROFILES_ACTIVE=prod

# Database
DB_URL=jdbc:postgresql://postgres:5432/chamaa_db
DB_USERNAME=postgres
DB_PASSWORD=postgres

# Redis
REDIS_HOST=redis
REDIS_PORT=6379
REDIS_PASSWORD=redis_password
```

### Override Environment Variables

```bash
# Pass via command line
docker-compose run -e DB_PASSWORD=newsecure api

# Edit .env file
nano .env
docker-compose up -d api --force-recreate
```

## Volumes

### Persistent Volumes

- `postgres_data` - PostgreSQL database files
- `redis_data` - Redis persistent data

### View Volume Information

```bash
# List all volumes
docker volume ls

# Inspect volume
docker volume inspect chamaa_postgres_data

# Remove volume
docker volume rm chamaa_postgres_data
```

## Building Images

### Build Production Image

```bash
# Build with cache
docker build -t chamaa-api:latest .

# Build without cache
docker build --no-cache -t chamaa-api:latest .

# Build with build args
docker build \
  --build-arg JAR_FILE=target/chamaa-api-*.jar \
  -t chamaa-api:latest .
```

### Build Development Image

```bash
docker build -f Dockerfile.dev -t chamaa-api:dev .
```

## Network Communication

Services communicate via internal network: `chamaa-network`

- **Database**: `postgres:5432`
- **Redis**: `redis:6379`
- **API**: `api:8080` (internal), `localhost:8080` (external)

## Debugging

### View Logs

```bash
# All services
docker-compose logs

# Specific service
docker-compose logs api

# Follow logs
docker-compose logs -f api

# Last 100 lines
docker-compose logs --tail=100 api

# With timestamps
docker-compose logs --timestamps api
```

### Access Container Shell

```bash
# Bash shell
docker exec -it chamaa-api bash

# PostgreSQL shell
docker exec -it chamaa-postgres psql -U postgres

# Redis CLI
docker exec -it chamaa-redis redis-cli
```

### Health Checks

```bash
# View health status
docker-compose ps

# Manual health check
docker exec chamaa-api curl http://localhost:8080/api/health

# View service health details
docker inspect --format='{{.State.Health}}' chamaa-api
```

## Performance Optimization

### Caching

- **Maven Cache**: ~/.m2 mounted for faster builds
- **Docker Layer Cache**: Multi-stage builds reduce image size
- **Image Cache**: `cache_from` in docker-compose

### Volume Mounts

- **Read-only**: Source code mounted as read-only (:ro)
- **Cached**: Maven cache with :cached flag
- **Delegated**: Used for better performance on Mac/Windows

## Troubleshooting

### Service Won't Start

```bash
# Check logs
docker-compose logs api

# Remove containers and restart
docker-compose down
docker-compose up -d --force-recreate

# Check resources
docker stats
```

### Database Connection Fails

```bash
# Test connection
docker exec chamaa-api curl http://postgres:5432

# Check health
docker-compose ps postgres

# View PostgreSQL logs
docker-compose logs postgres
```

### Port Already in Use

```bash
# Change port in .env
PORT=8081

# Or find process using port
lsof -i :8080
kill -9 <PID>
```

### Out of Disk Space

```bash
# Clean up
docker-compose down -v
docker system prune -a --volumes
```

## Production Considerations

### Security

1. **Change Default Passwords**
   ```bash
   DB_PASSWORD=your_secure_password
   REDIS_PASSWORD=your_redis_password
   ```

2. **Use Environment Files**
   - Don't commit .env with secrets
   - Use .env.example for documentation
   - Use secret management in production

3. **Network Security**
   - Don't expose Redis publicly
   - Use HTTPS for API
   - Implement firewall rules

### Backup and Recovery

```bash
# Database backup
docker exec chamaa-postgres pg_dump -U postgres chamaa_db | gzip > backup.sql.gz

# Redis backup
docker exec chamaa-redis redis-cli BGSAVE
docker cp chamaa-redis:/data/dump.rdb ./dump.rdb

# Restore database
gunzip < backup.sql.gz | docker exec -i chamaa-postgres psql -U postgres chamaa_db
```

### Monitoring

```bash
# Container resource usage
docker stats chamaa-api

# View logs with filtering
docker-compose logs api | grep ERROR

# Monitor processes
docker top chamaa-api
```

## Docker Images Sizes

- **Production**: ~600MB (JRE only)
- **Development**: ~800MB (includes Maven)

## Advanced Usage

### Run One-Off Commands

```bash
# Run tests
docker-compose run api mvn test

# Build only
docker-compose run api mvn clean verify

# Execute custom command
docker-compose run api mvn checkstyle:check
```

### Multi-Stage Build

Production Dockerfile uses multi-stage build:
1. **Builder Stage**: Maven compilation
2. **Runtime Stage**: Optimized JRE image

Benefits:
- Smaller final image
- Faster deployment
- No build tools in production

## References

- [Docker Documentation](https://docs.docker.com/)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [PostgreSQL Docker Hub](https://hub.docker.com/_/postgres)
- [Redis Docker Hub](https://hub.docker.com/_/redis)
- [OpenJDK Docker Hub](https://hub.docker.com/_/openjdk)

## Quick Reference

```bash
# Build and start
docker-compose up -d --build

# Stop all services
docker-compose stop

# Start all services
docker-compose start

# View running containers
docker-compose ps

# View service logs
docker-compose logs -f

# Remove everything
docker-compose down -v

# Rebuild specific service
docker-compose build --no-cache api

# Scale service (if stateless)
docker-compose up -d --scale api=2
```
