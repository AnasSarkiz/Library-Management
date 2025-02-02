public class CD extends Item {
    private String company;
    private int duration;

    public CD(String title, String company, int duration, int quantity) {
        super(title, quantity);
        this.company = company;
        this.duration = duration;
    }


    public String getInfo() {
        return "CD - Title: " + title + ", Company: " + company + 
               ", Duration: " + duration + "min - Quantity: " + quantity;
    }

    public String toFileString() {
        return "CD," + title + "," + company + "," + duration + "," + 
               quantity;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return title + " company: " + company  + " duration: " + "[" + duration + " min]" ;
    }
}
