package service;

import model.Sweet;
import exception.SweetNotFoundException;

import java.util.*;

public class SweetShopService {

    // Internal list to store all sweets in the shop
    private final List<Sweet> sweets = new ArrayList<>();

    
     /*
     * Adds a new sweet to the shop.
     * Throws an exception if a sweet with the same ID already exists.
     */
    public void addSweet(Sweet sweet) {
        for (Sweet s : sweets) {
            if (s.getId() == sweet.getId()) {
                throw new IllegalArgumentException("Sweet ID must be unique.");
            }
        }
        sweets.add(sweet);
    }

     /**
     * Deletes a sweet by its ID.
     * Throws SweetNotFoundException if no sweet with the given ID is found.
     */
    public void deleteSweet(int id) {
        Iterator<Sweet> iterator = sweets.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (!removed)
            throw new SweetNotFoundException("Sweet not found with ID: " + id);
    }

     /**
     * Returns a copy of all sweets in the shop.
     * This ensures the internal list is not modified externally.
     */
    public List<Sweet> getAllSweets() {
        return new ArrayList<>(sweets); 
    }

    /**
     * Searches for sweets whose names contain the given keyword (case-insensitive).
     */
    public List<Sweet> searchByName(String name) {
        List<Sweet> result = new ArrayList<>();

        // Loop through all sweets and check if name contains the given keyword (ignoring case)
        for (Sweet s : sweets) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Searches for sweets that belong to a specific category (case-insensitive).
     */
    public List<Sweet> searchByCategory(String category) {
        List<Sweet> result = new ArrayList<>();
        for (Sweet s : sweets) {
            if (s.getCategory().equalsIgnoreCase(category)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Searches for sweets within the given price range (inclusive).
     */
    public List<Sweet> searchByPriceRange(double min, double max) {
        List<Sweet> result = new ArrayList<>();
        for (Sweet s : sweets) {
            if (s.getPrice() >= min && s.getPrice() <= max) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Finds a sweet by its unique ID.
     */
    private Sweet findSweetById(int id) {
        for (Sweet s : sweets) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new SweetNotFoundException("Sweet not found with ID: " + id);
    }

    /**
     * Purchases a specific quantity of a sweet by ID.
     * Reduces the quantity if enough stock is available.
     */
    public void purchaseSweet(int id, int qty) {
        Sweet sweet = findSweetById(id);
        if (sweet.getQuantity() < qty) {
            throw new IllegalArgumentException("Not enough stock.");
        }
        sweet.setQuantity(sweet.getQuantity() - qty);
    }

    /**
     * Adds more stock (quantity) to an existing sweet by ID.
     */
    public void restockSweet(int id, int qty) {
        Sweet sweet = findSweetById(id);
        sweet.setQuantity(sweet.getQuantity() + qty);
    }

    /**
     * Sorts the list of sweets based on a specified field and order.
     */
    public List<Sweet> sortSweets(String sortBy, boolean ascending) {
        List<Sweet> sorted = new ArrayList<>(sweets);

        Comparator<Sweet> comparator;

        // Select comparator based on the sortBy field
        switch (sortBy.toLowerCase()) {
            case "name":
                comparator = Comparator.comparing(Sweet::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "category":
                comparator = Comparator.comparing(Sweet::getCategory, String.CASE_INSENSITIVE_ORDER);
                break;
            case "price":
                comparator = Comparator.comparingDouble(Sweet::getPrice);
                break;
            case "quantity":
                comparator = Comparator.comparingInt(Sweet::getQuantity);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }

        // Reverse comparator if descending order is requested
        if (!ascending) {
            comparator = comparator.reversed();
        }

        // Sort the list using the chosen comparator
        sorted.sort(comparator);
        return sorted;
    }

    /**
     * Clears all sweets from the sweet shop inventory.
     * 
     * Primarily used for resetting the state during testing.
     */
    public void clearAll() {
        sweets.clear(); 
    }

}
