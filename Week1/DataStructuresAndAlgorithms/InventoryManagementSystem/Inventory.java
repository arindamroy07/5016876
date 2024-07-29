package Week1.DataStructuresAndAlgorithms.InventoryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public void updateProduct(int productId, Product product) {
        for (Product p : inventory) {
            if (p.getProductId() == productId) {
                p.setProductName(product.getProductName());
                p.setQuantity(product.getQuantity());
                p.setPrice(product.getPrice());
            }
        }
    }

    public void deleteProduct(int productId) {
        inventory.removeIf(p -> p.getProductId() == productId);
    }

    public List<Product> getAllProducts() {
        return inventory;
    }
}
