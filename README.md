# Load & Booking Management System

A backend system built with **Spring Boot** and **PostgreSQL** to manage logistics load postings and transporter bookings. It supports robust CRUD operations, business logic, and data validations to simulate a real-world booking system.

---

## Features

### Load Management
- **Create Load** (`POST /load`)
- **Fetch All Loads** (`GET /load`) — with filters like `shipperId`, `truckType`
- **Get Load by ID** (`GET /load/{id}`)
- **Update Load** (`PUT /load/{id}`)
- **Delete Load** (`DELETE /load/{id}`)

 Automatically sets:
- `status = POSTED` when a new load is created
- `datePosted` to the current timestamp

---

### Booking Management
- **Create Booking** (`POST /booking`) — only if the load is not CANCELLED
- **Fetch All Bookings** (`GET /booking`) — filter by `shipperId`, `transporterId`
- **Get Booking by ID** (`GET /booking/{id}`)
- **Update Booking** (`PUT /booking/{id}`)
- **Delete Booking** (`DELETE /booking/{id}`) — automatically sets load status to `CANCELLED`

 Automatically sets:
- `status = PENDING` by default
- `requestedAt` timestamp on creation

---

##  Technologies Used

| Tech | Description |
|------|-------------|
| Spring Boot | REST API development |
| PostgreSQL | Database |
| Java | Backend programming |
| JPA (Hibernate) | ORM for entity mapping |
| Postman | API testing |
| IntelliJ IDEA | Development IDE |

---

## Business Rules/Logic

- New loads start with `POSTED` status.
- Bookings cannot be made on `CANCELLED` loads.
- Deleting a booking cancels the associated load.

---

### Prerequisites
- Java 17+ (because of spring 6)
- PostgreSQL running
- IntelliJ or any Java IDE
- Postman for testing

### Setup
- Clone the project.
- Make sure to update application.properties file in resources folder with your PostgreSQL username and Password
- Use Postman to do the testing.
