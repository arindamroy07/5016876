package Week1.DataStructuresAndAlgorithms.SortingCustomerOrders;

public class BubbleSort {
    public static void bubbleSort(Order[] orders) {
        for (int i = orders.length - 1; i > 0 ; i--) {
            for (int j = 0 ; j < i; j++) {
                if (orders[j].getTotalPrice() < orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j+1];
                    orders[j+1] = temp;
                }
            }
        }
    }
}
