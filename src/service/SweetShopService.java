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
}
