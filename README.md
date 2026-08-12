# 🌸 BeautyKart - Cosmetics E-Commerce Platform

## Overview

BeautyKart is a full-stack cosmetics e-commerce application built using a Microservices Architecture. The platform enables customers to browse beauty products, manage shopping carts, place orders, and track order status, while administrators can manage brands, categories, products, inventory, and customer orders.

The backend is developed using Spring Boot Microservices, Spring Security, JWT Authentication, OpenFeign, Hibernate/JPA, and MySQL. The frontend is built using React.

---

## 🚀 Technologies Used

### Backend

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- OpenFeign
- Lombok
- Maven
- REST APIs
- MySQL

### Frontend

- React
- Axios
- CSS

### Tools

- Postman
- Git
- GitHub
- IntelliJ IDEA
- VS Code

---

# 🔐 Security Features

- JWT Authentication
- Role-Based Access Control (RBAC)
- Stateless Authentication
- Spring Security Integration
- Protected Admin APIs
- Protected User APIs
- Role-Based Navigation and Access Management

---

# 🏗️ Microservices

## Category Service

Manages product categories.

### Features

- Add Category
- View Categories
- Get Category By Id
- Update Category
- Soft Delete Category
- Activate Category
- Search Category By Name

### APIs

```http
POST   /api/categories
GET    /api/categories
GET    /api/categories/{id}
PUT    /api/categories/{id}
DELETE /api/categories/{id}
GET    /api/categories/by-name/{name}
PUT    /api/categories/admin/{id}/activate Our stage, are font करना है, कोई ऑडिट ऑल है, मैंने सोचा अरे पूरा पैर हुआ है कि हसर होती है, वो भेजसकते