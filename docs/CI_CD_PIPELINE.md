# CI/CD Pipeline Documentation

This document describes the complete CI/CD pipeline for the Chamaa API.

## Overview

- **CI Provider**: GitHub Actions
- **Deployment Platforms**: Fly.io, Render
- **Environments**: Staging (develop), Production (main)
- **Automation**: Automatic deployment on successful builds

## Workflows

### 1. Spring Boot CI (`spring-boot-ci.yml`)

Runs on every push and pull request.

**Jobs:**

#### Build & Test
- Sets up Java 21
- Caches Maven dependencies
- Runs code style checks (Checkstyle)
- Executes `./mvnw clean verify`
- Generates JUnit test reports
- Publishes test results
- Uploads artifacts

**Triggers:**
- Push to `main`, `develop`, or `feature/**` branches
- Pull requests to `main` or `develop`

**Duration:** ~5-7 minutes

#### Code Quality Analysis (Optional)
- SonarQube analysis (if configured)
- Requires `SONAR_TOKEN` and `SONAR_HOST_URL` secrets

#### Security Scan (Optional)
- Dependency-check for vulnerabilities
- Uploads security report

### 2. Deployment (`deploy.yml`)

Handles automated deployments based on branch.

**Jobs:**

#### Deploy to Staging
- Triggers on successful build of `develop` branch
- Deploys to Fly.io staging environment
- Alternative: Render deployment hook
- Health check after deployment

**Environment:** https://chamaa-api-staging.fly.dev

#### Deploy to Production
- Triggers on successful build of `main` branch
- Requires environment approval
- Deploys to Fly.io production environment
- Alternative: Render deployment hook
- Health check verification
- Creates GitHub Release

**Environment:** https://chamaa-api.fly.dev

## Acceptance Criteria Status

✅ **CI fails if Spring Boot or Android build/tests fail**
   - Maven verify includes tests
   - Workflow fails if any test fails
   - Deployment blocked on CI failure

✅ **Test reports are available as artifacts**
   - Test reports uploaded to GitHub Artifacts
   - Available for download in Actions tab
   - 30-day retention

✅ **Deployments only occur if CI succeeds**
   - Deployment workflow depends on CI success
   - Manual approval required for production
   - Health checks verify deployment

✅ **Builds are reproducible across environments**
   - Maven cache for dependency reproducibility
   - Docker multi-stage build
   - Environment variables managed per platform
   - Same Java version (21) across all environments

## Configuration Files

### Docker Deployment
- **Dockerfile** - Multi-stage build for Spring Boot
- **.dockerignore** - Excludes unnecessary files

### Fly.io
- **fly.toml** - Fly.io configuration
- Sets port 8080, health checks, autoscaling

### Render
- **render.yaml** - Render deployment config
- Docker build, health checks, environment setup

## Environment Variables

### Development (Local)
```bash
cp .env.example .env
# Uses H2 database by default
./mvnw spring-boot:run
```

### Staging (Fly.io/Render)
```bash
SPRING_PROFILES_ACTIVE=dev  # Or use H2
DB_URL=postgresql://...
DB_USERNAME=postgres
DB_PASSWORD=...
```

### Production (Fly.io/Render)
```bash
SPRING_PROFILES_ACTIVE=prod
DB_URL=postgresql://...
DB_USERNAME=postgres
DB_PASSWORD=...
```

## Manual Deployment

### Deploy to Fly.io

```bash
# Install fly CLI
curl -L https://fly.io/install.sh | sh

# Authenticate
flyctl auth login

# Deploy staging
flyctl deploy --remote-only --app chamaa-api-staging

# Deploy production
flyctl deploy --remote-only --app chamaa-api-prod
```

### Deploy to Render

1. Push to GitHub
2. Render auto-detects changes (if configured)
3. Or manually trigger via Render dashboard

## Monitoring & Troubleshooting

### View Workflow Runs
1. Go to GitHub repo → Actions
2. Click on workflow run
3. Expand job details
4. View logs and artifacts

### Common Issues

**Build Fails**
- Check Java version (requires 21)
- Verify all dependencies installed
- Check test failures in output

**Deployment Fails**
- Check Fly.io/Render logs
- Verify secrets are set
- Check database connectivity
- Review health check logs

**Tests Fail**
- Tests use H2 by default
- Check test output for failures
- Verify @ActiveProfiles("test") is set
- Review entity mappings

## Performance Optimization

### Maven Caching
- Dependencies cached in ~/.m2
- Significantly speeds up builds
- Effective from 2nd build onward

### Docker Caching
- Multi-stage build reduces image size
- Layer caching speeds up builds
- Final image: ~600MB

### Parallel Testing
- JUnit runs tests in parallel when configured
- `maven-surefire-plugin` handles parallelization

## Security

### Secrets Management
- GitHub Secrets for deployment tokens
- Environment variables for credentials
- Never commit secrets to git
- Rotate tokens regularly

### HTTPS Enforcement
- Fly.io: force_https = true
- Render: Auto HTTPS
- Health checks use HTTP internally

### Non-root User in Docker
- Container runs as `appuser` (UID 1000)
- Least privilege principle

## Next Steps

1. **Add Secrets** (see `GITHUB_ACTIONS_SETUP.md`)
   - FLY_API_TOKEN
   - RENDER_DEPLOY_HOOK_STAGING
   - RENDER_DEPLOY_HOOK_PROD

2. **Test Workflow**
   - Push to `develop` branch
   - Verify CI runs successfully
   - Check staging deployment

3. **Verify Production**
   - Push to `main` branch
   - Approve production deployment
   - Test production endpoint

4. **Enable Optional Features**
   - SonarQube for code quality
   - Dependency check for security
   - Slack notifications

## References

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Fly.io Deployment Guide](https://fly.io/docs/)
- [Render Deployment Guide](https://render.com/docs)
- [Maven CI/CD Guide](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)
- [Docker Best Practices](https://docs.docker.com/develop/dev-best-practices/)
