package test;

import model.Sweet;
import service.SweetShopService;

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
}
