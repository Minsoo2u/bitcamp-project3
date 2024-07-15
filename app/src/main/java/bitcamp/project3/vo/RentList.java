package bitcamp.project3.vo;

import bitcamp.util.Print;
import java.time.LocalDate;
import java.util.LinkedList;

public class RentList<E> extends LinkedList<E> {
  Print print = new Print();

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

  public void printRentListByNo() {
    System.out.printf("No | %s | %s | 대여  시작 | 기간 | 대여  종료\n",
        print.printFittedString(User.MAX_NAME_LENGTH, "사용자"),
        print.printFittedString(Book.MAX_TITLE_LENGTH, "책"));
    System.out.println("---------------------------------------------------------------------------");

    for (int i = 0; i < this.size(); i++) {
      Rent rent = (Rent) this.get(i);
      int no = rent.getNo();
      String name = print.printFittedString(User.MAX_NAME_LENGTH, rent.getUser().getName());
      String title = print.printFittedString(Book.MAX_TITLE_LENGTH, rent.getBook().getTitle());
      LocalDate startDate = rent.getStartDate();
      int period = rent.getPeriod();
      LocalDate endDate = rent.getEndDate();

      System.out.printf("%02d | %s | %s | %s |  %02d  | %s\n", no, name, title, startDate, period, endDate);
    }
  }
}
