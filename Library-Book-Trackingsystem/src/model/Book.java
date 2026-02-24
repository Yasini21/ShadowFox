package model;

public class Book {

    // WHY: represent columns of books table
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    // WHY: constructor to create Book objects easily
    public Book(int bookId, String title, String author, boolean isIssued) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = isIssued;
    }

    // WHY: getters to access private data safely
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }
}
