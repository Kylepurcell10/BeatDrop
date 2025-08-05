# BeatDrop

- BeatDrop is a Spring Boot-based backend for an online music tracking forum, enabling users to register, log in, and manage their profiles securely. This project is in active development, showcasing RESTful API design, JWT authentication, and database management with MySQL.


## Features

- User Registration: Create accounts via POST /api/users/addUser, validating unique email addresses with error handling (HTTP 400 for duplicates).

- User Authentication: Secure login with JWT tokens via POST /api/auth/login, using BCrypt for password hashing.

- User Management: Update user profiles (e.g., setting birthDate to null) via PATCH /api/users/update/{userId} (in progress).

- RESTful APIs: Built with Spring MVC, handling JSON payloads and standard HTTP responses.

- Database Integration: Uses Spring Data JPA with MySQL, including a UserRepository with custom queries like findByEmailAddress.

- Security: Implements Spring Security with stateless JWT authentication, permitting public access to registration and login endpoints.


## Technologies

- Java: Core programming language.

- Spring Boot: Framework for building RESTful APIs.

- Spring Security: Configures JWT-based authentication and endpoint protection.

- Spring Data JPA: Manages database operations with MySQL.

- JJWT 0.12.6: Generates and validates JSON Web Tokens.

- MySQL: Stores user data with a unique email_address constraint.

- Maven: Dependency management.

- Postman: Used for API testing.

## Project Structure

```
BeatDrop/
├── src/
│   ├── main/
│   │   ├── java/com/kpurcell/BeatDrop/api/
│   │   │   ├── config/SecurityConfig.java
│   │   │   ├── controller/AuthController.java
│   │   │   ├── controller/UserController.java
│   │   │   ├── service/AuthService.java
│   │   │   ├── service/UserService.java
│   │   │   ├── repository/UserRepository.java
│   │   │   ├── entity/User.java
│   │   │   ├── util/JwtUtil.java
│   │   │   ├── exception/DuplicateEmailException.java
│   │   ├── resources/application.properties
│   ├── test/
├── pom.xml
├── .gitignore
├── README.md
```


## Setup Instructions

- Clone the Repository:
```
git clone https://github.com/Kylepurcell10/BeatDrop.git
cd BeatDrop
```

### Prerequisites:

- Java 21

- MySQL 8.0

- Maven 3.8+

- Postman (for testing)

### Configure Database:

- Create a MySQL database named beatdrop.

- Update src/main/resources/application.properties with your credentials:

```
spring.datasource.url=jdbc:mysql://localhost:3306/beatdrop
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your_secure_jwt_secret
```

## Build and Run:

```
mvn clean install
mvn spring-boot:run
```

## Test APIs:

Register a User:

```
POST http://localhost:8080/api/users/addUser
Content-Type: application/json
{
    "firstName": "Jane",
    "lastName": "Doe",
    "emailAddress": "jane.doe@example.com",
    "password": "securePassword123",
    "birthDate": "1990-01-01"
}
```

Login:

```
POST http://localhost:8080/api/auth/login
Content-Type: application/json
{
    "emailAddress": "jane.doe@example.com",
    "password": "securePassword123"
}
```

## Project Status

In Development: Core features (registration, login) are implemented, but far from finalized. User profile updates (PATCH /api/users/update/{userId}) and JWT-based endpoint protection are in progress.


## Future Plans:

Add JwtAuthenticationFilter for securing endpoints like GET /api/users/me.

Implement additional CRUD operations for music tracking features.

Add unit and integration tests.


## Contact

GitHub: Kylepurcell10

LinkedIn: (https://www.linkedin.com/in/kyle-purcell/)

Email: kylepurcell10@yahoo.com

This project is distinct from other “BeatDrop” projects (e.g., Beat Saber’s content manager or music visualizers). It is a custom Spring Boot backend for a music tracking forum.

Sensitive data (e.g., jwt.secret, database credentials) is excluded via .gitignore.
