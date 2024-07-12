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
}
