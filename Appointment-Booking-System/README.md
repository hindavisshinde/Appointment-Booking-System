# Appointment Booking & Management System

A Java web application for creating appointments and preventing duplicate bookings for the same slot.

## Technology stack
- Java 17, Jakarta Servlets 6, JSP
- JDBC and MySQL 8
- HTML5, CSS3, JavaScript
- Maven and Apache Tomcat 10.1+

## Requirements
1. JDK 17 or later
2. MySQL Server 8+
3. Apache Tomcat 10.1+
4. Maven 3.8+
5. An IDE such as Eclipse or IntelliJ IDEA (recommended)

## Setup
1. Create the database by running `database/schema.sql` in MySQL Workbench.
2. Open `src/main/java/com/hindavi/util/DBUtil.java`.
3. Set `DB_URL`, `DB_USER`, and `DB_PASSWORD` as environment variables, or update the local defaults in DBUtil for your own machine. Never commit real passwords.
4. From this project folder, run `mvn clean package`.
5. Copy `target/appointment-booking-system.war` to Tomcat's `webapps` folder and start Tomcat.
6. Open `http://localhost:8080/appointment-booking-system/`.

## Important
- This is a learning starter project, not a production-ready application.
- Review, run, test and understand the code before describing it as your own completed work.
- Add validation, authorization, CSRF protection, secure password handling and deployment configuration before production use.
- Do not commit passwords or private configuration.
