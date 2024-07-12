package bitcamp.project3.vo;

import java.time.LocalDate;

public class Rent {
    public static int seqNo;

    private int no;
    private User user;
    private Book book;
    private LocalDate startDate;
    private int period;

    public Rent() {
        this.no = ++seqNo;
    }

    public Rent(User user, Book book, LocalDate startDate, int period) {
        this.no = ++seqNo;
        this.user = user;
        this.book = book;
        this.startDate = startDate;
        this.period = period;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
