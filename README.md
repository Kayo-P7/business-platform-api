# Business Platform API

A RESTful e-commerce backend developed with **Java** and **Spring Boot** to practice backend architecture, object-oriented programming, relational database modeling, and business rules.

The project is being developed incrementally, following real-world backend development practices.

---

## Features

### Product Management

* Create products
* Update products
* Delete products
* Delete all products
* Find product by ID
* Find product by name
* Search products by price range
* Search products with low stock
* Pagination and sorting
* Bean Validation
* Swagger documentation

### Customer Management

* Customer entity
* Email validation
* Phone number validation
* Active status
* Relationship with orders

### Order Management

* Create orders
* Associate orders with customers
* Associate multiple products with a single order
* Automatic order total calculation
* Many-to-many relationship using `OrderItem`

### Exception Handling

* Global exception handler
* Custom exceptions
* HTTP status code mapping

---

## Technologies

* Java 26
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Docker
* Swagger / OpenAPI

---

## Architecture

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
```

The project follows a layered architecture to separate responsibilities between API, business logic, and data access.

---

## Domain Model

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

The `OrderItem` entity solves the many-to-many relationship between `Order` and `Product`, storing:

* Product
* Quantity
* Purchase price

This preserves historical information even if the product price changes later.

---

## Business Rules

* A customer can place multiple orders.
* An order belongs to exactly one customer.
* An order can contain multiple products.
* A product can belong to multiple orders.
* The order total is calculated automatically from its items.
* Product prices are copied to the order at the time of purchase.

---

## API Documentation

Swagger UI is available after starting the application:

```text
http://localhost:8084/swagger-ui/index.html
```

---

## Project Structure

```text
src/main/java
├── controller
├── dto
├── enums
├── exception
├── model
│   └── pk
├── repository
└── service
```

---

## Running the Project

Clone the repository:

```bash
git clone https://github.com/your-username/business-platform-api.git
```

Configure your database credentials using environment variables:

```text
DB_URL=
DB_USERNAME=
DB_PASSWORD=
```

Run the application:

```bash
./mvnw spring-boot:run
```

---

## Roadmap

* [x] Product CRUD
* [x] Customer entity
* [x] Order entity
* [x] OrderItem entity
* [x] Pagination
* [x] Swagger/OpenAPI
* [x] Exception handling
* [ ] Stock validation
* [ ] Customer active validation
* [ ] Spring Security + JWT
* [ ] Unit tests (JUnit + Mockito)
* [ ] Flyway migrations
* [ ] Telegram notification integration

---

## Author

Developed as a backend learning project focused on Java, Spring Boot, REST APIs, relational databases, and software architecture.
