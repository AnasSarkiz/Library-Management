import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();

    public static void main(String[] args) {
        System.out.println("\n\nWelcome to the Library Management System!");
        System.out.println("Enter 'help' to list all commands.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n> ");
            String command = scanner.nextLine().toLowerCase();
            if (command.equalsIgnoreCase("exit") || command.equalsIgnoreCase("quit")) {
                System.out.println("Exiting the Library Management System. Goodbye!");
                break;
            }
            execute(command);
        }
        scanner.close();
    }

    private static void execute(String command) {
        String[] args = command.trim().split(" ");
    
        if (args.length == 0 || args[0].isEmpty()) {
            System.out.println("Error: No command entered. Please provide a valid command. Type 'help' for a list of commands.");
            return;
        }
    
        String mainCommand = args[0].toLowerCase();
    
        switch (mainCommand) {
            case "help":
                displayHelp(args);
                break;
            case "add":
            case "insert":
                handleAddCommand(args);
                break;
            case "remove":
            case "delete":
                handleRemoveCommand(args);
                break;
            case "find":
            case "search":
                handleFindCommand(args);
                break;
            case "print":
            case "list":
            case "display":
            case "printall":
                library.printAllBooks();
                break;
            case "borrow":
                handleBorrowCommand(args);
                break;
            case "return":
                handleReturnCommand(args);
                break;
            default:
                System.out.println("Error: Invalid command '" + mainCommand + "'. Type 'help' for a list of valid commands.");
        }
    }
    
    private static void displayHelp(String[] args) {
        if (args.length == 1) {
            System.out.println("Available commands:");
            System.out.println("1. Add a book: 'add or insert <title> <author> <category> <email> <quantity>'");
            System.out.println("2. Remove a book: 'remove or delete <title>'");
            System.out.println("3. Find a book: 'find or search <by title/by author/by category/by email>'");
            System.out.println("4. Print all books: 'print' or 'list' or 'display' or 'printAll'");
            System.out.println("5. Borrow a book: 'borrow <title>'");
            System.out.println("6. Return a book: 'return <title>'");
            System.out.println("7. Exit the system: 'exit' or 'quit'");
        } else {
            System.out.println("Help details for: " + args[1]);
            switch (args[1].toLowerCase()) {
                case "add":
                    System.out.println("To add a book, enter 'add <title> <author> <category> <email> <quantity>'.");
                    break;
                case "remove":
                    System.out.println("To remove a book, enter 'remove <title>'.");
                    break;
                case "find":
                    System.out.println("To find a book, enter 'find by <title>, <author>, <category> or <email>'.");
                    break;
                case "print":
                case "list":
                case "display":
                case "printall":
                    System.out.println("To print all books.");
                    break;
                case "borrow":
                    System.out.println("To borrow a book, enter 'borrow <title>'.");
                    break;
                case "return":
                    System.out.println("To return a book, enter 'return <title>'.");
                    break;
                case "exit":
                case "quit":
                    System.out.println("To exit the system, enter 'exit'.");
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' for a list of valid commands.");
            }
        }
    }

    private static void handleAddCommand(String[] args) {
        String title = null;
        String author = null;
        String category = null;
        String email = null;
        String quantity = null;

        if (args.length == 6) {

            title = args[1];
            author = args[2];
            category = args[3];
            email = args[4];
            quantity = args[5];
        } else if (args.length == 1 && args[0].equals("add")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter title of the book:");
            title = scanner.nextLine();
            System.out.println("Enter author of the book:");
            author = scanner.nextLine();
            System.out.println("Enter category of the book:");
            category = scanner.nextLine();
            System.out.println("Enter email of the book:");
            email = scanner.nextLine();
            System.out.println("Enter quantity of the book:");
            quantity = scanner.nextLine();
        } else {
            System.out.println("Invalid 'add' command format. Use: 'add <title> <author> <category> <email> <quantity>'.");
            return;
        }
        if (!isNumeric(quantity)) {
            System.out.println("Invalid quantity. Expected a numeric value.");
            return;
        }

        int qnt = Integer.parseInt(quantity);
        if (qnt <= 0) {
            System.out.println("Invalid quantity. Quantity must be a positive number.");
            return;
        }

        Book book = new Book(title, author, category, email, qnt);
        library.addBook(book);
        System.out.println("Book added successfully: " + book.getInfo());
    }

    private static void handleRemoveCommand(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid 'remove' command format. Use: 'remove <title>'.");
            return;
        }
        String title = args[1];
        ArrayList<Book> books = library.findBookByTitle(title);

        if (books == null || books.isEmpty()) {
            System.out.println("No books found with the title: " + title);
            return;
        }

        System.out.println("Found the following books with the title '" + title + "':");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getInfo());
        }

        System.out.println("Enter the number of the book you want to remove:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!isNumeric(input)) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        int choice = Integer.parseInt(input);

        if (choice < 1 || choice > books.size()) {
            System.out.println("Invalid selection. No book removed.");
        } else {
            Book selectedBook = books.get(choice - 1);
            library.removeBook(selectedBook);
            System.out.println("Book removed successfully: " + selectedBook.getInfo());
        }
    }

    private static void handleFindCommand(String[] args) {
        if (args.length < 3) {
            System.out.println("Invalid 'find' command format. Use: 'find  <title/author/category/email> string'.");
            return;
        }
        String filter = args[1].toLowerCase();
        String query = args[2];
        ArrayList<Book> foundBooks = new ArrayList<>();
        switch (filter) {
            case "title":
                foundBooks = library.findBookByTitle(query);
                break;
            case "author":
                foundBooks = library.findBooksByAuthor(query);
                break;
            case "category":
                foundBooks = library.findBookByCategory(query);
                break;
            case "email":
                foundBooks = library.findBookByEmail(query);
                break;
            default:
                System.out.println("Error you must enter the title, author, category or email of the book that you want to find.");
                return;
        }
        if (foundBooks.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Found books:");
            for (Book book : foundBooks) {
                System.out.println(book.getInfo());
            }
        }
    }

    private static void handleBorrowCommand(String[] args) {
        if (args.length != 2) {
            System.out.println("Error you must enter the title of the book that you want to borrow.");
            return;
        }
        String title = args[1];
        ArrayList<Book> books = library.findBookByTitle(title);
    
        if (books == null || books.isEmpty()) {
            System.out.println("No books found with the title: " + title);
            return;
        }
    
        System.out.println("Found the following books with the title '" + title + "':");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getInfo());
        }
    
        System.out.println("Enter the number of the book you want to borrow:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!isNumeric(input)) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }
        int choice = Integer.parseInt(input);
    
        if (choice < 1 || choice > books.size()) {
            System.out.println("Invalid selection. No book borrowed.");
        } else {
            Book selectedBook = books.get(choice - 1);
            if (library.borrowBook(selectedBook)!=null) {
                System.out.println("Book borrowed successfully: " + selectedBook.getInfo());
            } else {
                System.out.println("The book is not available for borrowing.");
            }
        }
    }
    
    private static void handleReturnCommand(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid 'return' command format. Use: 'return <title>'.");
            return;
        }
        String title = args[1];
        ArrayList<Book> books = library.findBookByTitle(title);
    
        if (books == null || books.isEmpty()) {
            System.out.println("No books found with the title: " + title);
            return;
        }
    
        System.out.println("Found the following books with the title '" + title + "':");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            System.out.println((i + 1) + ". " + book.getInfo());
        }
    
        System.out.println("Enter the number of the book you want to return:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (!isNumeric(input)) 
            return;

        int choice = Integer.parseInt(input);
    
        if (choice < 1 || choice > books.size()) {
            System.out.println("Invalid selection. No book returned.");
        } else {
            Book selectedBook = books.get(choice - 1);
            if (library.returnBook(selectedBook) != null) {
                System.out.println("Book returned successfully: " + selectedBook.getInfo());
            } else {
                System.out.println("Unable to return the book. Ensure the book belongs to the library.");
            }
        }
    }
    private static boolean isNumeric(String str) {
        if (!str.matches("\\d+")) {
            System.out.println("Error: '" + str + "' is not a valid numeric value. Please enter numbers only.");
            return false;
        }
        return true;
    }
}