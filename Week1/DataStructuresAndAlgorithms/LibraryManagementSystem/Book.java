package Week1.DataStructuresAndAlgorithms.LibraryManagementSystem;

// Explain linear search and binary search algorithms.
// Linear: It is a simple algorith which checks each element in the list sequentially until the desired element is found or the list ends
// Binary: It works on sorted lists. It repeatedly divides the search interval in half, comparing the target value to the middle element

public class Book {
    private int bookId;
    private String title;
    private String author;
    
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
