package Week1.DataStructuresAndAlgorithms.SortingCustomerOrders;

// Explain different sorting algorithms (Bubble Sort, Insertion Sort, Quick Sort, Merge Sort).
// Bubble: compares each pair of adjacent elements and swap if the order is wrong
// Insertion: sorts array one item at a time, with assumption that first element is already sorted
// Quick: divide-and-conquer algorithm that selects a ‘pivot’ element and partitions the array into two sub-arrays then sorts independently
// Merge: divide-and-conquer algorithm that divides the array into halves, sorts and then merges them back

public class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;
    
    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
