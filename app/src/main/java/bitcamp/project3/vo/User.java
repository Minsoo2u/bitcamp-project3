package bitcamp.project3.vo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int seqNo = 0;

    private String name;
    private int userId;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.userId = ++seqNo;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

}
