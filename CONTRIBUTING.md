## Contributing to **Chamaa.api**

Thanks for wanting to make **Chamaa.api** better!
This project thrives on clean code, clear commits, and community collaboration.

---

## Code of Conduct

Be respectful, collaborative, and inclusive.
No spammy PRs, no plagiarism, no copy-paste code.
Focus on quality, not quantity.

---

## Development Setup

### Prerequisites
- **Node.js** 18+ ([Download](https://nodejs.org/))
- **pnpm** 8+ (preferred for monorepo): `npm install -g pnpm`
- **Git**

### Step-by-Step

1. **Fork** the repository on GitHub
2. **Clone** your fork

   ```bash
   git clone https://github.com/YOUR-USERNAME/chamaa.api.git
   cd chamaa.api
   ```
3. **Add upstream remote** (to sync with main repo)

   ```bash
   git remote add upstream https://github.com/flow-pie/chamaa.api.git
   ```
4. **Create a new branch** from `main`

   ```bash
   git checkout -b feature/awesome-update
   ```
5. **Install dependencies**

   ```bash
   pnpm install
   ```
6. **Setup environment variables**

   ```bash
   cp .env.example .env
   # Edit .env with necessary configuration
   ```
7. **Start development server**

   ```bash
   pnpm dev
   ```
8. **Run tests and linting**

   ```bash
   pnpm test      # Run all tests
   pnpm lint      # Check code style and formatting
   ```
9. **Make your changes** and test thoroughly

10. **Commit and push** your changes (see commit message guidelines below)

---

## Branch Naming Convention

Use **clear, scoped branch names** that describe intent:

| Type     | Example                           |
| -------- | --------------------------------- |
| Feature  | `feature/add-group-loans`         |
| Fix      | `fix/resolve-deposit-bug`         |
| Docs     | `docs/update-readme`              |
| Refactor | `refactor/optimize-ledger-module` |
| Test     | `test/improve-api-tests`          |

> Keep branch names lowercase with hyphens. Avoid vague names like `update` or `changes`.

---

## Commit Message Etiquette

Follow the **Conventional Commits** style:

```
<type>(scope): <short summary>
```

### Common Types

| Type     | Purpose                                    |
| -------- | ------------------------------------------ |
| feat     | A new feature                              |
| fix      | A bug fix                                  |
| docs     | Documentation only                         |
| style    | Code style changes (no logic impact)       |
| refactor | Code restructuring without behavior change |
| test     | Adding or improving tests                  |
| chore    | Tooling, CI, license, or maintenance work  |

### Examples

```
feat(wallet): add transaction history API
fix(ui): correct misaligned balance card
docs(readme): add API usage examples
chore(license): update MIT license year
```

> Keep messages concise and descriptive.
> Use present tense (“add feature” not “added feature”).

---

---

## 🎯 Choosing an Issue to Work On

1. **Check the [Issues tab](https://github.com/flow-pie/chamaa.api/issues)** on GitHub
2. **Look for labels**:
   - `good first issue` — Perfect for beginners, well-scoped tasks
   - `help wanted` — More complex issues that need community help
3. **Comment on the issue** before starting: "I'd like to work on this" — this prevents duplicate work
4. **Ask questions** in the issue if requirements are unclear
5. **Read the issue description carefully** — it contains acceptance criteria

---

## Pull Request Workflow

### Before You Submit
- [ ] Your branch is **up to date** with `main`: `git pull upstream main`
- [ ] All **tests pass**: `pnpm test`
- [ ] **No linting errors**: `pnpm lint`
- [ ] Your commits follow **conventional commit** style
- [ ] You've **signed your commits** (recommended): `git commit -s`
- [ ] Related **issue is linked** in PR description

### Creating a PR
1. **Push your branch**: `git push origin feature/your-feature-name`
2. **Create a Pull Request** on GitHub with:
   - Clear title describing the change
   - Description of what you changed and why
   - Link to related issue (e.g., "Fixes #123")
   - Screenshots/videos if applicable

### PR Review & Feedback
- **Expected review time**: 2-5 business days
- **Reviewers**: Project maintainers will be auto-assigned
- **Feedback**: Address comments and push updates
- **Re-request review** after making changes
- **Approval**: Need at least 1 approval to merge

### Merge & Deploy
- Once approved, maintainers will merge your PR
- Your contribution will be in the next release! 🎉

---

## Commit Signing (Recommended but optional)

To verify authorship and improve project security, please **sign your commits**.

### GPG Setup

```bash
gpg --full-generate-key
gpg --list-secret-keys --keyid-format=long
git config --global user.signingkey <your-key-id>
git config --global commit.gpgsign true
```

### SSH Setup

```bash
ssh-keygen -t ed25519 -C "your_email@example.com"
git config --global gpg.format ssh
git config --global user.signingkey ~/.ssh/id_ed25519.pub
```

To verify:

```bash
git log --show-signature
```

---

## License-Related Commits

When modifying legal files (e.g. `LICENSE`, `NOTICE`):

```
chore(license): update copyright year
chore(license): add third-party acknowledgments
```

> Use `chore:` to separate legal work from code commits.

---

## Legal: Developer Certificate of Origin (DCO)

By contributing to **Chamaa.api**, you agree to the **Developer Certificate of Origin (DCO)**.
This is a simple statement that confirms:

> “I wrote this code (or have the right to contribute it) and I agree to submit it under this project’s license.”

Each commit must include a `Signed-off-by` line, which you can add automatically using:

```bash
git commit -s -m "feat(wallet): add new transaction API"
```

This will append your signature like so:

```
Signed-off-by: username <email>
```

Your sign-off certifies that your contribution complies with the DCO.
You can read the full text of the DCO here:
[https://developercertificate.org/](https://developercertificate.org/)

---


```bash
git commit -s -m "chore(license): update MIT license"
```

This adds a `Signed-off-by:` line automatically.

---

## Final Checklist Before PR

* [ ] My code follows the project style guide
* [ ] I’ve added or updated tests if necessary
* [ ] I’ve updated documentation where relevant
* [ ] I’ve run `npm run lint` and `npm test` with no errors
* [ ] I’ve signed my commits

---

###  Need Help?

If you’re stuck, open a **discussion** or reach out via **issues**.
No question is too small — we’d rather help early than fix later 

