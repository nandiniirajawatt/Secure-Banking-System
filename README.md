# 🏛️ Enterprise Core-Banking Simulation & Data Audit Engine

A full-stack banking administration system built with **Java, Spring Boot, JPA/Hibernate, H2, HTML5, CSS3, and Vanilla JavaScript**.

The application simulates core banking-office workflows such as customer onboarding, account record management, asset-based ledger sorting, account searching, and record deletion through a structured multi-layer architecture.

## 🚀 Key Features

* 👤 **Customer Onboarding** — Create customer/account records with server-side validation.
* 💰 **Account Validation** — Prevents account creation when the initial balance is below the minimum required amount of **₹500**.
* 📊 **Ledger Dashboard** — Displays customer account records in a centralized administration interface.
* 🔍 **Fast Account Search** — Uses a custom **Binary Search** implementation on sorted account data.
* 📈 **Asset-Based Sorting** — Uses a custom **Insertion Sort** implementation to rank customers by account balance.
* 🗑️ **Record Deletion** — Supports server-side account deletion through HTTP `DELETE` requests.
* 🇮🇳 **Indian Currency Formatting** — Displays monetary values using the `en-IN` locale and ₹ formatting.
* 🔄 **Asynchronous Communication** — Frontend communicates with the Spring Boot backend using the browser's native **Fetch API**.
* 🗄️ **Relational Persistence** — Customer/account data is stored using H2 and managed through Spring Data JPA and Hibernate.

## 🛠️ Tech Stack

### Frontend

* HTML5
* CSS3
* Vanilla JavaScript
* Fetch API

### Backend

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate

### Database

* H2 Database
* SQL
* Relational data modelling

## 🏗️ Architecture

```
┌─────────────────────────────┐
│        Frontend Layer       │
│   HTML5 + CSS3 + JavaScript │
└──────────────┬──────────────┘
               │ Fetch API
               ▼
┌─────────────────────────────┐
│       Spring Boot API       │
│ Controllers → Services      │
│ → Business Logic            │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│    Persistence Layer        │
│ Spring Data JPA + Hibernate │
└──────────────┬──────────────┘
               │
               ▼
┌─────────────────────────────┐
│        H2 Database          │
└─────────────────────────────┘
```

## 🧠 Custom Algorithms

### 1. Insertion Sort — O(n²)

Customer records retrieved from the database are converted into an in-memory collection and sorted according to account/asset value.

The implementation uses the **Insertion Sort** algorithm rather than relying on a built-in sorting utility.

```
Database Records
      ↓
In-Memory Collection
      ↓
Insertion Sort
      ↓
Highest → Lowest Balance
      ↓
Ledger Display
```

**Time Complexity:** O(n²) worst/average case
**Space Complexity:** O(1) auxiliary space for in-place sorting

### 2. Binary Search — O(log n)

The account lookup functionality operates on a **sorted collection**, allowing the system to locate a target account efficiently using Binary Search.

```
Sorted Account Records
          ↓
    Binary Search
          ↓
   Target Account
          ↓
    Result / Not Found
```

**Time Complexity:** O(log n)
**Space Complexity:** O(1) iterative implementation

> The collection must remain sorted according to the same key used by the search operation for Binary Search to be valid.

## 📄 Application Structure

### `index.html`

Customer onboarding interface where new account/customer information is submitted.

### `ledger.html`

Administration dashboard containing the central ledger table, search functionality, sorting, and record-management operations.

## 🔐 Data Validation

The backend validates account information before persistence.

For example:

```text
Initial Balance < ₹500
        ↓
   Reject Request
```

This prevents invalid account records from reaching the database.

## 🔄 CRUD Operations

The backend exposes operations for managing account records:

| Operation | HTTP Method | Purpose                    |
| --------- | ----------- | -------------------------- |
| Create    | `POST`      | Add a new customer/account |
| Read      | `GET`       | Retrieve account records   |
| Delete    | `DELETE`    | Remove an account record   |

## 💵 Currency Formatting

The frontend uses the Indian locale for displaying account balances:

```javascript
new Intl.NumberFormat("en-IN", {
    style: "currency",
    currency: "INR"
});
```

Example:

```text
₹500
₹25,000
₹12,50,000
```

## 🗄️ Data Persistence

The project uses:

**Spring Data JPA → Hibernate → H2 Database**

This provides object-relational mapping between Java entities and relational database tables while keeping persistence logic separated from the application's business layer.

## ▶️ Running the Project

### Prerequisites

* Java 17+
* Maven
* Git

### Clone the Repository

```bash
git clone <your-repository-url>
cd <project-directory>
```

### Run the Spring Boot Application

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

Then open the application in your browser using the configured local server URL.
[http://localhost:9090/index.html](http://localhost:9090/index.html)

## 📌 Project Highlights

This project demonstrates practical implementation of:

* Object-Oriented Programming
* RESTful API development
* Spring Boot application architecture
* JPA/Hibernate ORM
* Relational database management
* Client-server communication
* HTTP methods and API integration
* Input validation
* Custom sorting algorithms
* Custom searching algorithms
* Time and space complexity analysis
* Indian currency localization

## 🔮 Future Improvements

* Authentication and role-based authorization
* PostgreSQL/MySQL production database integration
* Pagination for large ledger datasets
* Advanced transaction management
* Unit and integration testing
* Docker containerization
* Deployment to a cloud platform
* Audit logs for account modifications

## 👩‍💻 Author

**Nandini Rajawat**

B.Tech — Computer Science & Engineering
