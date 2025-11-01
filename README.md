# ScholarshipPortal2

🎓 Scholarship Portal — Spring Boot + PostgreSQL + Thymeleaf

A simple and functional Scholarship Management System where students can explore and apply for scholarships, and administrators can review and manage applications.

✨ Overview

The Scholarship Portal is a web-based application developed using Spring Boot, Thymeleaf, and PostgreSQL.
It provides a unified platform for both students and administrators to handle scholarship applications seamlessly.

🧩 Features
👩‍🎓 For Students

Browse all available scholarships

Apply for scholarships using a simple form

Track application status using email

🧑‍💼 For Administrators

View all student applications

Approve or reject applications

⚙️ Additional Highlights

✅ Centralized Global Exception Handling

🧾 Integrated SLF4J Logging across all layers (Controller, Service, Repository)

🗄️ Clean PostgreSQL database structure

🌐 Responsive Thymeleaf pages

🛠️ Tech Stack
Layer	Technology
Frontend	HTML5, CSS3, Thymeleaf
Backend	Spring Boot (MVC + JDBC)
Database:	PostgreSQL
Build Tool:	Maven
Logging	SLF4J + Logback
Server	Embedded Tomcat

**Database Setup (PostgreSQL)

Open pgAdmin or your SQL command line

Create a new database:

CREATE DATABASE scholarship_db;


Run the SQL script provided in the project root:

\i scholarship_db

Configure Application Properties

Edit src/main/resources/application.properties:
server.port=8080

# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/scholarship_db
spring.datasource.username=your username
spring.datasource.password=your password
spring.datasource.driver-class-name=org.postgresql.Driver

