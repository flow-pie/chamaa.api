# GitHub Actions Secrets Configuration

This document explains how to set up secrets for CI/CD pipelines.

## Required Secrets

### Fly.io Deployment

1. **FLY_API_TOKEN**
   - Go to https://fly.io/user/personal_access_tokens
   - Create new token
   - Add to GitHub Secrets

### Render Deployment

2. **RENDER_DEPLOY_HOOK_STAGING**
   - Go to Render dashboard → Service → Settings
   - Find "Deploy Hook"
   - Add to GitHub Secrets

3. **RENDER_DEPLOY_HOOK_PROD**
   - Go to Render dashboard → Service → Settings
   - Find "Deploy Hook" for production
   - Add to GitHub Secrets

### SonarQube Code Quality (Optional)

4. **SONAR_TOKEN**
   - Go to SonarQube → User → Security → Tokens
   - Generate new token
   - Add to GitHub Secrets

5. **SONAR_HOST_URL**
   - Your SonarQube server URL (e.g., https://sonarqube.example.com)
   - Add to GitHub Secrets

### Database Credentials (Environment-specific)

Set these in Render/Fly.io environment variables, NOT in GitHub secrets:

- `DB_URL` - Database connection URL
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password

## How to Add GitHub Secrets

1. Go to GitHub repository → Settings
2. Click "Secrets and variables" → "Actions"
3. Click "New repository secret"
4. Add name and value
5. Click "Add secret"

## Environment Variables in Deployment Platforms

### Fly.io

1. Create `fly.toml` with secrets:
   ```bash
   flyctl secrets set DB_URL=postgresql://...
   flyctl secrets set DB_USERNAME=postgres
   flyctl secrets set DB_PASSWORD=secure_password
   ```

2. Or set via `fly.toml`:
   ```toml
   [env]
     DB_URL = "postgresql://..."
     DB_USERNAME = "postgres"
   ```

### Render

1. Go to Service → Environment
2. Add environment variables
3. For sensitive data, click "Reference sync from GitHub"

## Workflow Secrets Usage

In GitHub Actions workflows, reference secrets as:

```yaml
env:
  MY_SECRET: ${{ secrets.MY_SECRET }}
```

## Security Best Practices

1. ✅ Never commit secrets to git
2. ✅ Use strong, unique tokens
3. ✅ Rotate tokens regularly
4. ✅ Use different credentials for staging/production
5. ✅ Enable GitHub's secret scanning
6. ✅ Restrict who can view/modify secrets

## Testing Secrets Locally

For local testing with Render/Fly.io, create `.env` file:

```bash
# Copy .env.example
cp .env.example .env

# Update with actual values
nano .env

# Use with Spring Boot
export SPRING_PROFILES_ACTIVE=prod
./mvnw spring-boot:run
```

Never commit `.env` - it's in `.gitignore`
