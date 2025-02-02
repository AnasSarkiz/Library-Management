import java.io.*;
import java.util.ArrayList;

public class Library {
    public ArrayList<Item> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        for (Item matchedItem : items) {
            if (matchedItem.equals(item)) {
                matchedItem.setQuantity(matchedItem.getQuantity() + item.getQuantity());
                System.out.println("Item '" + item.getTitle() + "' quantity updated. New quantity: " + matchedItem.getQuantity());
                return;
            }
        }
        items.add(item);
        System.out.println("Item '" + item.getTitle() + "' added to the library.");
    }

    public boolean removeItem(Item item) {
        for (Item matchedItem : items) {
            if (matchedItem.equals(item)) {
                items.remove(matchedItem);
                System.out.println("Item '" + item.getTitle() + "' removed.");
                return true;
            }
        }
        return false;
    }

    public ArrayList<Item> findItemByTitle(String title) {
        ArrayList<Item> matchingItems = new ArrayList<>();
        for (Item matchedItem : items) {
            if (matchedItem.getTitle().equals(title)) {
                matchingItems.add(matchedItem);
            }
        }
        return matchingItems;
    }

    public Item borrowItem(Item item) {
        for (Item matchedItem : items) {
            if (matchedItem.equals(item)) {
                if (matchedItem.getQuantity() <= 0) {
                    System.out.println("Item '" + matchedItem.getTitle() + "' is out of stock.");
                    return null;
                }
                matchedItem.setQuantity(matchedItem.getQuantity() - 1);
                System.out.println("Item '" + matchedItem.getTitle() + "' borrowed.");
                return matchedItem;
            }
        }
        System.out.println("Item '" + item.getTitle() + "' not found.");
        return null;
    }

    public Item returnItem(Item item) {
        for (Item matchedItem : items) {
            if (matchedItem.equals(item)) {
                matchedItem.setQuantity(matchedItem.getQuantity() + 1);
                System.out.println("Item '" + matchedItem.getTitle() + "' returned.");
                return matchedItem;
            }
        }
        System.out.println("Item '" + item.getTitle() + "' not found.");
        return null;
    }

    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals("BOOK")) {
                    items.add(new Book(parts[1], parts[2], parts[3], parts[4], Integer.parseInt(parts[5])));
                } else if (parts[0].equals("CD")) {
                    items.add(new CD(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                }
            }
        }
    }

    public void saveToFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Item item : items) {
                if(item instanceof Book)
                    writer.write(((Book)item).toFileString());
                else if(item instanceof CD)
                    writer.write(((CD)item).toFileString());
                writer.newLine();
            }
        }
    }
}