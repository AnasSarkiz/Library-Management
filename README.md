# Library Management System

A comprehensive Java-based library management system with a graphical user interface that enables efficient management of books and CDs, borrowing operations, and advanced search capabilities.

## System Requirements

- Java JDK 8 or higher
- Command-line interface
- Git (for cloning the repository)

## Getting Started

### Installation

1. Clone this repository:
```bash
git clone https://github.com/AnasSarkiz/library-management-system.git
cd library-management-system
```

2. Compile the Java files:
```bash
javac *.java
```

3. Run the application:
```bash
java Main
```

The graphical user interface will launch automatically.

## Introduction

This Library Management System is a modern GUI application designed for efficient library management. It offers:

### Key Features
- Complete item management (add, remove, update)
- Support for both Books and CDs
- Multi-criteria search functionality
- Borrowing and return tracking
- Quantity management
- User contact information management
- Data persistence
- Input validation
- Error handling
- Intuitive graphical interface

### Benefits
- User-friendly graphical interface
- Efficient item tracking
- Multiple search options
- Reliable inventory management
- Support for different media types
- User accountability through email tracking

## GUI Usage Guide

The application provides an intuitive graphical interface with the following features:

### Item Management
- Add new Books or CDs using the input form
- Remove items by title
- Search items by various criteria
- List all items in the library
- Borrow and return items

### Input Fields
For Books:
- Title
- Author
- Category
- Email
- Quantity

For CDs:
- Title
- Company
- Duration (in minutes)
- Quantity

### Operations
- Use the dropdown to select item type (Book/CD)
- Fill in the relevant fields
- Click the appropriate button for the desired action (Add, Remove, Find, etc.)
- Results are displayed in the output area

### Notes:
- All required fields must be filled
- Email addresses must be in valid format
- Quantity must be a positive integer
- Duration must be a positive number

## Help Guide

To use the Library Management System:

1. Launch the application
2. Select the type of item (Book/CD) from the dropdown
3. Fill in the required fields
4. Click the appropriate button for your desired action
5. View results in the output area
6. Clear fields using the reset button when needed

## System Design

### Use Case Diagram
```
                    Library Management System
                           ┌──────┐
                           │ User │
                           └──┬───┘
                              │
           ┌──────────────────┼──────────────────┐
           │                  │                  │
     ┌─────▼─────-┐     ┌─────▼─────┐      ┌─────▼─────┐
     │  Manage    │     │   Search  │      │  Borrow/  │
     │   Books    │     │   Books   │      │  Return   │
     └─────┬─────-┘     └─────┬─────┘      └─────-┬────┘
           │                  │                   │
     ┌─────▼─────-┐     ┌─────▼─────┐       ┌─────▼─────┐
     │ Add Book   │     │Find by    │       │ Borrow    │
     │ Remove Book│     │-Title     │       │ Return    │
     └───────────-┘     │-Author    │       └───────────┘
                        │-Category  │
                        │-Email     │
                        └──────────-┘
```

### Class Diagram
```
                     ┌───────────────┐
                     │     Item      │
                     ├───────────────┤
                     │ -title        │
                     │ -quantity     │
                     ├───────────────┤
                     │ +getInfo()    │
                     │ +toFileString()│
                     │ +getters()    │
                     │ +setters()    │
                     └───────┬───────┘
                             │
              ┌──────────────┴──────────────┐
              │                             │
    ┌─────────▼────────┐         ┌─────────▼────────┐
    │      Book        │         │       CD         │
    ├──────────────────┤         ├──────────────────┤
    │ -author          │         │ -company         │
    │ -category        │         │ -duration        │
    │ -email           │         │                  │
    ├──────────────────┤         ├──────────────────┤
    │ +getInfo()       │         │ +getInfo()       │
    │ +toFileString()  │         │ +toFileString()  │
    └──────────────────┘         └──────────────────┘
                     │
              ┌──────▼───────┐
              │   Library    │
              ├──────────────┤
              │ +items       │
              ├──────────────┤
              │ +addItem()   │
              │ +removeItem()│
              │ +findItem()  │
              │ +borrow()    │
              │ +return()    │
              └──────────────┘
```

## Error Handling

The system handles various error scenarios:
- Invalid command format
- Book not found
- Insufficient book quantity
- Invalid email format
- Duplicate book entries
- Invalid quantity values

## Data Management

- Books are stored in memory during runtime
- Each book record contains:
  - Title
  - Author
  - Category
  - Contact Email
  - Quantity
- Changes are tracked in real-time

## Contributing

We welcome contributions! Please:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support, please:
1. Check the help command in the application
2. Review the documentation
3. Submit an issue on GitHub
