# Mundo Tech — Backend

REST API for a digital newspaper platform where authors create articles and managers review and publish them.

Built with **Java + Spring Boot 3**, following a 3-layer MVC architecture with DTOs and Mappers.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Environment Variables](#environment-variables)
- [API Endpoints](#api-endpoints)
- [Article Lifecycle](#article-lifecycle)
- [Key Technical Decisions](#key-technical-decisions)
- [Team](#team)

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 25 | Main programming language |
| Spring Boot 3.5.16 | REST API framework |
| Spring Data JPA + Hibernate | ORM and database interaction |
| PostgreSQL | Relational database |
| Lombok | Boilerplate reduction |
| spring-dotenv | Environment variable management via `.env` |
| JUnit Jupiter + Mockito | Unit testing |
| H2 | In-memory database for tests |
| Spring Boot Actuator | Application health monitoring |

---

## Project Structure

```
mundotech-backend/
├── .env                          ← Local environment variables (not committed)
├── .env.example                  ← Environment variable template
├── pom.xml                       ← Maven dependencies
└── src/
    ├── main/
    │   ├── java/com/periodico/mundotech/
    │   │   ├── config/
    │   │   │   └── CorsConfig.java               ← CORS configuration
    │   │   ├── controller/
    │   │   │   ├── ArticleController.java         ← Article CRUD endpoints
    │   │   │   ├── RoleController.java            ← Role CRUD endpoints
    │   │   │   ├── StorageController.java         ← File upload endpoints
    │   │   │   └── UserController.java            ← User CRUD endpoints
    │   │   ├── dto/
    │   │   │   ├── request/                       ← What the client sends
    │   │   │   └── response/                      ← What the client receives
    │   │   ├── entity/
    │   │   │   ├── enums/
    │   │   │   │   └── ArticleStatus.java         ← DRAFT, IN_REVIEW, PUBLISHED
    │   │   │   ├── Article.java
    │   │   │   ├── FileData.java                  ← Image file entity
    │   │   │   └── Role.java
    │   │   ├── mapper/                            ← Entity ↔ DTO conversion
    │   │   ├── repository/                        ← Spring Data JPA interfaces
    │   │   ├── service/
    │   │   │   ├── implement/                     ← Business logic implementation
    │   │   │   ├── ArticleService.java            ← Contract interface
    │   │   │   ├── RoleService.java
    │   │   │   ├── StorageService.java            ← File upload logic
    │   │   │   └── UserService.java
    │   │   └── Main.java                          ← Application entry point
    │   └── resources/
    │       ├── application.properties             ← Main configuration
    │       └── application-local.properties       ← Local overrides (not committed)
    └── test/                                      ← Unit tests (JUnit + Mockito)
```

---

## Getting Started

### Prerequisites

- Java 25
- Maven
- PostgreSQL running locally

### 1. Clone the repository

```bash
git clone https://github.com/FactoriaF5-MundoTech-Team2/PeriodicoTeam2.git
cd PeriodicoTeam2/mundotech-backend
```

### 2. Create the database

```sql
CREATE DATABASE mundotech_db;
```

### 3. Configure environment variables

Copy the example file and fill in your credentials:

```bash
cp .env.example .env
```

Edit `.env`:

```
DB_USERNAME=your_postgres_username
DB_PASSWORD=your_postgres_password
```

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`

### 5. Run the tests

```bash
./mvnw test
```

---

## Environment Variables

| Variable | Description | Example |
|---|---|---|
| `DB_USERNAME` | PostgreSQL username | `postgres` |
| `DB_PASSWORD` | PostgreSQL password | `yourpassword` |

These are loaded automatically via `spring-dotenv` from the `.env` file in the project root.

---

## API Endpoints

### Roles

```
POST   /api/v1/roles           Create a role
GET    /api/v1/roles           List all roles
GET    /api/v1/roles/{id}      Get role by ID
PUT    /api/v1/roles/{id}      Update a role
DELETE /api/v1/roles/{id}      Delete a role
```

### Users

```
POST   /api/v1/users           Create a user
GET    /api/v1/users           List all users
GET    /api/v1/users/{id}      Get user by ID
PUT    /api/v1/users/{id}      Update a user
DELETE /api/v1/users/{id}      Delete own account (requesterId required)
```

### Articles — CRUD

```
POST   /api/articles                        Create article (defaults to DRAFT)
GET    /api/articles                        List all articles
GET    /api/articles/{id}                   Get article by ID
GET    /api/articles/author/{authorId}      Articles by author
GET    /api/articles/status/draft           Articles in DRAFT
GET    /api/articles/status/in-review       Articles in IN_REVIEW
GET    /api/articles/status/published       Articles in PUBLISHED
PUT    /api/articles/{id}                   Update article (author only)
DELETE /api/articles/{id}                   Delete article (author only)
```

### Articles — Editorial Workflow

```
PUT /api/articles/{id}/submit-review        Author sends to review
PUT /api/articles/{id}/approve              Manager approves → PUBLISHED
PUT /api/articles/{id}/reject               Manager rejects → back to DRAFT
```

### Storage

```
POST /api/storage/upload                    Upload image file
GET  /api/storage/{filename}               Retrieve image file
```

---

## Article Lifecycle

```
[DRAFT] ──(author: submit-review)──► [IN_REVIEW] ──(manager: approve)──► [PUBLISHED]
                                           │
                                    (manager: reject)
                                           │
                                           ▼
                                        [DRAFT]
```

All articles are created with status `DRAFT` by default. The backend enforces this regardless of what the client sends.

---

## Key Technical Decisions

**DTOs and Mappers** — The client never interacts with JPA entities directly. Request DTOs define what comes in; Response DTOs define what goes out. Mappers handle the translation between layers.

**Role-based validation without JWT** — Since full authentication is pending, role validation is done by querying the database at runtime using a `managerId` query parameter. Only users with the `manager` role can approve or reject articles.

**Cascade delete on User** — Deleting a user automatically removes all their associated articles (`CascadeType.ALL + orphanRemoval = true`).

**File storage** — Images are stored on disk via `StorageService`. The database only stores the file reference path, not the binary data.

---

## Team

| Name | Role |
|---|---|
| Viviana | Product Owner |
| Chiara | Scrum Master |
| Nayeli | Developer |
| Johanna | Developer |

---

## Repository

[github.com/FactoriaF5-MundoTech-Team2/PeriodicoTeam2](https://github.com/FactoriaF5-MundoTech-Team2/PeriodicoTeam2)