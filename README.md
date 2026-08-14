````markdown
# Business Platform API

RESTful e-commerce backend developed with **Java** and **Spring Boot**, focused on backend architecture, business rules, relational database modeling, API development, and automated testing.

The project is being developed incrementally as a backend learning and portfolio project.

---

## 🚀 Implemented Features

### Product Management

- Create products
- Update products
- Delete products
- Delete all products
- Find product by ID
- Find product by name
- Search products by price range
- Search products with low stock
- Search products by active status
- Stock management
- Pagination and sorting
- Bean Validation
- Swagger/OpenAPI documentation

### Customer Management

- Create customers
- Update customers
- Delete customers
- Find customer by ID
- Find customer by name
- Pagination and sorting
- Email validation
- Phone number validation
- Customer active status
- Relationship between customers and orders

### Order Management

- Create orders
- Find orders by ID
- List orders with pagination
- Update order status
- Associate orders with customers
- Associate multiple products with a single order
- `OrderItem` entity for the relationship between orders and products
- Automatic order total calculation
- Stock validation
- Customer active status validation
- Product active status validation
- Insufficient stock validation

### Exception Handling

- Global exception handler
- Custom business exceptions
- Resource not found handling
- Inactive customer validation
- Inactive product validation
- Insufficient stock handling
- HTTP status code mapping

### Testing

- Unit tests with JUnit 5
- Mockito
- Mocked repositories
- Business rule testing
- Exception scenario testing
- Service layer testing

Current tests cover important `OrderService` scenarios such as:

- Order found
- Customer not found
- Inactive customer
- Product not found
- Inactive product
- Insufficient stock

---

## 🛠️ Technologies

- Java 26
- Spring Boot 4
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Lombok
- Docker
- JUnit 5
- Mockito
- Swagger / OpenAPI

---

## 🏗️ Architecture

The project follows a layered architecture:

```text
Controller
     │
     ▼
Service
     │
     ▼
Repository
     │
     ▼
PostgreSQL
````

### Main Layers

* **Controller** — Handles HTTP requests and responses.
* **Service** — Contains business rules and application logic.
* **Repository** — Handles database access using Spring Data JPA.
* **DTO** — Separates API request/response models from entities.
* **Model** — Represents the domain entities.
* **Exception** — Contains custom exceptions and global exception handling.

---

## 📦 Domain Model

```text
Customer
   │
   │ 1:N
   ▼
Order
   │
   │ 1:N
   ▼
OrderItem
   ▲
   │ N:1
Product
```

`OrderItem` represents the relationship between `Order` and `Product`.

It stores:

* Product
* Order
* Quantity
* Purchase price

This allows a single order to contain multiple products while preserving the price recorded at the time of purchase.

---

## 📚 API Documentation

The API is documented using Swagger/OpenAPI.

After starting the application, Swagger UI is available at:

```text
http://localhost:8084/swagger-ui/index.html
```

Swagger provides interactive documentation for the available REST endpoints.

---

## 🧪 Testing

Run all tests using the Maven Wrapper:

```bash
./mvnw test
```

The project uses **JUnit 5 and Mockito** for unit testing.

The tests focus mainly on validating business rules and service behavior without directly depending on the real database.

Example:

```text
OrderService
     │
     ├── CustomerRepository → Mock
     ├── ProductRepository  → Mock
     └── OrderRepository    → Mock
```

---

## ▶️ Running the Project

### Clone the repository

```bash
git clone https://github.com/your-username/business-platform-api.git
cd business-platform-api
```

### Database

The application uses **PostgreSQL**.

Configure the database connection according to the application's configuration and environment variables.

Example:

```text
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

### Run the application

Using the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

### Run the tests

```bash
./mvnw test
```

---

# 🔮 Next Steps

The project will continue evolving with additional backend concepts and infrastructure.

## 🔐 Security

* [ ] Spring Security
* [ ] JWT authentication
* [ ] Authorization and roles

## 🗄️ Database

* [ ] Flyway migrations
* [ ] Improve database constraints
* [ ] Improve transaction management

## 🧪 Testing

* [ ] Expand unit test coverage
* [ ] Integration tests
* [ ] Repository tests
* [ ] Controller tests

## 🏗️ Architecture

* [ ] Refactor services with many responsibilities
* [ ] Improve domain organization
* [ ] Improve DTO and validation structure
* [ ] Improve API error responses

## ⚙️ Infrastructure

* [ ] CI/CD
* [ ] Improve Docker configuration
* [ ] Application deployment
* [ ] Cloud infrastructure

## 📡 Messaging and Integrations

* [ ] Telegram notification integration
* [ ] RabbitMQ or Kafka
* [ ] Redis

## ☁️ Future Architecture

* [ ] AWS
* [ ] Microservices
* [ ] Kubernetes

---

## 🎯 Project Goal

The goal of this project is to progressively build a complete backend application while applying real-world development practices.

The project is being used to study and practice:

* Java
* Spring Boot
* REST APIs
* JPA/Hibernate
* PostgreSQL
* Docker
* Business rules
* DTO pattern
* Exception handling
* Automated testing
* API documentation
* Git and GitHub
* Software architecture

The project will continue evolving as new backend concepts are learned and implemented.

---

## 👨‍💻 Author

Developed as a backend learning and portfolio project focused on **Java, Spring Boot, REST APIs, PostgreSQL, JPA/Hibernate, automated testing, and backend architecture**.

```
```
