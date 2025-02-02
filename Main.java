import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main extends JFrame {
    private static Library library;
    private JTextField titleField, authorField, categoryField, emailField;
    private JTextField quantityField, companyField, durationField;
    private JComboBox<String> itemTypeComboBox;
    private JTextArea outputArea;
    private String DATA_FILE = "library_data.txt";

    // Panels for grouping input components
    private JPanel bookPanel;
    private JPanel cdPanel;
    // The container for common and specific fields
    private JPanel inputPanel;

    public Main() {
        try {
            library = new Library();
            library.loadFromFile(DATA_FILE);
            initializeGUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to initialize library: " +
                    e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void initializeGUI() {
        setTitle("Library Management System");
        setSize(900, 700);
        setMinimumSize(new Dimension(700, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Welcome Panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));

        JLabel welcomeLabel = new JLabel("Welcome to Library Management System 📚");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setForeground(new Color(44, 62, 80));

        JLabel subLabel = new JLabel("Organize, Track, and Manage Your Collection");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subLabel.setForeground(new Color(52, 73, 94));

        JButton continueButton = new JButton("Enter Library →");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        continueButton.setForeground(Color.black);

        welcomePanel.add(Box.createVerticalGlue());
        welcomePanel.add(Box.createVerticalStrut(10));
        welcomePanel.add(welcomeLabel);
        welcomePanel.add(Box.createVerticalStrut(15));
        welcomePanel.add(subLabel);
        welcomePanel.add(Box.createVerticalStrut(50));
        welcomePanel.add(continueButton);
        welcomePanel.add(Box.createVerticalGlue());

        // Common fields panel (applies to both Book and CD)
        JPanel commonPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        commonPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 10));

        JLabel itemTypeLabel = new JLabel("Item Type:");
        itemTypeComboBox = new JComboBox<>(new String[]{"Book 📚", "CD 💿"});
        itemTypeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        commonPanel.add(itemTypeLabel);
        commonPanel.add(itemTypeComboBox);

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField(20);
        titleField.setToolTipText("Enter the title of the item");
        commonPanel.add(titleLabel);
        commonPanel.add(titleField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(10);
        quantityField.setToolTipText("Enter the quantity to add");
        quantityField.setText("1");
        commonPanel.add(quantityLabel);
        commonPanel.add(quantityField);

        // Book-specific panel
        bookPanel = new JPanel(new GridLayout(3, 2, 3, 5));
        bookPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        JLabel authorLabel = new JLabel("Author:");
        authorField = new JTextField(20);
        authorField.setToolTipText("Enter the author's name (for books)");
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);
        categoryField.setToolTipText("Enter the book category");
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setToolTipText("Enter contact email for the book");

        bookPanel.add(authorLabel);
        bookPanel.add(authorField);
        bookPanel.add(categoryLabel);
        bookPanel.add(categoryField);
        bookPanel.add(emailLabel);
        bookPanel.add(emailField);

        // CD-specific panel
        cdPanel = new JPanel(new GridLayout(2, 2, 3, 5));
        cdPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 40, 10));
        JLabel companyLabel = new JLabel("Company:");
        companyField = new JTextField(20);
        companyField.setToolTipText("Enter the company name (for CDs)");
        JLabel durationLabel = new JLabel("Duration (minutes):");
        durationField = new JTextField(20);
        durationField.setToolTipText("Enter the duration in minutes (for CDs)");

        cdPanel.add(companyLabel);
        cdPanel.add(companyField);
        cdPanel.add(durationLabel);
        cdPanel.add(durationField);
        // Initially hide CD panel
        cdPanel.setVisible(false);

        // Combine the common panel and the specific panels using BoxLayout.
        inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(commonPanel);
        inputPanel.add(bookPanel);
        inputPanel.add(cdPanel);

        // Tooltips for common fields already set above

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JButton findButton = new JButton("Find Item");
        JButton listButton = new JButton("List All");
        JButton borrowButton = new JButton("Borrow Item");
        JButton returnButton = new JButton("Return Item");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(findButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);

        // List button panel (for List All)
        JPanel listButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listButtonPanel.add(listButton);

        // Output text area
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Main content panel (combines fields, buttons, and output)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(listButtonPanel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel exitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        exitPanel.add(exitButton);
        bottomPanel.add(exitPanel, BorderLayout.SOUTH);

        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Use CardLayout for welcome and main panels
        CardLayout cardLayout = new CardLayout();
        JPanel mainContainer = new JPanel(cardLayout);
        mainContainer.add(welcomePanel, "welcome");
        mainContainer.add(contentPanel, "main");

        setLayout(new BorderLayout());
        add(mainContainer);

        // Switch panels
        continueButton.addActionListener(e -> cardLayout.show(mainContainer, "main"));

        // Exit button action listener
        exitButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
            );
            if (result == JOptionPane.YES_OPTION) {
                try {
                    library.saveToFile(DATA_FILE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error saving data: " +
                            ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
                System.exit(0);
            }
        });

        // Update which fields are shown based on item type selection
        itemTypeComboBox.addActionListener(e -> {
            String selected = itemTypeComboBox.getSelectedItem().toString();
            if (selected.contains("Book")) {
                bookPanel.setVisible(true);
                cdPanel.setVisible(false);
            } else if (selected.contains("CD")) {
                bookPanel.setVisible(false);
                cdPanel.setVisible(true);
            }
            // Refresh layout
            inputPanel.revalidate();
            inputPanel.repaint();
        });

        // Action Listeners for buttons
        addButton.addActionListener(e -> handleAddBook());
        removeButton.addActionListener(e -> handleRemoveBook());
        findButton.addActionListener(e -> handleFindBook());
        listButton.addActionListener(e -> handleListBooks());
        borrowButton.addActionListener(e -> handleBorrowBook());
        returnButton.addActionListener(e -> handleReturnBook());

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
            // Determine if picking Book or CD based on text (contains "Book")
            boolean isBook = itemTypeComboBox.getSelectedItem().toString()
                    .contains("Book");

            if (title.isEmpty()) {
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

            Item item;
            if (isBook) {
                String author = authorField.getText().trim();
                String category = categoryField.getText().trim();
                String email = emailField.getText().trim();
                if (author.isEmpty() || category.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "All fields must be filled out for a book!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                item = new Book(title, author, category, email, quantity);
            } else {
                String company = companyField.getText().trim();
                String durationStr = durationField.getText().trim();
                if (company.isEmpty() || durationStr.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "All fields must be filled out for a CD!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int duration;
                try {
                    duration = Integer.parseInt(durationStr);
                    if(duration <= 0){
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                        "Please enter a valid positive number for duration!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                item = new CD(title, company, duration, quantity);
            }

            library.addItem(item);
            outputArea.setText("Item added successfully:\n" + item.getInfo());
            clearFields();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error adding item: " + e.getMessage(),
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
        ArrayList<Item> items = library.findItemByTitle(title);
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No items found with title: " + title,
                "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (items.size() > 1) {
            Item selectedItem = (Item) JOptionPane.showInputDialog(
                this,
                "Select item to remove:",
                "Remove Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                items.toArray(),
                items.get(0));
            if (selectedItem != null && library.removeItem(selectedItem)) {
                outputArea.setText("Item removed successfully:\n" +
                    selectedItem.getInfo());
                clearFields();
            }
        } else {
            Item itemToRemove = items.get(0);
            if (library.removeItem(itemToRemove)) {
                outputArea.setText("Item removed successfully:\n" +
                    itemToRemove.getInfo());
                clearFields();
            }
        }
    }

    private void handleFindBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String category = categoryField.getText().trim();
        String email = emailField.getText().trim();
        String company = companyField.getText().trim();
        String duration = durationField.getText().trim();
        boolean isBook = itemTypeComboBox.getSelectedItem().toString().contains("Book");
        
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter a title to search!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        ArrayList<Item> items = library.findItemByTitle(title);
        ArrayList<Item> filteredItems = new ArrayList<>();
        
        for (Item item : items) {
            // First filter by item type
            if (isBook && !(item instanceof Book)) continue;
            if (!isBook && !(item instanceof CD)) continue;
            
            boolean matches = true;
            
            if (item instanceof Book) {
                Book book = (Book) item;
                if (!author.isEmpty() && !book.getAuthor().equals(author)) {
                    matches = false;
                }
                if (!category.isEmpty() && !book.getCategory().equals(category)) {
                    matches = false;
                }
                if (!email.isEmpty() && !book.getEmail().equals(email)) {
                    matches = false;
                }
            } else if (item instanceof CD) {
                CD cd = (CD) item;
                if (!company.isEmpty() && !cd.getCompany().equals(company)) {
                    matches = false;
                }
                if (!duration.isEmpty()) {
                    try {
                        int durationValue = Integer.parseInt(duration);
                        if (cd.getDuration() != durationValue) {
                            matches = false;
                        }
                    } catch (NumberFormatException e) {
                        // Invalid duration format, ignore duration filter
                    }
                }
            }
            
            if (matches) {
                filteredItems.add(item);
            }
        }
        
        if (filteredItems.isEmpty()) {
            outputArea.setText("No " + (isBook ? "books" : "CDs") + " found matching all criteria");
        } else {
            StringBuilder result = new StringBuilder("Found " + (isBook ? "books" : "CDs") + ":\n");
            for (Item item : filteredItems) {
                result.append(item.getInfo()).append("\n");
            }
            outputArea.setText(result.toString());
        }
    }

    private void handleListBooks() {
        StringBuilder result = new StringBuilder("All items in library:\n");
        if (library.items.isEmpty()) {
            outputArea.setText("No items in library.");
            return;
        }
        for (Item item : library.items) {
            result.append(item.getInfo()).append("\n");
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
        ArrayList<Item> items = library.findItemByTitle(title);
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No items found with title: " + title,
                "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Item selectedItem = (items.size() > 1) ?
            (Item) JOptionPane.showInputDialog(
                this,
                "Select item to borrow:",
                "Borrow Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                items.toArray(),
                items.get(0)) : items.get(0);
        if (selectedItem != null) {
            Item borrowedItem = library.borrowItem(selectedItem);
            if (borrowedItem != null) {
                outputArea.setText("Item borrowed successfully:\n" +
                    borrowedItem.getInfo());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "The item is not available for borrowing.",
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
        ArrayList<Item> items = library.findItemByTitle(title);
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No items found with title: " + title,
                "Not Found",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Item selectedItem = (items.size() > 1) ?
            (Item) JOptionPane.showInputDialog(
                this,
                "Select item to return:",
                "Return Item",
                JOptionPane.QUESTION_MESSAGE,
                null,
                items.toArray(),
                items.get(0))
            : items.get(0);
        if (selectedItem != null) {
            Item returnedItem = library.returnItem(selectedItem);
            if (returnedItem != null) {
                outputArea.setText("Item returned successfully:\n" +
                    returnedItem.getInfo());
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Unable to return the item. Ensure the item belongs to the library.",
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
        companyField.setText("");
        durationField.setText("");
        quantityField.setText("1");
    }
}
