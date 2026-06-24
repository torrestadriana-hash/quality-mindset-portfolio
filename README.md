<div align="center">

<sub>

# Quality Mindset Portfolio

**Author:** Adriana Torres · Senior SDET / QA Automation Engineer

[LinkedIn](https://www.linkedin.com/in/adriana-torres-834771132) · [GitHub Repository](https://github.com/torrestadriana-hash/quality-mindset-portfolio/tree/main)

</sub>

</div>

---

<sub>

## About this project

This repository is **not** a basic "click-and-assert" automation suite. It is a **scalable, production-style QA Automation Framework** built with **Kotlin + Serenity BDD + Cucumber + Screenplay Pattern**, designed to reflect how automation is actually architected inside mature QA / SDET teams.

It demonstrates, in a single codebase, the ability to:

- Design a **layered, framework-style architecture** (not just test scripts).
- Apply **SOLID principles** to test automation code, the same discipline expected from a software engineer.
- Automate **both API and Web (E2E UI)** layers with a **shared, unified core**.
- Produce **traceable, BDD-readable requirements** (Gherkin) that any stakeholder — technical or not — can understand.
- Generate **professional, evidence-based reporting** (Serenity HTML reports + recorded execution videos + screenshots).

This project was built to show a hiring manager or technical recruiter how I think about **quality as a mindset**, not as an afterthought: maintainability, reusability, traceability and clear separation of concerns are treated as first-class requirements, exactly as they would be in a real product team.

</sub>

---

<sub>

## What this project proves to a recruiter

| Skill demonstrated | Where it shows in the repo |
|---|---|
| BDD / Gherkin requirement writing | `src/test/resources/features/**` |
| Screenplay Pattern (Tasks, Questions, Abilities, Actors) | `src/main/kotlin/com/test/suites/**` |
| API test automation (REST Assured + Serenity) | `com.test.suites.api.*` against `reqres.in` |
| Web E2E automation (Selenium WebDriver via Serenity) | `com.test.suites.web.*` against `saucedemo.com` |
| Clean architecture / SOLID applied to test code | See *SOLID principles applied* section below |
| Reporting & evidence culture | `evidence/videos`, `evidence/reports`, Serenity Single Page HTML report |
| Build & dependency management with Gradle (Kotlin DSL) | `build.gradle`, `settings.gradle`, Gradle Wrapper |
| Version control hygiene | `.gitignore`, structured Git history |

</sub>

---

<sub>

## 🎥 Execution Evidence

All execution evidence (videos and screenshots) is stored inside the `evidence/` folder of this repository, covering **both automation suites**: Web UI and API.

### Web UI Automation (SauceDemo)

📹 **Video:** [`evidence/videos/web-execution.mp4`](./evidence/videos/web-execution.mp4)
Shows the full Selenium/Serenity execution of the login, cart and checkout scenarios running against `https://www.saucedemo.com` in a real Chrome browser.

### API Automation (ReqRes)

📹 **Video:** [`evidence/videos/api-execution.mp4`](./evidence/videos/api-execution.mp4)
Shows the REST Assured + Serenity execution of the Login, Register and Get User API scenarios against `https://reqres.in`.

### Report Screenshot

🖼️ [`evidence/reports/Captura de pantalla 2026-06-23 092146.png`](<./evidence/reports/Captura de pantalla 2026-06-23 092146.png>)
Screenshot of the generated **Serenity Single Page HTML Report**, showing test results, requirements traceability and step-by-step execution detail.

> 💡 *Tip for recruiters:* you can also generate this report yourself locally by running `./gradlew test` — it will be created at `target/site/serenity/index.html`.

</sub>

---

<sub>

## Architecture

The framework follows a **clean, layered architecture** that separates *what* is being tested (requirements), *how* the actor performs it (Screenplay business logic) and *how it talks to the system under test* (low-level driver/HTTP layer).

```
quality-mindset-portfolio/
├── build.gradle                  → dependencies, plugins, test tasks
├── settings.gradle
├── gradlew / gradlew.bat         → Gradle Wrapper (no local Gradle install needed)
├── evidence/
│   ├── videos/                  → web-execution.mp4 · api-execution.mp4
│   └── reports/                 → report screenshots
├── src/
│   ├── main/kotlin/com/test/suites/
│   │   ├── common/logging/      → centralized logger factory (Loggers.kt)
│   │   ├── api/
│   │   │   ├── config/          → ApiConfig.kt (base URL, environment)
│   │   │   ├── models/          → request/response data models
│   │   │   ├── abilities/       → ApiAbilities.kt (Screenplay Ability to call REST)
│   │   │   ├── tasks/           → LoginUser, RegisterUser, GetUser
│   │   │   ├── questions/       → ApiResponseQuestions.kt (assertions on responses)
│   │   │   └── utils/           → AuthRequestBodyBuilder, ApiRequests (shared HTTP helpers)
│   │   └── web/
│   │       ├── config/          → WebConfig.kt (browser/env configuration)
│   │       ├── ui/               → SauceDemoUI.kt (PageObject-style locators)
│   │       ├── tasks/            → Tasks.kt (login, add to cart, checkout)
│   │       └── questions/        → Questions.kt (UI state assertions)
│   └── test/
│       ├── kotlin/com/test/suites/
│       │   ├── api/steps/        → ApiSteps.kt (Cucumber step definitions)
│       │   ├── api/runners/      → GetUserRunner.kt (JUnit + Cucumber runner)
│       │   ├── web/stepdefinitions/ → WebSteps.kt
│       │   └── web/runners/      → WebTestRunner.kt
│       └── resources/
│           ├── serenity.conf     → project/environment/webdriver configuration
│           └── features/
│               ├── api/getuser/get_user.feature
│               ├── api/login/login_user.feature
│               ├── api/register/register_user.feature
│               └── web/{login,cart,checkout}.feature
```

**Design highlights:**

- **Screenplay Pattern** instead of classic Page Objects only: Actors (`Ana`) perform `Tasks` and ask `Questions`, which makes scenarios read like real user behavior and keeps step definitions thin.
- **Single module, dual suite**: API and Web share the same `build.gradle`, the same `serenity.conf` and the same reporting pipeline, while staying fully decoupled at the package level (`com.test.suites.api` vs `com.test.suites.web`).
- **Centralized configuration**: `serenity.conf` defines environments, WebDriver capabilities (headless toggle, Chrome flags, incognito, disabled password manager) and reporting format (`single-page-html`).

</sub>

---

<sub>

## 🧱 SOLID principles applied to test automation

- **SRP (Single Responsibility):** each class has one reason to exist — one Target/locator repository, one request builder, one logger factory, one `Task` per business action.
- **OCP (Open/Closed):** new `Tasks` / `Questions` can be added for new features without touching existing ones.
- **DIP (Dependency Inversion):** Step Definitions depend on Screenplay abstractions (`Task`, `Question`, `Ability`) — never directly on REST Assured or Selenium WebDriver.
- **DRY (Don't Repeat Yourself):**
  - `AuthRequestBodyBuilder` centralizes JSON body creation for auth requests.
  - `withCommonApiHeaders()` / `withJsonBody()` centralize HTTP header logic.
  - `Loggers.of(...)` removes repeated logger boilerplate across classes.
  - A single `LastApiResponse` ability replaces duplicated `SerenityRest` calls.

</sub>

---

<sub>

## ⚙️ Tech stack & versions

| Component | Version |
|---|---|
| Kotlin | 1.9.23 |
| Serenity BDD | 4.2.30 |
| Cucumber JVM | 7.15.0 |
| JUnit | 4.13.2 |
| SLF4J | 2.0.9 |
| REST Assured | 5.4.0 |
| AssertJ | 3.24.2 |
| Allure | 2.29.0 |
| WebDriverManager | 5.8.0 |
| JVM target | Java 21 (Kotlin toolchain) |

**System requirements to run it locally:**

- Java 21 (JDK) installed and configured (`JAVA_HOME`).
- Google Chrome installed (required for the Web UI suite — WebDriverManager resolves the matching ChromeDriver automatically).
- Internet connection (for Gradle dependency resolution and WebDriverManager's first run).
- No local Gradle installation needed — the project ships with the **Gradle Wrapper** (`gradlew` / `gradlew.bat`).

</sub>

---

<sub>

## 🚀 How to run the project

```bash
# Clone the repository
git clone https://github.com/torrestadriana-hash/quality-mindset-portfolio.git
cd quality-mindset-portfolio

# Run the API suite only (REST Assured against reqres.in)
./gradlew apiTest

# Run the Web suite only (Selenium/Chrome against saucedemo.com)
./gradlew webTest

# Run the full regression suite (API + Web)
./gradlew test
```

After execution, open the generated report at:

```
target/site/serenity/index.html
```

> On Windows, replace `./gradlew` with `gradlew.bat`.
> By default, `headless.mode = false` in `serenity.conf`, so the Chrome browser will open visibly during the Web suite — set it to `true` for CI/headless execution.

</sub>

---

<sub>

## 🧩 Test suites overview

**API suite** — `com.test.suites.api.*`, executed against `https://reqres.in/api`:

- ✅ User Registration: success and validation failures (missing password, missing email).
- ✅ User Login: success, missing password, unregistered email.
- ✅ Get User: existing user (200) and non-existing user (404).

**Web suite** — `com.test.suites.web.*`, executed against `https://www.saucedemo.com`:

- ✅ Login: valid credentials and locked-out user.
- ✅ Cart: add single and multiple products, badge count validation.
- ✅ Checkout: full purchase flow with order confirmation.

Both suites use **Gherkin feature files** as living documentation, making the scenarios understandable to QA, Dev and Product stakeholders alike.

</sub>

---

<sub>

## 💬 Why this matters for my next role

This project was built to show, in practice, the **mindset** I bring to a QA Engineering / SDET role:

- I treat test automation as **software engineering**, applying clean architecture and SOLID, not just "writing scripts that click buttons."
- I think in terms of **maintainability and scalability**: adding a new feature or endpoint should require minimal changes to existing code.
- I value **evidence and traceability**: every run produces a report, every feature is documented in Gherkin, every execution can be recorded and shared.
- I'm comfortable owning a project **end-to-end**: from architecture decisions, to implementation, to CI-ready execution, to reporting.

I'm currently open to **SDET / QA Automation Engineer** opportunities where this mindset can add value to a product team. Feel free to connect with me on [LinkedIn](https://www.linkedin.com/in/adriana-torres-834771132) or check more of my work on [GitHub](https://github.com/torrestadriana-hash/quality-mindset-portfolio/tree/main).

</sub>

