package Week1.DataStructuresAndAlgorithms.InventoryManagementSystem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        
        inventory.addProduct(new Product(1,"Product1", 10, 5.99));
        inventory.addProduct(new Product(2,"Product2", 20, 8.99));
        inventory.addProduct(new Product(3,"Product3", 30, 20.99));
        
        List<Product> products = inventory.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println();

        System.out.println("After updating and deleting..");
        inventory.updateProduct(2, new Product(2, "product2", 55, 17.44));

        inventory.deleteProduct(3);
        
        List<Product> newProducts = inventory.getAllProducts();
        for (Product product : newProducts) {
            System.out.println(product);
        }
    }
}

// Analyze the time complexity of each operation (add, update, delete) in your chosen data structure.
// Add: O(1)
// Update: O(n)
// Delete: O(n)

// Discuss how you can optimize these operations.
// By using appropriate data structures or and caching, indexing for faster lookups