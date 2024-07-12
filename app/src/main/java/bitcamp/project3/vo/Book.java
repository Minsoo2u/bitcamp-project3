package bitcamp.project3.vo;

public class Book {
    private static int seqNo = 0;

    private String title;
    private String author;
    private int ISBN;
    private int publishYear;
    private boolean isBorrowed;

    public Book() {
        this.ISBN = ++seqNo;
    }

    public Book(String title, String author, int publishYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ++seqNo;
        this.publishYear = publishYear;
        this.isBorrowed = false;
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

    public int getISBN() {
        return ISBN;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "제목='" + title + '\'' +
                ", 저자='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", 출판년도=" + publishYear +
                ", 대출중=" + isBorrowed +
                '}';
    }
}
