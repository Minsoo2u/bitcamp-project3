package bitcamp.project3.vo;

public class Book {
    private static int seqNo = 0;
    public static final int MAX_TITLE_LENGTH = 20;
    public static final int MAX_AUTHOR_NAME_LENGTH = 10;

    private String title;
    private String author;
    private int ISBN;
    private int publishYear;
    private int amount;

    public Book() {
        this.ISBN = ++seqNo;
    }

    public Book(String title, String author, int publishYear, int amount) {
        this.title = title;
        this.author = author;
        this.ISBN = ++seqNo;
        this.publishYear = publishYear;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean borrowable() {
        return amount > 0;
    }


    @Override
    public String toString() {
        return "Book{" +
                "제목='" + title + '\'' +
                ", 저자='" + author + '\'' +
                ", ISBN=" + ISBN +
                ", 출판년도=" + publishYear +
                ", 대출중=" + borrowable() +
                '}';
    }
}
