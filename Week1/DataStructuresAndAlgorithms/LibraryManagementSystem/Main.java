package Week1.DataStructuresAndAlgorithms.LibraryManagementSystem;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book(1, "Book 1", "Author 1");
        Book book2 = new Book(2, "Book 2", "Author 2");
        Book book3 = new Book(3, "Book 3", "Author 3");
        Book book4 = new Book(4, "Book 4", "Author 4");
        Book book5 = new Book(5, "Book 5", "Author 5");

        // for linear search
        Book[] booksForLinearSearch = {book2, book5, book4, book1, book3};
        
        Book resultLinearSearch = LinearSearch.linearSearch(booksForLinearSearch, "Book 3");
        if (resultLinearSearch != null) {
            System.out.println("Linear Search found: " + resultLinearSearch.getTitle());
        } else {
            System.out.println("Linear Search: Book not found");
        }

        // for binary search
        Book[] booksForBinarySearch = {book1, book2, book3, book4, book5};
        
        Book resultBinarySearch = Binarysearch.binarySearch(booksForBinarySearch, "Book 4");
        if (resultBinarySearch != null) {
            System.out.println("Binary Search found: " + resultBinarySearch.getTitle());
        } else {
            System.out.println("Binary Search: Book not found");
        }
    }
}

// Compare the time complexity of linear and binary search.
// Linear: O(n)
// Binary: O(logn)

// Discuss when to use each algorithm based on the data set size and order.
// Linear: use when the dataset is small and can be unsorted
// Binary: Use when the dataset is large and sorted
