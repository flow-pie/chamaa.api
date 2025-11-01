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

1. **Fork** the repository
2. **Clone** your fork

   ```bash
   git clone https://github.com/flow-pie/chamaa.api.git
   cd chamaa.api
   ```
3. **Create a new branch**

   ```bash
   git checkout -b feature/awesome-update
   ```
4. **Install dependencies** and start coding 

   ```bash
   npm install
   npm run dev
   ```

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

## Pull Request Guidelines

* Make sure your branch is **up to date** with `main`
* Run all **tests and linters** before submitting
* Write a **clear PR title** and short description
* Link related **issues** in the PR body
* Avoid bundling unrelated changes in one PR

> NOTE : PRs with clean commits and descriptions get merged faster.

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

Perfect 👌 — here’s a clean **DCO section** you can drop straight into your `CONTRIBUTING.md` file.
It’s concise, professional, and matches your current markdown style:

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

