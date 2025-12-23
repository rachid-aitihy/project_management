# Project Management Dashboard

A comprehensive full-stack application for efficient project and task management. Built with Spring Boot, Angular, and MySQL, it provides secure authentication, real-time progress tracking, and an intuitive user interface for managing projects and tasks.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [System Requirements](#system-requirements)
- [Installation](#installation)


---

## Features

### Authentication & Security
- **Secure User Registration & Login**: Create accounts with secure credential management
- **JWT Authentication**: Stateless token-based authentication for API endpoints
- **Password Encryption**: BCrypt hashing ensures database security
- **Route Guards**: Angular route protection with automatic token validation
- **Token Interceptors**: Seamless token injection in all HTTP requests

### Project Management
- **Project Creation**: Intuitive modal interface for creating new projects
- **Dynamic Dashboard**: Comprehensive view of all projects with real-time progress indicators
- **Progress Tracking**: Automatic calculation of project completion percentage based on task status
- **Project Statistics**: Instant access to task counts and completion metrics

### Task Management
- **Task Creation**: Add tasks with descriptions and due dates
- **Real-Time Updates**: Marking tasks as complete instantly updates project progress
- **Task Management**: Edit, delete, or toggle completion status of tasks
- **Task Filtering**: View tasks by project with comprehensive details

---

## Technology Stack

### Backend
| Technology | Version | Purpose |
|-----------|---------|---------|
| Java | 17+ | Core programming language |
| Spring Boot | 3.0 | REST API framework |
| Spring Security | 6 | Authentication & authorization |
| JWT (J.J.W.T) | Latest | Token-based authentication |
| Spring Data JPA | 3.0 | Data persistence layer |
| Hibernate | Latest | ORM framework |
| MySQL | 8.0+ | Relational database |
| Maven | Latest | Build & dependency management |

### Frontend
| Technology | Version | Purpose |
|-----------|---------|---------|
| Angular | 19 | Frontend framework |
| TypeScript | 5+ | Programming language |
| Bootstrap | 5 | UI component library |
| RxJS | 7+ | Reactive programming |
| Standalone Components | - | Modern Angular architecture |

---

## System Requirements

### Prerequisites
- **Java**: JDK 17 or higher
- **Node.js**: v18.0.0 or higher
- **npm**: v9.0.0 or higher
- **MySQL**: 8.0 or higher
- **Git**: Latest version
- **Maven**: 3.8.0 or higher (or use included Maven wrapper)

### Recommended
- IDE: IntelliJ IDEA, Eclipse, or VS Code
- MySQL Client: MySQL Workbench or CLI
- API Testing: Postman or Insomnia

---

## Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/rachid-aitihy/project_management.git
cd project-management-dashboard
```

### Step 2: Create the Database

Open MySQL Workbench or your terminal and execute:

```sql
CREATE DATABASE project-management-db;
```


### Step 3: Configure Backend

Navigate to the backend directory:

```bash
cd backend
```

Update the database configuration in `src/main/resources/application.properties`:

```properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/project_management_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT Configuration
app.jwt.secret=your_secret_key_here_min_32_characters_recommended
app.jwt.expiration=86400000

# Server Configuration
server.port=8080
server.servlet.context-path=/api
```

### Step 4: Install and Run Backend

```bash
# Build the project
./mvnw clean build

# Run the Spring Boot application
./mvnw spring-boot:run
```

The backend will be available at: `http://localhost:8080`

### Step 5: Configure and Run Frontend

In a new terminal, navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```


Start the Angular development server:

```bash
ng serve
```

The frontend will be available at: `http://localhost:4200`


For questions, suggestions, or issues, please reach out:

- **Email**: rachidaitihy04@gmail.com
- **LinkedIn**: https://www.linkedin.com/in/rachid-aitihy/
- **GitHub**: https://github.com/rachid-aitihy/

