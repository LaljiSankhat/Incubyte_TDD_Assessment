# 🍭 Sweet Shop Management System

A simple Java-based application to manage a sweet shop. It supports adding, updating, deleting, searching, sorting, purchasing, and restocking sweets. This project follows Test-Driven Development (TDD) with JUnit tests.

## ✅ Features

- Add, update, and delete sweets
- Search sweets by name
- Sort sweets by name or price
- Purchase and restock sweets
- View all sweets
- Unit tested with JUnit

## 🧪 Test Cases Covered

- `testAddSweetWithDuplicateIdShouldFail()` – Fails if sweet ID is not unique
- `testDeleteSweet()` – Deletes a sweet by ID
- `testUpdateSweet()` – Updates sweet details
- `testGetAllSweets()` – Retrieves all sweets
- `testSearchByName()` – Searches sweets by name
- `testSortByName()` – Sorts sweets alphabetically
- `testSortByPrice()` – Sorts sweets by price
- `testPurchaseSweet()` – Reduces quantity when purchasing
- `testPurchaseSweetInsufficientQuantity()` – Throws error when purchase exceeds available quantity
- `testRestockSweet()` – Increases quantity
- `testClearAll()` – Clears all sweets (for testing purposes)

## ▶️ How to Run

1. Clone or download the project.
2. Open in any Java IDE (e.g., IntelliJ, Eclipse).
3. Make sure to first compile the project with all JAR files present in the `lib` folder to run the test cases successfully.
4. Run `Main.java` to test manually.
5. Run `SweetShopServiceTest.java` to execute all unit tests.

## 🧰 Technologies Used

- Java
- JUnit 5
