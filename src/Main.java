import model.Sweet;
import service.SweetShopService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SweetShopService shop = new SweetShopService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Sweet Shop Management System");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Sweet");
            System.out.println("2. View All Sweets");
            System.out.println("3. Delete Sweet");
            System.out.println("4. Search by Name");
            System.out.println("5. Search by Category");
            System.out.println("6. Search by Price Range");
            System.out.println("7. Purchase Sweet");
            System.out.println("8. Restock Sweet");
            System.out.println("0. Exit");
            System.out.println("9. Sort Sweets");


            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Category: ");
                        String category = scanner.nextLine();
                        System.out.print("Price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Quantity: ");
                        int qty = scanner.nextInt();
                        shop.addSweet(new Sweet(id, name, category, price, qty));
                        System.out.println("Sweet added!");
                    }
                    case 2 -> {
                        List<Sweet> all = shop.getAllSweets();
                        if (all.isEmpty()) System.out.println("No sweets found.");
                        else all.forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.print("Enter ID to delete: ");
                        int id = scanner.nextInt();
                        shop.deleteSweet(id);
                        System.out.println("Sweet deleted.");
                    }
                    case 4 -> {
                        System.out.print("Enter name to search: ");
                        String name = scanner.nextLine();
                        shop.searchByName(name).forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.print("Enter category to search: ");
                        String category = scanner.nextLine();
                        shop.searchByCategory(category).forEach(System.out::println);
                    }
                    case 6 -> {
                        System.out.print("Min price: ");
                        double min = scanner.nextDouble();
                        System.out.print("Max price: ");
                        double max = scanner.nextDouble();
                        shop.searchByPriceRange(min, max).forEach(System.out::println);
                    }
                    case 7 -> {
                        System.out.print("Enter Sweet ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter quantity to purchase: ");
                        int qty = scanner.nextInt();
                        shop.purchaseSweet(id, qty);
                        System.out.println("Purchase successful!");
                    }
                    case 8 -> {
                        System.out.print("Enter Sweet ID: ");
                        int id = scanner.nextInt();
                        System.out.print("Enter quantity to restock: ");
                        int qty = scanner.nextInt();
                        shop.restockSweet(id, qty);
                        System.out.println("Restocked successfully!");
                    }
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting. Thank you!");
                    }
                    case 9 -> {
                        System.out.print("Sort by (name/category/price/quantity): ");
                        String field = scanner.nextLine();
                        System.out.print("Ascending? (true/false): ");
                        boolean asc = scanner.nextBoolean();
                        scanner.nextLine(); // flush

                        List<Sweet> sorted = shop.sortSweets(field, asc);
                        sorted.forEach(System.out::println);
                    }

                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
