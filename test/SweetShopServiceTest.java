package test;

import model.Sweet;
import service.SweetShopService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
