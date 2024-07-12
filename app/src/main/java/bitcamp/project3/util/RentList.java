package bitcamp.project3.util;

import bitcamp.project3.vo.Rent;
import java.util.LinkedList;

public class RentList<E> extends LinkedList<E> {
  public Rent rentByNo(int no){
    for (int i = 0; i < this.size(); i++) {
      if (((Rent) this.get(i)).getNo() == no) {
        return (Rent) this.get(i);
      }
    }
    return null;
  }

  public int indexByNo(int no){
    for (int i = 0; i < this.size(); i++) {
      if (((Rent) this.get(i)).getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
