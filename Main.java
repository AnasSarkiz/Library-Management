import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

public class Main extends JFrame {
    private static Library library;
    private JTextField titleField, authorField, categoryField, emailField, quantityField;
    private JTextArea outputArea;
    
    public Main() {
        try {
            library = new Library();
            initializeGUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Failed to initialize library: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private void initializeGUI() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(new Color(242, 242, 242));

        JLabel welcomeLabel = new JLabel("Welcome to Our Library Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(new Color(86, 130, 140));  

        JLabel subLabel = new JLabel("Manage your books efficiently");
        subLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subLabel.setForeground(welcomeLabel.getForeground());

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createVerticalStrut(10));
        welcomePanel.add(subLabel);
        welcomePanel.add(Box.createVerticalStrut(20));
        JButton continueButton = new JButton("Continue to Library");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setFont(new Font("Arial", Font.BOLD, 14));
        continueButton.setForeground(welcomeLabel.getForeground());
        welcomePanel.add(continueButton);
        welcomePanel.add(Box.createVerticalGlue());
        
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); 
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
       
        titleField = new JTextField(20);
        authorField = new JTextField(20);
        categoryField = new JTextField(20);
        emailField = new JTextField(20);
        quantityField = new JTextField(10);
        
       
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        quantityField.setText("1");
        
        for (Component c : inputPanel.getComponents()) {
           c.setForeground(new Color(61, 118, 130));
        }
       
        JButton addButton = new JButton("Add Book");
        JButton removeButton = new JButton("Remove Book");
        JButton findButton = new JButton("Find Book");
        JButton listButton = new JButton("List All Books");
        JButton borrowButton = new JButton("Borrow Book");
        JButton returnButton = new JButton("Return Book");
        JButton exitButton = new JButton("Exit");
       
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(findButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        for (Component c : buttonPanel.getComponents()) {
            c.setForeground(new Color(61, 118, 130));
        }
        
        JPanel listButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listButtonPanel.add(listButton);
        
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        
       
        addButton.addActionListener(e -> handleAddBook());
        removeButton.addActionListener(e -> handleRemoveBook());
        findButton.addActionListener(e -> handleFindBook());
        listButton.addActionListener(e -> handleListBooks());
        borrowButton.addActionListener(e -> handleBorrowBook());
        returnButton.addActionListener(e -> handleReturnBook());
        
       
        CardLayout cardLayout = new CardLayout();
        JPanel mainContainer = new JPanel(cardLayout);
        
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.add(exitButton);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(listButtonPanel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        bottomPanel.add(exitPanel, BorderLayout.SOUTH);
        
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainContainer.add(welcomePanel, "welcome");
        mainContainer.add(contentPanel, "main");
        
        setLayout(new BorderLayout());
        add(mainContainer);
        
        continueButton.addActionListener(e -> cardLayout.show(mainContainer, "main"));
        
        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
       
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    private void handleAddBook() {
        try {
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String category = categoryField.getText().trim();
            String email = emailField.getText().trim();
            
            if (title.isEmpty() || author.isEmpty() || category.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "All fields must be filled out!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText().trim());
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a valid positive number for quantity!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Book book = new Book(title, author, category, email, quantity);
            library.addBook(book);
            outputArea.setText("Book added successfully:\n" + book.getInfo());
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error adding book: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleRemoveBook() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a title to remove!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ArrayList<Book> books = library.findBookByTitle(title);
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No books found with title: " + title,
                "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (books.size() > 1) {
            Book selectedBook = (Book) JOptionPane.showInputDialog(
                this,
                "Select book to remove:",
                "Remove Book",
                JOptionPane.QUESTION_MESSAGE,
                null,
                books.toArray(),
                books.get(0));
                
            if (selectedBook != null && library.removeBook(selectedBook)) {
                outputArea.setText("Book removed successfully:\n" + selectedBook.getInfo());
                clearFields();
            }
        } else {
            Book bookToRemove = books.get(0);
            if (library.removeBook(bookToRemove)) {
                outputArea.setText("Book removed successfully:\n" + bookToRemove.getInfo());
                clearFields();
            }
        }
    }
    
    private void handleFindBook() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a title to search!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ArrayList<Book> books = library.findBookByTitle(title);
        if (books.isEmpty()) {
            outputArea.setText("No books found with title: " + title);
        } else {
            StringBuilder result = new StringBuilder("Found books:\n");
            for (Book book : books) {
                result.append(book.getInfo()).append("\n");
            }
            outputArea.setText(result.toString());
        }
    }
    
    private void handleListBooks() {
        StringBuilder result = new StringBuilder("All books in library:\n");
        if(library.Books.isEmpty()) {
            outputArea.setText("No books in library.");
            return;
        }
        for (Book book : library.Books) {
            result.append(book.getInfo()).append("\n");
        }
        outputArea.setText(result.toString());
    }
    
    private void handleBorrowBook() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a title to borrow!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ArrayList<Book> books = library.findBookByTitle(title);
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No books found with title: " + title,
                "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Book selectedBook = books.size() > 1 ?
            (Book) JOptionPane.showInputDialog(
                this,
                "Select book to borrow:",
                "Borrow Book",
                JOptionPane.QUESTION_MESSAGE,
                null,
                books.toArray(),
                books.get(0)) :
            books.get(0);
            
        if (selectedBook != null) {
            Book borrowedBook = library.borrowBook(selectedBook);
            if (borrowedBook != null) {
                outputArea.setText("Book borrowed successfully:\n" + borrowedBook.getInfo());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "The book is not available for borrowing.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void handleReturnBook() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a title to return!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ArrayList<Book> books = library.findBookByTitle(title);
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No books found with title: " + title,
                "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Book selectedBook = books.size() > 1 ?
            (Book) JOptionPane.showInputDialog(
                this,
                "Select book to return:",
                "Return Book",
                JOptionPane.QUESTION_MESSAGE,
                null,
                books.toArray(),
                books.get(0)) :
            books.get(0);
            
        if (selectedBook != null) {
            Book returnedBook = library.returnBook(selectedBook);
            if (returnedBook != null) {
                outputArea.setText("Book returned successfully:\n" + returnedBook.getInfo());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Unable to return the book. Ensure the book belongs to the library.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        categoryField.setText("");
        emailField.setText("");
        quantityField.setText("1");
    }
}
