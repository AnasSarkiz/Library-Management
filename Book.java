public class Book extends Item {
    private String author;
    private String category;
    private String email;

    public Book(String title, String author, String category, String email, int quantity) {
        super(title, quantity);
        this.author = author;
        this.category = category;
        this.email = email;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }


    public String getInfo() {
        return "Book - Title: " + title + ", Category: " + category + 
               " by: " + author + ", Email: " + email + 
               " - Quantity: " + quantity;
    }

    public String toFileString() {
        return "BOOK," + title + "," + author + "," + category + "," + 
               email + "," + quantity;
    }

    public String getAuthor() {
        return author;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return title + " by: " + author + " category: " + category+ " email [" + email + "]";
    }
}
