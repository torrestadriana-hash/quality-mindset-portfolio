## About the Author

This automation framework is part of the professional QA Engineering portfolio of:

**Adriana Torres**  
Senior SDET (QA Automation Engineer)

Focused on building scalable test automation frameworks, improving test reliability, and applying real-world software engineering principles to QA practices.

## Quality Mindset Portfolio

This project goes beyond a basic automation suite and is designed as a scalable QA Automation Framework built with Kotlin + Serenity BDD + Cucumber + Screenplay Pattern.

It follows a modular approach that simulates real-world automation architecture used in modern QA teams, separating concerns between API and Web testing while sharing a unified core structure.

## Requirements to run the project

To execute this project, the following versions are required:

kotlinVersion       = '1.9.23'  
serenityVersion     = '4.2.30'  
cucumberVersion     = '7.15.0'  
junitVersion        = '4.13.2'  
slf4jVersion        = '2.0.9'  
restAssuredVersion  = '5.4.0'  
assertjVersion      = '3.24.2'  
allureVersion       = '2.29.0'  
webdrivermgrVersion = '5.8.0'

System requirements:
- Java 21 installed and configured
- Google Chrome installed (for Web UI execution)
- Internet connection for dependency download and WebDriverManager

## Test suites

- **API** → `com.test.suites.api.*` — REST tests against https://reqres.in
- **Web** → `com.test.suites.web.*` — end-to-end UI tests against https://www.saucedemo.com

Both suites share:

- The same `build.gradle` (single module, no submodules).
- The same `serenity.conf` (`src/test/resources/serenity.conf`).
- The same Serenity report (`single-page-html`, latest HTML theme)
  generated in `target/site/serenity/index.html`.
- The same design conventions (Screenplay + SOLID principles).

## The framework follows a clean layered architecture

```
quality-mindset-portfolio/
├── build.gradle                
├── settings.gradle
├── gradlew / gradlew.bat
├── src/
│   ├── main/kotlin/com/test/suites/
│   │   ├── common/logging/     
│   │   ├── api/
│   │   │   ├── config/         
│   │   │   ├── models/          
│   │   │   ├── abilities/      
│   │   │   ├── tasks/           
│   │   │   ├── questions/      
│   │   │   └── utils/        
│   │   └── web/
│   │       ├── config/         
│   │       ├── ui/              
│   │       ├── tasks/           
│   │       └── questions/       
│   └── test/
│       ├── kotlin/com/test/suites/
│       │   ├── api/steps/       
│       │   ├── api/runners/   
│       │   ├── web/stepdefinitions/  
│       │   ├── web/runners/     
│       │   └── runners/         
│       └── resources/
│           ├── serenity.conf   
│           └── features/
│               ├── api/getuser/get_user.feature
│               ├── api/login/login_user.feature
│               ├── api/register/register_user.feature
│               └── web/{login,cart,checkout}.feature
```

This structure ensures:

Separation of concerns  
High maintainability  
Easy scalability for new services or UI modules  
Reusable components across API and Web layers

## SOLID principles applieds

- **SRP**: each class has a single responsibility (one Target repository,
  one builder, one logger factory, one Task per business action).
- **OCP**: new Tasks/Questions can be added without modifying existing ones.
- **DIP**: Step Definitions depend on Screenplay abstractions (`Task`, `Question`,
  `Ability`), never directly on REST Assured or WebDriver.
- **DRY**: duplicated code was removed and centralized:
  - `AuthRequestBodyBuilder` centralizes JSON creation
  - `withCommonApiHeaders()` / `withJsonBody()` centralize HTTP headers
  - `Loggers.of(...)` avoids repeating logger setup
  - `SerenityRest` duplication was removed and replaced with a single `LastApiResponse`

## How to run

```bash
# Run API suite only
./gradlew apiTest

# Run Web suite only
./gradlew webTest

# Run all tests
./gradlew test