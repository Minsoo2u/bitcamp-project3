package bitcamp.project3.util;

import bitcamp.project3.vo.Book;
import java.util.LinkedList;

public class BookList<E> extends LinkedList<E> {
  Print print = new Print();

  public Book bookByISBN(int ISBN) {
    for (int i = 0; i < this.size(); i++) {
      if (((Book) this.get(i)).getISBN() == ISBN) {
        return (Book) this.get(i);
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
    System.out.printf("ISBN | %s | %s | 출판년도\n",
        print.printFittedString(Book.MAX_TITLE_LENGTH, "제목"),
        print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, "저자"));
    System.out.println("---------------------------------------------------");

    for (int i = 0; i < this.size(); i++) {
      Book book = (Book) this.get(i);
      int isbnNo = book.getISBN();
      String title = print.printFittedString(Book.MAX_TITLE_LENGTH, book.getTitle());
      String author = print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, book.getAuthor());
      int publishYear = book.getPublishYear();

      System.out.printf(" %02d  | %s | %s | %d \n", isbnNo, title, author, publishYear);

    }
  }

  public void printBookListByTitle() {
    System.out.printf("%s | ISBN | %s | 출판년도\n",
        print.printFittedString(Book.MAX_TITLE_LENGTH, "제목"),
        print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, "저자"));

    for (int i = 0; i < this.size(); i++) {
      Book book = (Book) this.get(i);
      int isbnNo = book.getISBN();
      String title = print.printFittedString(Book.MAX_TITLE_LENGTH, book.getTitle());
      String author = print.printFittedString(Book.MAX_AUTHOR_NAME_LENGTH, book.getAuthor());
      int publishYear = book.getPublishYear();

      System.out.printf("%s |  %02d  | %s | %d \n", title, isbnNo, author, publishYear);

    }
  }
}
