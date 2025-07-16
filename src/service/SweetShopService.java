package service;

import model.Sweet;
import exception.SweetNotFoundException;

import java.util.*;

public class SweetShopService {
    private final List<Sweet> sweets = new ArrayList<>();

    public void addSweet(Sweet sweet) {
        for (Sweet s : sweets) {
            if (s.getId() == sweet.getId()) {
                throw new IllegalArgumentException("Sweet ID must be unique.");
            }
        }
        sweets.add(sweet);
    }

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
}
