# Spring Boot JPA Relationship & N+1 Query Demo

## 📌 Description
A Spring Boot project demonstrating JPA entity relationships, Hibernate persistence context, cascading operations, and the N+1 query problem using PostgreSQL.

## 🚀 Technologies Used
- Java
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- REST API
- Postman

## ✨ Features
- Entity relationship mapping
- One-to-One and Many-to-One relationships
- JPA persistence context
- Cascading operations
- CRUD operations using REST APIs
- Understanding and resolving the N+1 query problem
- SQL query logging for debugging

## ⚙️ Configuration

1. Clone the repository.
2. Create a PostgreSQL database.
3. Update `src/main/resources/application.properties` with your database configuration.
4. Make sure Java and Maven are installed.

Example configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Replace the database name and credentials with your own values.

## ▶️ Run the Project

Using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

The application runs at:

`http://localhost:8080`

## 🧪 Testing

Use Postman to test the REST APIs for creating, retrieving, updating, and deleting records.

Enable SQL logging to observe Hibernate queries and compare query behavior with and without `JOIN FETCH`.

## 🎯 Learning Objectives
- Understand JPA entity relationships.
- Learn how the persistence context manages entities.
- Explore cascade types and entity lifecycle operations.
- Identify the N+1 query problem.
- Improve query efficiency using `JOIN FETCH`.

## 👨‍💻 Author
Rahul Singh

GitHub: [122rahul2054](https://github.com/122rahul2054)
