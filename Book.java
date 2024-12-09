public class Book {
    private String title;
    private String author;
    private String category;
    private String email;
    private int quantity=0;

    public Book(String title, String author, String category, String email, int quantity) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.email = email;
        this.quantity = quantity;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }
    public String getInfo() {
        return "Title: "+ title + ", Category: " + category + " by: " + author + " , Email: " + email + " - Quantity: " + quantity;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public String getEmail() {
        return email;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
