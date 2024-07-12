package bitcamp.project3.util;


import bitcamp.project3.vo.User;
import java.util.LinkedList;

public class UserList<E> extends LinkedList<E> {

  public User userByNo(int no) {
    for (E e : this) {
      if (((User) e).getNo() == no) {
        return (User) e;
      }
    }
    return null;
  }

  public int indexByNo(int no) {
    for (int i = 0; i < this.size(); i++) {
      if (((User) this.get(i)).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

  public void printUserListByNo() {
    System.out.println("ID | 이름 | 연락처");
    for (int i = 0; i < this.size(); i++) {
      User user = (User) this.get(i);
      System.out.printf("%d | %s | %s \n", user.getNo(), user.getName(), user.getContact());
    }
  }


}
