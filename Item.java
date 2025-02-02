public class Item {
    protected String title;
    protected int quantity;

    public Item(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getInfo() {
        return "Item - Title: " + title + " - Quantity: " + quantity;
    }
    
    public String toFileString() {
        return "ITEM," + title + "," + quantity;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        if (obj instanceof Book && this instanceof Book) {
            Book thisBook = (Book) this;
            Book otherBook = (Book) obj;
            return title.equalsIgnoreCase(otherBook.title) &&
                   thisBook.getAuthor().equalsIgnoreCase(otherBook.getAuthor()) &&
                   thisBook.getCategory().equalsIgnoreCase(otherBook.getCategory()) &&
                   thisBook.getEmail().equalsIgnoreCase(otherBook.getEmail());
        } else if (obj instanceof CD && this instanceof CD) {
            CD thisCD = (CD) this;
            CD otherCD = (CD) obj;
            return title.equalsIgnoreCase(otherCD.title) &&
                   thisCD.getCompany().equalsIgnoreCase(otherCD.getCompany()) &&
                   thisCD.getDuration() == otherCD.getDuration();
        }
        return false;
    }
      
    public boolean isEmpty() {
        return quantity == 0;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
