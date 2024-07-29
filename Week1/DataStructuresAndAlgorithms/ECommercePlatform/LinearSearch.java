package Week1.DataStructuresAndAlgorithms.ECommercePlatform;

public class LinearSearch {
    public static Product linearSearch(Product[] products, int targetId) {
        for (Product product : products) {
            if (product.getProductId() == targetId) {
                return product;
            }
        }
        return null;
    }
}
