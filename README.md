# Channa Stores - Web Application

A comprehensive web-based store management system built with Spring Boot backend and HTML/CSS/JavaScript frontend.

## 🏗️ Architecture

### Backend (Spring Boot)
- **Framework**: Spring Boot 3.3.5
- **Database**: MySQL
- **Port**: 8080

### Frontend
- **Technology**: HTML5, CSS3, JavaScript (ES6)
- **Architecture**: Multi-layout structure
- **Port**: 5500 (Live Server)

## 📁 Project Structure

```
Y02S1_Project-ChannaStoresWebAppTeam11/
├── Backend/team11/team11/
│   └── src/main/java/com/example/team11/
│       ├── Controller/          # REST API endpoints
│       ├── Service/             # Business logic
│       ├── Repository/          # Data access layer
│       ├── Entity/              # JPA entities
│       └── DTO/                 # Data transfer objects
└── Frontend/
    ├── AdminLayout/             # Admin interface
    │   ├── Adprofile.html       # Admin profile
    │   ├── Adproducts.html      # Inventory management
    │   └── userhandling03.html  # Account management
    ├── UserLayout/              # User interface
    │   ├── profile.html         # Customer profile
    │   ├── products.html        # Product catalog
    │   ├── cart.html           # Shopping cart
    │   └── orders.html         # Order history
    └── Common/                  # Shared resources
        ├── login.html          # Authentication
        ├── signup.html         # Registration
        └── styles.css          # Global styles
```

## 🚀 Getting Started

### Prerequisites
- Java 17+
- MySQL 8.0+
- Maven 3.6+
- VS Code with Live Server extension

### Backend Setup
1. **Configure Database**
   ```properties
   # application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ChannaStores
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

2. **Build & Run**
   ```bash
   cd Backend/team11/team11
   mvn clean install
   mvn spring-boot:run
   ```

### Frontend Setup
1. **Start Live Server**
   - Open VS Code
   - Navigate to `Frontend/Common/login.html`
   - Right-click → "Open with Live Server"

2. **Access Application**
   - URL: `http://127.0.0.1:5500/Common/login.html`

## 👥 User Roles & Features

### 🔧 Admin
- **Account Management**: View/manage customers, supplier contacts, co-admins
- **Inventory Management**: Full CRUD operations on products
- **Profile Management**: Update personal information

### 🛒 Customer
- **Product Browsing**: View product catalog with filtering
- **Shopping Cart**: Add/remove items, checkout
- **Order Management**: View order history
- **Profile Management**: Update personal details

### 📦 Supplier (Contact Only)
- **Contact Information**: Name, email, phone, product type
- **No Login Access**: Managed by admin as contact details only

## 🔌 API Endpoints

### Authentication
- `POST /api/users/login` - User authentication
- `POST /api/users` - User registration

### Admin Management
- `GET /api/admins` - List all admins
- `POST /api/admins/{userId}` - Create admin from user
- `PUT /api/admins/{id}` - Update admin profile
- `DELETE /api/admins/{id}` - Delete admin

### Product Management
- `GET /api/products` - List all products
- `POST /api/products` - Add new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product

### Customer Management
- `GET /api/customers` - List all customers
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer

### Supplier Contacts
- `GET /api/supplier-contacts` - List supplier contacts
- `POST /api/supplier-contacts` - Add supplier contact
- `PUT /api/supplier-contacts/{id}` - Update supplier contact
- `DELETE /api/supplier-contacts/{id}` - Delete supplier contact

## 🔐 Security Features
- Role-based access control
- CORS configuration for cross-origin requests
- Input validation
- Secure password handling