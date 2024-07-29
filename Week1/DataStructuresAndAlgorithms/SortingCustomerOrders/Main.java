package Week1.DataStructuresAndAlgorithms.SortingCustomerOrders;

public class Main {
    public static void main(String[] args) {
        Order order1 = new Order(1, "Customer A", 759);
        Order order2 = new Order(2, "Customer B", 528);
        Order order3 = new Order(3, "Customer C", 1444);
        Order order4 = new Order(4, "Customer D", 224);
        Order order5 = new Order(5, "Customer E", 984);

        Order[] orders = {order1, order2, order3, order4, order5};

        System.out.println("Original Order:");
        for (Order original : orders)
        System.out.println(original);
        System.out.println();

        System.out.println("After bubble sort..");
        BubbleSort.bubbleSort(orders);
        for (Order bubble : orders)
        System.out.println(bubble);
        System.out.println();

        System.out.println("After quick sort..");
        QuickSort.quickSort(orders);
        for (Order quick : orders)
        System.out.println(quick);
    }
}

// Compare the performance (time complexity) of Bubble Sort and Quick Sort.
// Bubble: O(n^2)
// Quick: O(nlogn)

// Discuss why Quick Sort is generally preferred over Bubble Sort.
// Quick Sort is generally faster and suitable for large databases thus preferred over Bubble Sort
