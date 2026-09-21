# Campus Resource Management System

A backend application developed using Java, Spring Boot, MySQL, Spring Data JPA, Hibernate, and Maven.

## Project Overview

The Campus Resource Management System manages campus resources such as books and learning materials. Students can borrow and return resources while the system tracks availability, loan records, and overdue items.

## Features

- Student CRUD operations
- Resource CRUD operations
- Issue resources to students
- Return borrowed resources
- Track total and available resource copies
- View all loan records
- Filter loans by status, student, and resource
- Update overdue loans
- Transactional operations using Spring Data JPA

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST API
- Git and GitHub

## API Endpoints

### Students

- POST `/api/students`
- GET `/api/students`
- GET `/api/students/{id}`
- PUT `/api/students/{id}`
- DELETE `/api/students/{id}`

### Resources

- POST `/api/resources`
- GET `/api/resources`
- GET `/api/resources/{id}`
- PUT `/api/resources/{id}`
- DELETE `/api/resources/{id}`

### Loans

- POST `/api/loans/issue`
- GET `/api/loans`
- GET `/api/loans/{id}`
- PUT `/api/loans/{id}/return`
- GET `/api/loans/status/{status}`
- GET `/api/loans/student/{studentId}`
- GET `/api/loans/resource/{resourceId}`
- PUT `/api/loans/update-overdue`

## Running the Application

1. Create a MySQL database named `campus_resource_db`.
2. Configure your MySQL credentials in `application.properties`.
3. Run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

The application runs at:

`http://localhost:8080`

## Build the Project

```powershell
.\mvnw.cmd clean compile
```

## Example Issue Request

`POST /api/loans/issue?studentId=1&resourceId=1&dueDate=2026-10-01`

## Example Return Request

`PUT /api/loans/1/return`

## Project Purpose

This project demonstrates REST APIs, CRUD operations, relational database management, entity relationships, transactions, and business logic using Spring Boot and JPA.
