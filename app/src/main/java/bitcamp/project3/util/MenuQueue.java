package bitcamp.project3.util;

import java.util.LinkedList;
import java.util.Queue;

public class MenuQueue<E> extends LinkedList<E> implements Queue<E> {
  public String getMenuPath() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < this.size(); i++) {
      stringBuilder.append(this.get(i));
      if (i != this.size() - 1) {
        stringBuilder.append("/");
      }
    }
    return stringBuilder.toString();
  }
}
