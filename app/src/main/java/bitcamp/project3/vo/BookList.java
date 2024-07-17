package bitcamp.project3.vo;

import bitcamp.util.Print;
import java.util.LinkedList;

public class BookList<E> extends LinkedList<E> {
  Print print = new Print();

  public Book bookByISBN(int ISBN) {
    for (E e : this) {
      if (((Book) e).getISBN() == ISBN) {
        return (Book) e;
      }
    }
    return null;
  }

  public int indexByISBN(int ISBN) {
    for (int i = 0; i < this.size(); i++) {
      if (((Book) this.get(i)).getISBN() == ISBN) {
        return i;
      }
    }
    return -1;
  }

  public void printBookListByNo() {
    System.out.printf("ISBN | %s | %s | 출판년도 | 재고 | 대출 가능 여부 \n",
        print.printFittedString(Book.MAX_TITLE_LENGTH, "제목"),
        print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, "저자"));
    System.out.println("--------------------------------------------------------------------------");

    for (int i = 0; i < this.size(); i++) {
      Book book = (Book) this.get(i);
      int isbnNo = book.getISBN();
      String title = print.printFittedString(Book.MAX_TITLE_LENGTH, book.getTitle());
      String author = print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, book.getAuthor());
      int publishYear = book.getPublishYear();
      int amount = book.getAmount();
      String isBorrowed = book.borrowable() ? "Yes" : "No";

      System.out.printf(" %02d  | %s | %s |   %d   |  %02d  |      %s \n", isbnNo, title, author, publishYear, amount,  isBorrowed);
    }
  }

  public void printBookListByTitle() {
    System.out.printf("%s | ISBN | %s | 출판년도 | 재고 | 대출 가능 여부 \n",
        print.printFittedString(Book.MAX_TITLE_LENGTH, "제목"),
        print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, "저자"));
    System.out.println("--------------------------------------------------------------------------");

    for (int i = 0; i < this.size(); i++) {
      Book book = (Book) this.get(i);
      int isbnNo = book.getISBN();
      String title = print.printFittedString(Book.MAX_TITLE_LENGTH, book.getTitle());
      String author = print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, book.getAuthor());
      int publishYear = book.getPublishYear();
      int amount = book.getAmount();
      String isBorrowed = book.borrowable() ? "Yes" : "No";

      System.out.printf("%s |  %02d  | %s |   %d   |  %02d  |      %s \n", title, isbnNo, author, publishYear, amount, isBorrowed);

    }
  }
}
