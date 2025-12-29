# CI/CD Quick Setup Guide

## Prerequisites

- GitHub repository access
- Fly.io or Render account (or both)
- GitHub repository with Secrets enabled

## 5-Minute Setup

### Step 1: Add GitHub Secrets

Go to GitHub Repo → Settings → Secrets and variables → Actions

Add these secrets:

```
FLY_API_TOKEN = [Your Fly.io API token]
RENDER_DEPLOY_HOOK_STAGING = [Your Render staging webhook]
RENDER_DEPLOY_HOOK_PROD = [Your Render production webhook]
```

**Get Fly.io Token:**
```bash
# Visit https://fly.io/user/personal_access_tokens
# Create new token
# Copy and add to GitHub Secrets
```

**Get Render Webhooks:**
1. Render Dashboard → Service
2. Settings → Deploy Hook
3. Copy webhook URL
4. Add to GitHub Secrets

### Step 2: Deploy Configuration

The workflow files are ready:

- `.github/workflows/spring-boot-ci.yml` - CI pipeline
- `.github/workflows/deploy.yml` - Deployment pipeline
- `Dockerfile` - Docker image build
- `fly.toml` - Fly.io configuration
- `render.yaml` - Render configuration

### Step 3: Test the Pipeline

```bash
# Push to develop branch
git push origin develop

# Watch GitHub Actions
# Go to Actions tab
# Monitor build progress
# Deployment auto-triggers on success
```

## Verify It Works

1. **Check CI Status**
   - Go to Actions tab
   - See build logs
   - Verify tests pass

2. **Check Deployment**
   - Visit staging URL after develop push
   - Visit production URL after main push

3. **View Artifacts**
   - Go to build run details
   - Download test reports

## Common Commands

```bash
# Run CI locally
./mvnw clean verify

# Build Docker image
docker build -t chamaa-api:latest .

# Deploy with Fly.io
flyctl deploy --remote-only

# Check deployment logs
flyctl logs --app chamaa-api-staging
```

## Troubleshooting

**Build fails?**
```bash
# Test locally first
./mvnw clean verify
```

**Deployment fails?**
```bash
# Check platform logs
flyctl logs --app chamaa-api-prod
```

**Tests fail?**
```bash
# Run tests locally
./mvnw test
# Check test output
```

## What's Automated

✅ Build on every push
✅ Run all unit tests
✅ Generate test reports
✅ Upload artifacts
✅ Deploy to staging (develop)
✅ Deploy to production (main)
✅ Health checks
✅ Create releases (production)

## Documentation

- **CI/CD Details**: `docs/CI_CD_PIPELINE.md`
- **Secrets Setup**: `docs/GITHUB_ACTIONS_SETUP.md`
- **Dockerfile**: `Dockerfile`
- **Fly.io Config**: `fly.toml`
- **Render Config**: `render.yaml`

## Next Steps

1. ✅ Add GitHub Secrets
2. ✅ Push to develop
3. ✅ Verify staging deployment
4. ✅ Push to main
5. ✅ Verify production deployment
6. ✅ Monitor with logs

That's it! Your CI/CD pipeline is ready!
