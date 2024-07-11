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

    public int getPeriod() {
        return period;
    }

    public LocalDate getReturnDate() {
        return startDate.plusDays(period);
    }

    @Override
    public String toString() {
        return "Rent{" +
                "사용자=" + user +
                ", 도서=" + book +
                ", 대출 시작일=" + startDate +
                ", 대출 기간(일수)=" + period +
                ", 반납일=" + getReturnDate() +
                '}';
    }
}
