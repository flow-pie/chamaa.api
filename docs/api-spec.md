# API Specification

## Base URL
```
http://localhost:8080/api
```

## Authentication

Firebase Authentication is used for secure API access. Include the Firebase ID token in the Authorization header:

```bash
Authorization: Bearer <firebase_id_token>
```

## Endpoints

### Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create a new user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/email/{email}` | Get user by email |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |

**Request Body (POST /users):**
```json
{
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+254712345678"
}
```

### Groups

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/groups` | Create a new group |
| GET | `/api/groups` | Get all groups |
| GET | `/api/groups/{id}` | Get group by ID |
| GET | `/api/groups/creator/{creatorId}` | Get groups by creator |
| GET | `/api/groups/active` | Get active groups |
| PUT | `/api/groups/{id}` | Update group |
| DELETE | `/api/groups/{id}` | Delete group |

**Request Body (POST /groups):**
```json
{
  "name": "Community Savings",
  "description": "Monthly savings group",
  "creatorId": 1,
  "targetAmount": 10000.0
}
```

### Wallets

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/wallets` | Create a new wallet |
| GET | `/api/wallets/{id}` | Get wallet by ID |
| GET | `/api/wallets/user/{userId}` | Get wallet by user ID |
| PUT | `/api/wallets/{id}/balance` | Update wallet balance |
| DELETE | `/api/wallets/{id}` | Delete wallet |

### Loans

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/loans` | Create a new loan |
| GET | `/api/loans/{id}` | Get loan by ID |
| GET | `/api/loans/borrower/{borrowerId}` | Get loans by borrower |
| GET | `/api/loans/group/{groupId}` | Get loans by group |
| GET | `/api/loans/status/{status}` | Get loans by status |
| PUT | `/api/loans/{id}/approve` | Approve a loan |
| PUT | `/api/loans/{id}/reject` | Reject a loan |
| DELETE | `/api/loans/{id}` | Delete loan |

**Request Body (POST /loans):**
```json
{
  "borrowerId": 1,
  "groupId": 1,
  "amount": 5000.0,
  "durationInMonths": 12,
  "purpose": "Business expansion"
}
```

## Response Formats

### Success Response
```json
{
  "id": 1,
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "createdAt": "2024-01-01T10:00:00",
  "updatedAt": "2024-01-01T10:00:00"
}
```

### Error Response
```json
{
  "error": "VALIDATION_ERROR",
  "message": "Invalid request",
  "status": 400,
  "timestamp": "2024-01-01T10:00:00"
}
```

### Base Response Wrapper (Android)
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... },
  "error": null
}
```

## Status Codes

| Code | Description |
|------|-------------|
| 200 | OK - Success |
| 201 | Created - Resource created |
| 400 | Bad Request - Invalid input |
| 401 | Unauthorized - Invalid/missing auth |
| 404 | Not Found - Resource doesn't exist |
| 500 | Internal Server Error |
