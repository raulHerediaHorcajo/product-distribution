# Product Distribution Dashboard

A full-stack application for managing and visualizing product distribution across warehouses and stores. The application provides an interactive dashboard with map visualization, data tables, and detailed metrics.

## Tech Stack

### Backend
- **Spring Boot 3.4.5** (Java 17)
- **PostgreSQL 16** - Database
- **Maven** - Build tool

### Frontend
- **Angular 18** - Framework
- **TypeScript** - Language
- **Leaflet** - Map visualization
- **Chart.js / ng2-charts** - Data visualization
- **ng-select** - Enhanced dropdowns
- **RxJS** - Reactive programming

### Development Tools
- **Docker & Docker Compose** - Containerization
- **ESLint** - Code linting
- **Prettier** - Code formatting
- **pgAdmin** - Database administration

## Prerequisites

- **Node.js** (v18 or higher)
- **Java 17** (JDK)
- **Maven 3.6+**
- **Docker & Docker Compose** (optional, only needed for PostgreSQL testing)
- **npm** or **yarn**

## Development Setup

### Recommended Approach: Local Development (No Docker Required)

The default `local` profile uses H2 in-memory database, so no Docker is needed for daily development. This provides the fastest setup with hot reload.

#### Step 1: Run Backend Locally

In a terminal, navigate to the backend directory and run:

```bash
cd backend
mvn spring-boot:run
```

The backend will start with hot reload enabled (Spring Boot DevTools). The default `local` profile uses H2 in-memory database, so no database setup is required.

#### Step 2: Run Frontend Locally

In another terminal, navigate to the frontend directory:

```bash
cd frontend
npm install  # Only needed the first time
npm start    # or: ng serve
```

The frontend will start with hot reload enabled (Angular CLI).

#### Access Points

- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **H2 Console**: http://localhost:8080/h2-console (when backend is running)

**Advantages:**
- ✅ No Docker required - fastest setup
- ✅ Automatic hot reload in backend (Spring Boot DevTools)
- ✅ Automatic hot reload in frontend (Angular CLI)
- ✅ Easy debugging from your IDE
- ✅ Fast development without rebuilds
- ✅ Zero configuration needed

### Alternative Approach: Docker Services + PostgreSQL

Use this approach when you need to test with PostgreSQL (same as production).

#### Step 1: Start PostgreSQL in Docker

```bash
docker compose up postgres pgadmin -d
```

#### Step 2: Run Backend with Dev Profile

```bash
cd backend
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

#### Step 3: Run Frontend Locally

```bash
cd frontend
npm start
```

#### Access Points

- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **pgAdmin**: http://localhost:5050

**When to use:**
- Testing with PostgreSQL (production-like database)
- Verifying database-specific features
- Testing database migrations

### Full Docker Stack (For Complete Testing)

Use this approach when you need to test the complete integration or verify production-like behavior.

#### Start Everything in Docker

```bash
# Rebuild when you change code
docker compose up --build
```

#### Access Points

- **Frontend**: http://localhost
- **Backend API**: http://localhost:8080
- **pgAdmin**: http://localhost:5050

**When to use:**
- Testing complete frontend-backend integration
- Verifying production-like behavior
- CI/CD and automated testing


## Project Structure

```
product-distribution/
├── backend/              # Spring Boot application
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
├── frontend/             # Angular application
│   ├── src/
│   ├── Dockerfile
│   └── package.json
├── data/                # JSON data files
├── docker-compose.yml   # Docker services configuration
└── README.md
```

## Available Scripts

### Frontend

- `npm start` - Start development server
- `npm run build` - Build for production
- `npm run lint` - Run ESLint
- `npm run format` - Format code with Prettier

### Backend

- `mvn spring-boot:run` - Run Spring Boot application
- `mvn clean package` - Build JAR file
- `mvn test` - Run tests

## Database

The application supports two database configurations:

### Development (Local Profile - Default)
- **H2 In-Memory Database** - No setup required
- **H2 Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:product_distribution_db`
- **User**: `product_distribution`
- **Password**: `product_distribution`

### Production/Testing (Dev/Prod Profiles)
- **PostgreSQL** - Requires Docker or external PostgreSQL instance
- **Database**: `product_distribution_db`
- **User**: `product_distribution`
- **Password**: `product_distribution`
- **Port**: `5432`

When running with Docker Compose, PostgreSQL is automatically created. You can access it via pgAdmin at http://localhost:5050 or directly using any PostgreSQL client.

## Code Quality

The project uses:

- **ESLint** - For code linting (Angular TypeScript and templates)
- **Prettier** - For code formatting
- **EditorConfig** - For consistent editor settings

Run `npm run lint` in the frontend directory to check for linting issues, and `npm run format` to format code.

## Summary

**Daily Development**: Run backend and frontend locally (no Docker needed - uses H2 in-memory)  
**PostgreSQL Testing**: Use Docker for PostgreSQL + run backend and frontend locally with `dev` profile  
**Complete Testing/CI**: Use full Docker stack
