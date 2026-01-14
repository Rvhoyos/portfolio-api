# AGENTS.md

## Project Description
The **portfolio-api** is a Spring Boot application designed to serve as the backend for a portfolio website. It manages contact form submissions (Leads) and is set up with modern Java practices.

**Key Features:**
*   **Tech Stack:** Java 25, Spring Boot 3.5.7, PostgreSQL, Flyway, Gradle.
*   **Architecture:** Layered architecture (Controller -> Service -> Repository) organized by domain (e.g., `contact`).
*   **Deployment:** Containerized with Docker, CI/CD with GitHub Actions.
*   **Database:** PostgreSQL with Flyway for schema migrations.

## File Audit

| File | Rating | Comments |
| :--- | :--- | :--- |
| `src/main/java/com/raulhoyos/portfolio_api/PortfolioApiApplication.java` | ★★★★★ | Standard Spring Boot entry point. Clean and focused. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/app/LeadService.java` | ★★★★★ | Simple interface defining the service contract. SRP compliant. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/app/LeadServiceImpl.java` | ★★★★★ | implementation of LeadService. Handles business logic and mapping. clean manual mapping. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/data/Lead.java` | ★★★★★ | Well-defined JPA entity. Uses records logic (immutable-ish where approriate) and proper JPA annotations. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/data/LeadRepository.java` | ★★★★★ | Standard Spring Data JPA repository. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/web/LeadController.java` | ★★★★★ | Clean REST controller. Uses DTOs and validation correctly. Separation of concerns is respected. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/web/dto/LeadRequest.java` | ★★★★★ | Java Record used for DTO. Includes comprehensive Bean Validation annotations. |
| `src/main/java/com/raulhoyos/portfolio_api/contact/web/dto/LeadResponse.java` | ★★★★★ | Simple Record DTO for responses. Clean. |
| `src/test/java/com/raulhoyos/portfolio_api/PortfolioApiApplicationTests.java` | ★★★★★ | Standard context load test. |
| `src/test/java/com/raulhoyos/portfolio_api/contact/web/LeadControllerIT.java` | ★★★★☆ | Good integration test. Relies on "dev" profile which might imply external dependencies or specific environment configuration. |
| `src/test/java/com/raulhoyos/portfolio_api/contact/web/LeadControllerTest.java` | ★★★★★ | Excellent slice test using `WebMvcTest` and `MockitoBean`. Tests validation and happy paths. |
| `src/test/java/com/raulhoyos/portfolio_api/contact/web/LeadRepositoryTest.java` | ★★★★★ | Good persistence layer test. Verifies mapping and DB interaction. |

## Architectural & Functional Recommendations

### General Observations
The codebase is in excellent shape. It uses modern Java features (Records, Java 25 target) and the latest Spring Boot practices. The domain is well-segmented (`contact` package), suggesting a modular monolith approach which is scalable.

### Recommendations

1.  **Java Version Compatibility**:
    *   **Observation**: The project is configured for Java 25 (`toolchain { languageVersion = JavaLanguageVersion.of(25) }`).
    *   **Rating Impact**: None on code quality, but high on build portability.
    *   **Recommendation**: Unless specific Java 25 features are being used, consider downgrading to the latest LTS (Java 21) to ensure broader compatibility with CI environments and developer machines.

2.  **DTO Mapping**:
    *   **Observation**: Mapping from `LeadRequest` to `Lead` entity is done manually in `LeadServiceImpl`.
    *   **Rating Impact**: ★★★★★ (Clean for now).
    *   **Recommendation**: As the object complexity grows, manual mapping can become error-prone. Consider introducing a mapper library like **MapStruct** to automate this while keeping it type-safe.

3.  **Test Configuration**:
    *   **Observation**: `LeadControllerIT` and `LeadRepositoryTest` use `@ActiveProfiles("dev")`.
    *   **Rating Impact**: ★★★★☆.
    *   **Recommendation**: Ensure that the "dev" profile doesn't rely on a pre-provisioned external database. Using **Testcontainers** for integration tests is best practice to ensure tests are reproducible and isolated from the environment.

4.  **API Documentation**:
    *   **Observation**: The API is well-structured but currently lacks auto-generated documentation.
    *   **Recommendation**: Add **Springdoc OpenAPI** (Swagger) to automatically generate API documentation. This matches the "clean and modern" vibe of the project and helps with the "Roadmap" item of exposing the API.
