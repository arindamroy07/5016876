package Week1.DataStructuresAndAlgorithms.ECommercePlatform;

public class Main {
    public static void main(String[] args) {
        
        Product product1 = new Product(1, "Product A", "Category A");
        Product product2 = new Product(2, "Product B", "Category B");
        Product product3 = new Product(3, "Product C", "Category C");
        Product product4 = new Product(4, "Product D", "Category D");
        Product product5 = new Product(5, "Product E", "Category E");

        // for linear search
        Product[] productsForLinearSearch = {product3, product5, product2, product1, product4};
        
        Product resultLinearSearch = LinearSearch.linearSearch(productsForLinearSearch, 5);
        if (resultLinearSearch != null) {
            System.out.println("Linear Search found: " + resultLinearSearch.getProductName());
        } else {
            System.out.println("Linear Search: Product not found");
        }

        // for binary search
        Product[] productsForBinarySearch = {product1, product2, product3, product4, product5};

        Product resultBinarySearch = BinarySearch.binarySearch(productsForBinarySearch, 3);
        if (resultBinarySearch != null) {
            System.out.println("Binary Search found: " + resultBinarySearch.getProductName());
        } else {
            System.out.println("Binary Search: Product not found");
        }
    }
}


// Compare the time complexity of linear and binary search algorithms.
// linear: searches sequentially thus time complexity O(n)
// binary: uses divide and conquer approach thus time complexity O(log n)

// Discuss which algorithm is more suitable for your platform and why.
// linear: suitable for small datasets, can be unsorted array
// binary: requires a sorted list, efficient for large datasets