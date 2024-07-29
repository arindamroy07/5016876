package Week1.DataStructuresAndAlgorithms.SortingCustomerOrders;

public class QuickSort {
    private static void swap(Order[] orders, int firstIndex, int secondIndex) {
        Order temp = orders[firstIndex];
        orders[firstIndex] = orders[secondIndex];
        orders[secondIndex] = temp;
    }

    private static int pivot(Order[] orders, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;
        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            if (orders[i].getTotalPrice() > orders[pivotIndex].getTotalPrice()) {
                swapIndex++;
                swap(orders, swapIndex, i);
            }
        }
        swap(orders, pivotIndex, swapIndex);
        return swapIndex;
    }

	public static void quickSortHelper(Order[] orders, int left, int right) {
        if (left < right) {
            int pivotIndex = pivot(orders, left, right);
            quickSortHelper(orders, left, pivotIndex - 1);
            quickSortHelper(orders, pivotIndex + 1, right);
        }
    }

    public static void quickSort(Order[] orders) {
        quickSortHelper(orders, 0, orders.length-1);
    }
}
