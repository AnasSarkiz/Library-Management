import java.util.ArrayList;

public class Library {
    public ArrayList<Book> Books;

    public Library() {
        Books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        for (Book matchedBook : Books) {
            if (matchedBook.getTitle().equals(book.getTitle())&&matchedBook.getAuthor().equals(book.getAuthor())&&matchedBook.getCategory().equals(book.getCategory())&&matchedBook.getEmail().equals(book.getEmail())) {
                matchedBook.setQuantity(matchedBook.getQuantity() + book.getQuantity());
                System.out.println("Book '" + book.getTitle() + "' quantity updated. New quantity: " + matchedBook.getQuantity());
                return;
            }
        }
        Books.add(book);
        System.out.println("Book '" + book.getTitle() + "' added to the library.");
    }

    public boolean removeBook(Book book) {
        for (Book matchedBook : Books) {
            if (matchedBook.equals(book)) {
                Books.remove(matchedBook);
                System.out.println("Book '" + book.getTitle() + "' removed.");
                return true;
            }
        }
        return false;
    }
    public ArrayList<Book> findBookByTitle(String title) {
        ArrayList<Book> matchingBooks = new ArrayList<>();
        for (Book matchedBook : Books) {
            if (matchedBook.getTitle().equals(title)) {
                matchingBooks.add(matchedBook);
            }
        }
        return matchingBooks;
    }
    public ArrayList<Book> findBooksByAuthor(String author) {
        ArrayList<Book> matchingBooks = new ArrayList<>();
        for (Book matchedBook : Books) {
            if (matchedBook.getAuthor().equals(author)) {
                matchingBooks.add(matchedBook);
            }
        }
        return matchingBooks;
    }
    public ArrayList<Book> findBookByCategory(String category) {
        ArrayList<Book> matchingBooks = new ArrayList<>();
        for (Book matchedBook : Books) {
            if (matchedBook.getCategory().equals(category)) {
                matchingBooks.add(matchedBook);
            }
        }
        return matchingBooks;
    }

    public ArrayList<Book> findBookByEmail(String email) {
        ArrayList<Book> matchingBooks = new ArrayList<>();
        for (Book matchedBook : Books) {
            if (matchedBook.getEmail().equals(email)) {
                matchingBooks.add(matchedBook);
            }
        }
        return matchingBooks;
    }

    public void printAllBooks() {
        for (Book Book : Books) {
            System.out.println(Book.getInfo());
        }
    }
    public Book borrowBook(Book book) {
        for (Book matchedBook : Books) {
            if (matchedBook.equals(book)) {
                if (matchedBook.getQuantity() <= 0) {
                    System.out.println("Book '" + matchedBook.getTitle() + "' is out of stock.");
                    return null;
                }
                matchedBook.setQuantity(matchedBook.getQuantity() - 1);
                System.out.println("Book '" + matchedBook.getTitle() + "' borrowed.");
                return matchedBook;
            }
        }
        System.out.println("Book '" + book.getTitle() + "' not found.");
        return null;
    }

    public Book returnBook(Book book) {
        for (Book matchedBook : Books) {
            if (matchedBook.equals(book)) {
                matchedBook.setQuantity(matchedBook.getQuantity() + 1);
                System.out.println("Book '" + matchedBook.getTitle() + "' returned.");
                return matchedBook;
            }
        }
        System.out.println("Book '" + book.getTitle() + "' not found.");
        return null;
    }
}