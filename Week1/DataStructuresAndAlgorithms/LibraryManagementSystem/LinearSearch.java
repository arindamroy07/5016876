package Week1.DataStructuresAndAlgorithms.LibraryManagementSystem;

public class LinearSearch {
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
}
