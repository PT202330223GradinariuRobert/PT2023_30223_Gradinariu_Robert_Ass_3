# Warehouse Management System


## Table of Contents
1. [Project Objective](#project-objective)  
2. [Problem Analysis, Modeling, and Use Cases](#problem-analysis-modeling-and-use-cases)  
3. [Design](#design)  
4. [Implementation](#implementation)  
5. [Results](#results)  
6. [Conclusions](#conclusions)  
7. [References](#references)  

## Project Objective
The main objective of this project is to create an application to manage the orders received by a warehouse. Each order consists of:
- A client
- One or more products
- A specified quantity for each product  

The application uses a relational database and reflection techniques to ensure a generalized and maintainable solution.  
### Secondary Objectives:
1. Accessing the database
2. Extracting relevant information from the database
3. Processing this information based on specific scenarios
4. Displaying processed results via a graphical user interface (GUI)

---

## Problem Analysis, Modeling, and Use Cases
### Problem Analysis:
One critical requirement is securing data access, ensuring users only see and modify data they are authorized to handle.  
For example:
- Employees should not have access to client email addresses.
- Clients should not access data about other clients.  

The database stores information about clients, products, and transactions in relational tables.

### Use Cases:
For each table in the database, the application supports the following operations:
1. Select all rows
2. Select rows based on specific criteria
3. Insert a new row
4. Update an existing row
5. Delete a row  

Each operation includes clear input specifications in the GUI.

### Scenarios:
1. A client places an order for a product with insufficient stock → The system displays an error message.
2. A client places an order for a product with sufficient stock → The database is updated accordingly.
3. Additional operations like deleting, inserting, or updating data also generate appropriate exceptions when invalid input is detected.

---

## Design
The application follows a layered architecture, ensuring data flows from one layer to another. This design improves maintainability, security, and data integrity.

### Key Design Features:
1. **Reflection**: Used to create a generalized approach for accessing and modifying data. Reflection ensures minimal changes when adding new data types or tables.
2. **Data Modeling**: Classes in the `model` layer mirror the database tables, with fields mapped 1:1 to ensure consistency.
3. **GUI**: A simple interface with three main buttons for accessing secondary views (Clients, Products, Orders). Users can quickly access or update any available data.

---

## Implementation
The project is divided into multiple packages based on the layered architecture:

### 1. **Connection Layer**:
- Manages the connection to the MySQL database using the `ConnectionUnit` class.
- Includes methods to create and close connections.
- Uses static constants for database address, driver, username, and password.

### 2. **Data Access Layer**:
- **AbstractDAO**: An abstract class with base methods for selecting, inserting, updating, and deleting data.
- Implements SQL queries and processes the `ResultSet` returned from the database.
- Generates objects or lists of objects using the `createObjects` method.
- Each table in the database has a corresponding class extending `AbstractDAO` for additional table-specific operations.

### 3. **Business Logic Layer**:
- Processes data retrieved from the data access layer.
- Contains methods implementing application logic, including data validation and preparing data for the GUI.

### 4. **Model Layer**:
- Contains classes (`Client`, `Product`, `Order`, `OrdersProducts`) representing database tables.
- Fields in each class are mapped 1:1 to database columns for consistency.

### 5. **Graphical User Interface**:
- Features a simplistic design for ease of use.
- Main panel includes buttons to navigate to `Clients`, `Products`, and `Orders` interfaces.

---

## Results
The application successfully implements a generalized approach for managing warehouse data. Features include:
- Secure and reliable access to the database.
- Efficient handling of various operations (CRUD) for all database tables.
- A user-friendly interface for managing clients, products, and orders.

---

## Conclusions
- The layered architecture ensures modularity and maintainability.
- Using reflection simplifies data management and improves scalability.
- The relational database design and GUI implementation meet the requirements of a warehouse management system.

---

## References
- MySQL Documentation: [https://dev.mysql.com/doc/](https://dev.mysql.com/doc/)
- Java Reflection API: [https://docs.oracle.com/javase/tutorial/reflect/](https://docs.oracle.com/javase/tutorial/reflect/)
