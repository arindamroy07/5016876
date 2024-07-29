package Week1.DataStructuresAndAlgorithms.ECommercePlatform;

// Explain Big O notation and how it helps in analyzing algorithms.
// It helps analyze algorithm's time or space requirements with respect to input size.

// Describe the best, average, and worst-case scenarios for search operations.
// Best: Minimum time an algorithm takes for all inputs
// Average: Expected time for over all possible inputs
// Worst: Maximum time 

public class Product {
    private int productId;
    private String productName;
    private String category;
    
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
