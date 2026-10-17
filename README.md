# Digital Wallet & Payment Management System

A full-stack digital wallet application built using Spring Boot, Spring Security, JWT authentication, React, and MySQL.

Users can register, log in, manage virtual wallet balances, add demo funds, transfer funds between users, and view transaction history. Administrators can manage users, wallets, and transactions.

## Live Demo

- **Frontend:** https://digital-wallet-frontend-phi.vercel.app/
- **Backend API:** https://digital-wallet-backend-xz1g.onrender.com
- **Swagger API Documentation:** https://digital-wallet-backend-xz1g.onrender.com/swagger-ui/index.html

## Features

- User registration and login
- JWT-based authentication
- Role-based access control for users and administrators
- Wallet balance management
- Add virtual money to a wallet
- Transfer funds between registered users
- Transaction history
- Insufficient-balance validation
- RESTful APIs
- MySQL database integration
- Responsive frontend interface
- Admin dashboard for user, wallet, and transaction management

## Technology Stack

### Backend
- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA and Hibernate
- Maven
- MySQL

### Frontend
- React
- Vite
- JavaScript
- Axios
- CSS

### Deployment
- Frontend: Vercel
- Backend: Render
- Database: Aiven MySQL
- Version control: Git and GitHub

## Architecture

1. The React frontend sends HTTP requests to the Spring Boot backend.
2. The backend authenticates users using JWT.
3. Controllers handle API requests, and service classes implement business logic.
4. Spring Data JPA communicates with the MySQL database.
5. Role-based authorization restricts administrative operations.

## Run Locally

### Prerequisites

- Java 21
- Maven
- MySQL 8
- Node.js and npm

### 1. Start the backend

Clone the backend repository:

```bash
git clone https://github.com/Nikhil-Arsham/digital-wallet-backend.git
cd digital-wallet-backend
```

Create a MySQL database named `digital_wallet`.

Configure the following environment variables:

```bash
export DB_URL="jdbc:mysql://localhost:3306/digital_wallet"
export DB_USERNAME="your_mysql_username"
export DB_PASSWORD="your_mysql_password"
export JWT_SECRET="your_base64_encoded_secret"
export JWT_EXPIRATION="86400000"
```

Use a strong, randomly generated Base64-encoded JWT secret. Never commit credentials or secrets to GitHub.

Start the application:

```bash
mvn spring-boot:run
```

Backend URL: `http://localhost:8080`

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### 2. Start the frontend

Open another terminal:

```bash
git clone https://github.com/Nikhil-Arsham/digital-wallet-frontend.git
cd digital-wallet-frontend
npm install
```

Create a `.env` file in the frontend directory:

```env
VITE_API_URL=http://localhost:8080
```

Start the development server:

```bash
npm run dev
```

Frontend URL: `http://localhost:5173`

## Application Screenshots

See the [Frontend Repository](https://github.com/Nikhil-Arsham/digital-wallet-frontend)
for screenshots of the user dashboard, admin dashboard, transaction history,
and Swagger API documentation.

## API Overview

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/register` | Register a user |
| POST | `/api/auth/login` | Authenticate a user |
| GET | `/api/users/me` | Retrieve the authenticated user's details |
| GET | `/api/wallet` | Retrieve wallet information |
| POST | `/api/wallet/add-money` | Add virtual funds |
| POST | `/api/wallet/transfer` | Transfer funds |
| GET | `/api/transactions` | Retrieve transaction history |
| GET | `/api/admin/users` | Admin: manage users |
| GET | `/api/admin/wallets` | Admin: view wallets |
| GET | `/api/admin/transactions` | Admin: view transactions |

Refer to Swagger UI for available request parameters, response formats, and additional API details.

## Security

- Password hashing using BCrypt
- JWT-based authentication
- Role-based authorization
- Configurable CORS
- Environment-based configuration for sensitive credentials
- Validation to prevent transfers exceeding the available balance

## Testing

The application has been manually tested for registration, login, adding virtual funds, transfers, transaction history, insufficient-balance rejection, and administrative functionality.

## Disclaimer

This project is intended for learning and demonstration purposes. Wallet balances represent virtual funds; the application does not process real payments or connect to banking systems.

## Author

**Nikhil Arsham**

GitHub: https://github.com/Nikhil-Arsham
