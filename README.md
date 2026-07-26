# 🌸 BeautyKart Backend System

## Overview

BeautyKart is a cosmetics-based e-commerce backend application developed using a Microservices Architecture. The system allows customers to browse beauty products, manage shopping carts, place orders, and track order status.

This project was built using Spring Boot, Spring Data JPA, Hibernate, MySQL, OpenFeign, and REST APIs.

---

## 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- OpenFeign
- Maven
- Lombok
- Postman
- REST APIs

---

## 🏗️ Microservices

### Category Service

Manages product categories.

#### Features

- Add Category
- View Categories
- Get Category By Id
- Update Category
- Delete Category
- Search Category By Name

#### APIs

```http
POST /api/categories
GET /api/categories
GET /api/categories/{id}
PUT /api/categories/{id}
DELETE /api/categories/{id}
GET /api/categories/by-name/{name}
```

---

### Brand Service

Manages cosmetic brands.

#### Features

- Add Brand
- View Brands
- Get Brand By Id
- Update Brand
- Delete Brand
- Search Brand By Name

#### APIs

```http
POST /api/brands
GET /api/brands
GET /api/brands/{id}
PUT /api/brands/{id}
DELETE /api/brands/{id}
GET /api/brands/by-name/{name}
```

---

### Product Service

Manages beauty and cosmetic products.

#### Features

- Add Product
- View Products
- Get Product By Id
- Update Product
- Delete Product
- Search Product By Name
- Filter Products By Category
- Filter Products By Brand
- OpenFeign Integration with Category Service and Brand Service

#### APIs

```http
POST /api/products
GET /api/products
GET /api/products/{id}
PUT /api/products/{id}
DELETE /api/products/{id}
GET /api/products/category/{categoryId}
GET /api/products/brand/{brandId}
GET /api/products/search?name={productName}
```

---

### Cart Service

Manages customer shopping carts.

#### Features

- Add Product To Cart
- Get Cart By User Id
- Update Cart Quantity
- Delete Cart Item
- Clear User Cart
- OpenFeign Integration with Product Service

#### APIs

```http
POST /api/cart
GET /api/cart/user/{userId}
PUT /api/cart/{cartItemId}
DELETE /api/cart/{cartItemId}
DELETE /api/cart/user/{userId}
```

---

### Order Service

Manages customer orders.

#### Features

- Place Order
- View Order By Id
- View Orders By User Id
- Update Order Status
- Clear Cart After Successful Order Placement
- OpenFeign Integration with Cart Service

#### APIs

```http
POST /api/orders/user/{userId}
GET /api/orders/{orderId}
GET /api/orders/user/{userId}
PUT /api/orders/{orderId}/status
```

---

## 📦 Order Status Flow

```text
PLACED
   ↓
PACKED
   ↓
SHIPPED
   ↓
DELIVERED
```

Or

```text
PLACED
   ↓
CANCELLED
```

---

## 🔄 Microservice Communication

### Product Service

Communicates with:

- Category Service
- Brand Service

### Cart Service

Communicates with:

- Product Service

### Order Service

Communicates with:

- Cart Service

---

## 🗄️ Databases

Each microservice uses its own database.

```text
categorydb
branddb
productdb
cartdb
orderdb
```

---

## 📂 Project Structure

```text
BeautyKart
│
├── CategoryService
├── BrandService
├── ProductService
├── CartService
├── OrderService
│
├── README.md
└── .gitignore
```

---

## ✅ Implemented Features

- Microservices Architecture
- CRUD Operations
- OpenFeign Communication
- DTO-Based Responses
- Mapper Layer
- Validation using Jakarta Validation
- Global Exception Handling
- Custom Error Responses
- Product Search Functionality
- Cart Management
- Order Management
- MySQL Integration
- Hibernate/JPA Mapping

---

## ⚙️ Running the Project

### 1. Clone Repository

```bash
git clone https://github.com/Mitali-Shandilya/BeautyKart.git
```

### 2. Update Database Credentials

Configure the following in each service:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run Services

Start the services in the following order:

1. Category Service
2. Brand Service
3. Product Service
4. Cart Service
5. Order Service

---

## 📌 Sample Products

- Lipstick
- Foundation
- Kajal
- Sunscreen
- Face Wash
- Shampoo
- Moisturizer
- Perfume

---

## 🎯 Learning Outcomes

This project demonstrates:

- Spring Boot Development
- REST API Design
- Hibernate & JPA
- DTO and Mapper Pattern
- Microservices Architecture
- OpenFeign Communication
- Exception Handling
- Validation
- MySQL Database Integration

---

## 👩‍💻 Author

**Mitali Shandilya**

Capstone Project: **BeautyKart Backend System**

Built using **Spring Boot Microservices Architecture** 🚀✨