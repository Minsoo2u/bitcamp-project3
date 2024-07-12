package bitcamp.project3.util;

import bitcamp.project3.vo.Book;
import java.util.LinkedList;

public class BookList<E> extends LinkedList<E> {

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
    System.out.println("ISBN | 제목 | 저자 | 출판년도");
    for (int i = 0; i < this.size(); i++) {
      Book book = (Book) this.get(i);
      int isbnNo = book.getISBN();
      String title = book.getTitle();
      String author = book.getAuthor();
      int publishYear = book.getPublishYear();

      System.out.printf("%d | %s | %s | %d \n", isbnNo, title, author, publishYear);

    }
  }
}
