package bitcamp.project3.vo;

import java.time.LocalDate;

public class Rent {
    private User user;
    private Book book;
    private LocalDate startDate;
    private int period;

    public Rent(User user, Book book, LocalDate startDate, int period) {
        this.user = user;
        this.book = book;
        this.startDate = startDate;
        this.period = period;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

}
