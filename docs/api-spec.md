# Chamaa API Specification

## Base URL
```
http://localhost:8080/api
```

## Authentication
(To be implemented)

## Endpoints

### Users
- `POST /users` - Create a new user
- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `GET /users/email/{email}` - Get user by email
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user

### Groups
- `POST /groups` - Create a new group
- `GET /groups` - Get all groups
- `GET /groups/{id}` - Get group by ID
- `GET /groups/creator/{creatorId}` - Get groups by creator
- `GET /groups/active` - Get active groups
- `PUT /groups/{id}` - Update group
- `DELETE /groups/{id}` - Delete group

### Wallets
- `POST /wallets` - Create a new wallet
- `GET /wallets/{id}` - Get wallet by ID
- `GET /wallets/user/{userId}` - Get wallet by user ID
- `PUT /wallets/{id}/balance` - Update wallet balance
- `DELETE /wallets/{id}` - Delete wallet

### Loans
- `POST /loans` - Create a new loan
- `GET /loans/{id}` - Get loan by ID
- `GET /loans/borrower/{borrowerId}` - Get loans by borrower
- `GET /loans/group/{groupId}` - Get loans by group
- `GET /loans/status/{status}` - Get loans by status
- `PUT /loans/{id}/approve` - Approve a loan
- `PUT /loans/{id}/reject` - Reject a loan
- `DELETE /loans/{id}` - Delete loan

## Error Handling
All errors follow this format:
```json
{
  "error_code": "ERROR_CODE",
  "message": "Error message",
  "status_code": 400
}
```
