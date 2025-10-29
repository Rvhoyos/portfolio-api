# portfolio-api
Spring Boot 3.5.7 API for portfolio content (Java 25, Gradle 9, PostgreSQL, Flyway)

[![Prod Deploy](https://github.com/Rvhoyos/portfolio-api/actions/workflows/deploy-prod.yml/badge.svg?branch=main)](https://github.com/Rvhoyos/portfolio-api/actions/workflows/deploy-prod.yml)
[![Staging Deploy](https://github.com/Rvhoyos/portfolio-api/actions/workflows/deploy-staging.yml/badge.svg?branch=staging)](https://github.com/Rvhoyos/portfolio-api/actions/workflows/deploy-staging.yml)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-6DB33F)
![Java](https://img.shields.io/badge/Java-25-007396)
![Postgres](https://img.shields.io/badge/Postgres-18-336791)
![Docker](https://img.shields.io/badge/Docker-yes-2496ED)

A Spring Boot service that powers my portfolio site content. The codebase has CI/CD setup from day one: 
- containerized app,
- versioned DB migrations,
- automated deployments to staging & prod.

---

## Stack
- **Runtime:** Spring Boot **3.5.7** (Java **25**), embedded Tomcat (8081 internal)
- **Database:** PostgreSQL **18**
- **Migrations:** Flyway (**`flyway_portfolio_history`** schema**`v1`**)
- **Packaging:** Docker (app + Flyway jobs)
- **CI/CD:** GitHub Actions: self-hosted runner

## Environments & Branches
- **staging** branch: `staging`
- **prod** branch: `main` 

Both environments run as Docker Compose stacks on an internal network (no host ports exposed).    
Reverse proxy (Caddy) will front the app(s) publicly under a domain.

## CI/CD (how it works)
- **On push** to `staging` or `main`:
  1) Build & tag the Docker image (`:staging` / `:prod`)
  2) Run Flyway migrations for the target environment
  3) Recreate the app container
- **Runner labels:** `[self-hosted, Linux, X64]`
- **Dependency graph** job runs with JDK 21 

> Workflow files live under `.github/workflows/` (see badges above).

## Configuration
- Environment is provided via **`.env.staging`** / **`.env.prod`** (e.g., `SPRING_PROFILES_ACTIVE`, DB URL/creds).
- The app expects an internal Postgres service, no DB ports are published externally.

## Project Status
- API surface: **TBD** (endpoints will be added as the frontend is built).
- Infra is ready: container build, migrations, and deploy automation are in place.

## Roadmap
- Expose via Caddy with **same-origin routing** (`/api/...`)
- Add health/actuator endpoints and basic endpoint set
- Observability (logs/metrics)

## Development (brief)
- Java 25 + Gradle users can run locally once endpoints exist.
- Container users can run a local Compose stack (app + Postgres) when the first endpoints land.

---

>This repo is intentionally concise while the API is bootstrapped, more docs will land alongside the first endpoints.
