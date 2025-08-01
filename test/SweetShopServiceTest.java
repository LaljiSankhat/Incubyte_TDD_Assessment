package test;

import model.Sweet;
import service.SweetShopService;
import exception.SweetNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.*;

public class SweetShopServiceTest {

    private SweetShopService shop;

    /**
     * Initializes a fresh SweetShopService instance with some default sweets.
     * 
     * This method runs before each individual test case.
     */
    @BeforeEach
    public void setUp() {
        shop = new SweetShopService();
        shop.addSweet(new Sweet(1001, "Kaju Katli", "Nut-Based", 50, 20));
        shop.addSweet(new Sweet(1002, "Gajar Halwa", "Vegetable-Based", 30, 15));
        shop.addSweet(new Sweet(1003, "Gulab Jamun", "Milk-Based", 10, 5));
    }

    /**
     * Clears the sweet shop inventory after each test.
     * 
     * Ensures no state is shared between test cases.
     */
    @AfterEach
    public void tearDown() {
        shop.clearAll();
    }

    @Test
    public void testAddSweetWithDuplicateIdShouldFail() {

        // Attempting to add a sweet with an ID that already exists (1001).
        // The shop already contains a sweet with ID 1001 from the setUp() method.

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shop.addSweet(new Sweet(1001, "Duplicate Sweet", "Milk-Based", 10, 5));
        });

        // Verifying that the correct exception message is returned
        assertEquals("Sweet ID must be unique.", exception.getMessage());
    }

    @Test
    public void testDeleteSweet() {
        // Delete the sweet with ID 1003 and verify that the list size is reduced by 1
        shop.deleteSweet(1003);
        List<Sweet> all = shop.getAllSweets();
        assertEquals(2, all.size());
    }

    @Test
    // Verifies that searchByName correctly returns sweets matching a partial,
    // case-insensitive name
    public void testSearchByName() {
        List<Sweet> result = shop.searchByName("jamun");
        assertEquals(1, result.size());
        assertEquals("Gulab Jamun", result.get(0).getName());
    }

    @Test
    // Verifies that searching sweets by category returns correct matches
    public void testSearchByCategory() {
        List<Sweet> result = shop.searchByCategory("Milk-Based");
        assertEquals(1, result.size());
    }

    @Test
    // Checks that sweets within a specified price range are correctly returned
    public void testSearchByPriceRange() {
        List<Sweet> result = shop.searchByPriceRange(10, 40);
        assertEquals(2, result.size());
    }

    @Test
    // Validates successful purchase of sweet and quantity update
    public void testPurchaseSweetSuccess() {
        shop.purchaseSweet(1003, 3);
        Sweet gulab = null;
        for (Sweet s : shop.getAllSweets()) {
            if (s.getId() == 1003) {
                gulab = s;
                break;
            }
        }
        assertNotNull(gulab);
        assertEquals(2, gulab.getQuantity());
    }

    
    @Test
    // Ensures an exception is thrown when purchasing more than available stock
    public void testPurchaseSweetFailIfNotEnoughStock() {
        assertThrows(IllegalArgumentException.class, () -> {
            shop.purchaseSweet(1003, 10);
        });
    }

    @Test
    // Confirms that restocking a sweet correctly increases its quantity
    public void testRestockSweet() {
        shop.restockSweet(1002, 5);
        Sweet sweet = null;
        for (Sweet s : shop.getAllSweets()) {
            if (s.getId() == 1002) {
                sweet = s;
                break;
            }
        }
        assertNotNull(sweet);
        assertEquals(20, sweet.getQuantity());
    }

    @Test
    // Ensures proper exception is thrown when attempting to delete a non-existing sweet
    public void testDeleteNonExistingSweet() {
        assertThrows(SweetNotFoundException.class, () -> {
            shop.deleteSweet(9999);
        });
    }

    @Test
    // Verifies that sweets are sorted by price in descending order
    public void testSortByPriceDescending() {
        List<Sweet> result = shop.sortSweets("price", false);
        assertEquals("Kaju Katli", result.get(0).getName()); // ₹50
        assertEquals("Gulab Jamun", result.get(2).getName()); // ₹10
    }
}
