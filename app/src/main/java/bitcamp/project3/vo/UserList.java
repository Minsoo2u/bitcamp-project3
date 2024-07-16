package bitcamp.project3.vo;


import bitcamp.util.Print;
import java.util.LinkedList;

public class UserList<E> extends LinkedList<E> {
  Print print = new Print();

  public E userByID(int id) {
    for (E user : this) {
      if (((User)user).getNo() == id) {
        return user;
      }
    }
    return null;
  }

  public int indexByID(int id) {
    for (int i = 0; i < this.size(); i++) {
      if (((User) this.get(i)).getNo() == id) {
        return i;
      }
    }
    return -1;
  }

  public void printUserListByNo() {
    System.out.printf("ID | %s | %s\n",
        print.printFittedString(User.MAX_NAME_LENGTH, "이름"),
        print.printFittedString(User.MAX_CONTACT_LENGTH, "연락처"));
    System.out.println("---------------------------------------------------------------------------");

    for (int i = 0; i < this.size(); i++) {
      User user = (User) this.get(i);

      int no = user.getNo();
      String userName = print.printFittedString(User.MAX_NAME_LENGTH, user.getName());
      String contact = print.printFittedString(User.MAX_CONTACT_LENGTH, user.getContact());
      System.out.printf("%02d | %s | %s \n", no, userName, contact);
    }
  }

  public void printUserListByName() {
    System.out.printf("%s | ID | %s\n",
        print.printFittedString(User.MAX_NAME_LENGTH, "이름"),
        print.printFittedString(User.MAX_CONTACT_LENGTH, "연락처"));
    System.out.println("--------------------------");

    for (int i = 0; i < this.size(); i++) {
      User user = (User) this.get(i);

      int no = user.getNo();
      String userName = print.printFittedString(User.MAX_NAME_LENGTH, user.getName());
      String contact = print.printFittedString(User.MAX_CONTACT_LENGTH, user.getContact());
      System.out.printf("%s | %02d | %s \n", userName, no, contact);
    }
  }


}
