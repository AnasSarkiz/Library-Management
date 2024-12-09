# Library Management System

A comprehensive Java-based library management system that enables efficient book management, borrowing operations, and advanced search capabilities.

## System Requirements

- Java JDK 8 or higher
- Command-line interface
- Git (for cloning the repository)

## Getting Started

### Installation

1. Clone this repository:
```bash
git clone https://github.com/yourusername/library-management-system.git
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

## Introduction

This Library Management System is a robust command-line application designed for efficient library management. It offers:

### Key Features
- Complete book management (add, remove, update)
- Multi-criteria search functionality
- Borrowing and return tracking
- Quantity management
- User contact information management
- Data persistence
- Input validation
- Error handling

### Benefits
- Easy to use command-line interface
- Efficient book tracking
- Multiple search options
- Reliable book management
- User accountability through email tracking

## Commands and Usage

| Command | Format | Description | Example |
|---------|---------|-------------|----------|
| add/insert | `add <title> <author> <category> <email> <quantity>` | Add a new book to the library | `add "Java Programming" "John Doe" "Programming" "john@email.com" 5` |
| remove/delete | `remove <title>` | Remove a book from the library | `remove "Java Programming"` |
| find/search | `find by <title/author/category/email> <query>` | Search for books using different criteria | `find by author "John Doe"` |
| print/list/display | `print` | Display all books in the library | `print` |
| borrow | `borrow <title>` | Borrow a book from the library | `borrow "Java Programming"` |
| return | `return <title>` | Return a book to the library | `return "Java Programming"` |
| help | `help` | Display available commands | `help` |
| exit/quit | `exit` | Exit the application | `exit` |

### Notes:
- Titles with spaces should be enclosed in quotes
- Email addresses must be valid format
- Quantity must be a positive integer
- Commands are case-insensitive

## Help Guide

To use the Library Management System:

1. Start the application
2. Use commands from the table above
3. Follow the format exactly as shown
4. Type 'help' anytime to see available commands

## System Design

### Use Case Diagram
```
                    Library Management System
                            ┌──────┐
                            │ User │
                            └──┬───┘
                               │
           ┌──────────────────┼──────────────────┐
           │                  │                   │
     ┌─────▼─────┐     ┌─────▼─────┐      ┌─────▼─────┐
     │  Manage    │     │   Search   │      │  Borrow/   │
     │   Books    │     │   Books    │      │  Return    │
     └─────┬─────┘     └─────┬─────┘      └─────┬─────┘
           │                  │                   │
     ┌─────▼─────┐     ┌─────▼─────┐      ┌─────▼─────┐
     │ Add Book   │     │Find by    │      │ Borrow    │
     │ Remove Book│     │-Title     │      │ Return    │
     └───────────┘     │-Author    │      └───────────┘
                       │-Category  │
                       │-Email     │
                       └───────────┘
```

### Class Diagram
```
┌───────────────┐         ┌───────────────┐
│    Library    │         │     Book      │
├───────────────┤    1    ├───────────────┤
│ +Books        │◆────────│ -title        │
├───────────────┤    *    │ -author       │
│ +addBook()    │         │ -category     │
│ +removeBook() │         │ -email        │
│ +findBook...()│         │ -quantity     │
│ +borrowBook() │         ├───────────────┤
│ +returnBook() │         │ +getters()    │
└───────────────┘         │ +setters()    │
                         └───────────────┘
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
