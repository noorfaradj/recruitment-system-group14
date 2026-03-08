# Recruitment Application – Group 14

## Project Overview

This project is a web-based recruitment system developed in the IV1201 course.

The system allows applicants to submit job applications and recruiters to review them.  
Applicants can provide competence profiles and availability periods when applying.

The goal of the project is to replace an older recruitment system with a new maintainable system built with modern web technologies.

The system supports two user roles:

Applicant  
Can log in and submit job applications.

Recruiter  
Can log in and view submitted applications.

---

## Technologies

The project is built using the following technologies:

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- Maven
- Flyway
- PostgreSQL
- Supabase (database hosting)
- Railway (cloud deployment)

---

## Project Structure

The application follows a layered architecture.

controller  
Handles HTTP requests from the browser.

service  
Contains business logic and transaction handling.

repository  
Handles communication with the database using Spring Data JPA.

domain  
Contains entity classes representing database tables.

dto  
Data Transfer Objects used to transfer data between layers.

exception  
Handles application errors and global exception handling.

config  
Contains security configuration and other application configuration.

---

## Database and Data Migration

The system uses PostgreSQL as the database.

The database is hosted using Supabase.

Database schema changes and migration are handled using Flyway.  
Migration scripts are located in:

src/main/resources/db/migration

The migration process first imports the existing database so that all old data is preserved.  
After that, new tables required by the new system are created.

This ensures that all data from the existing database exists in the new database.

Some records in the existing database contain missing values, such as missing username or password.  
These cases are handled during migration so that no records are lost.

---

## Running the Project Locally

### Requirements

- Java 17
- Maven
- Access to a PostgreSQL database

### Start the application

Run the following command:

mvn spring-boot:run

or

./mvnw spring-boot:run

Flyway migrations will run automatically when the application starts.

---

## Deployment

The application is deployed in the cloud.

Application platform: Railway  
Database: Supabase PostgreSQL

To deploy a new version:

1. Push the latest code to the public Git repository
2. Connect the repository to Railway
3. Configure environment variables
4. Connect the application to the Supabase database
5. Deploy the application

Flyway will automatically run migrations when the application starts.

---

## Handover

The source code is available in a public Git repository so that other developers can continue development and deploy new versions of the system.

The repository includes:

- source code
- database migration scripts
- configuration files
- documentation

This documentation explains how the system works, how to run the project, and how to deploy new versions.

---

## Repository

The source code is available in a public Git repository.

GitHub: https://github.com/noorfaradj/recruitment-system-group14.git

## Authors

Group 14  
IV1201 – Design of Global Applications