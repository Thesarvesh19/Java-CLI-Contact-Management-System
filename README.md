# Java CLI Contact Management System

updated version

## About The Project

This project is a comprehensive, console-based Contact Management System built entirely in Java. It serves as a practical demonstration of core Object-Oriented Programming (OOP) principles, including encapsulation, and data management using `ArrayLists`.

The application provides full **CRUD (Create, Read, Update, Delete)** functionality, allowing a user to manage a list of contacts through a simple, interactive command-line interface (CLI). The interface is enhanced with color-coding for a more intuitive and readable user experience.

## Key Features

* **Full CRUD Functionality**
    * **Create:** Add a new contact by providing a name, phone number, and email address.
    * **Read:** Display a clean, numbered list of all saved contacts.
    * **Update:** Select a contact by number to edit their name, phone number, or email address individually.
    * **Delete:** Remove a contact permanently from the list by selecting their corresponding number.

* **Interactive Command-Line Interface (CLI)**
    * Features a user-friendly main menu that clearly lists all available actions.
    * Utilizes ANSI escape codes to color-code the console output, improving readability. For example, success messages are green, errors are red, and prompts are yellow.

* **Input Validation and Error Handling**
    * **Duplicate Prevention:** The system checks for existing phone numbers to prevent duplicate entries, ensuring data integrity.
    * **Phone Number Validation:** Verifies that a phone number input contains digits before accepting it.
    * **Email Validation:** Performs a basic check to ensure that an email address contains the `@` and `.` characters.
    * **Robust Menu Navigation:** Gracefully handles invalid inputs, such as text instead of numbers, when the user is making a menu choice.

* **Powerful Search**
    * Allows users to search the entire contact list by a name or keyword.
    * The search is **case-insensitive**, meaning a search for "john" will find "John" and "JOHN".
    * Displays all contacts whose names contain the search query.

## Technologies & Concepts

* **Language:** Java
* **Core Concepts:** Object-Oriented Programming (OOP), Encapsulation.
* **Data Structure:** `java.util.ArrayList` for dynamic storage of contact objects.
* **I/O:** `java.util.Scanner` for handling all user input from the console.

## Code Structure

* `Contact.java`: A model class that encapsulates the properties of a single contact (name, phoneNumber, email) with private fields and public getter/setter methods.
* `ContactManager.java`: The main driver class that contains the application's logic, including the main menu, user input handling, and all methods for managing the contact list.
