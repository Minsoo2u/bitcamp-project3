package bitcamp.project3.command;

import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.util.RentList;
import bitcamp.project3.util.UserList;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.User;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RentCommand extends AbstractCommand {

  private String menuTitle = "대출 관리";
  private RentList<Rent> rentList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"도서 대출", "도서 반납", "대출 조회", "대출 수정", "이전"};
  private Map<String, CrudCommand> crudMap = new HashMap<>();
  private UserList<User> userList;
  private BookList<Book> bookList;
  private Print print = new Print();

  public RentCommand(RentList<Rent> rentList, UserList<User> userList, BookList<Book> bookList) {
    this.rentList = rentList;
    this.bookList = bookList;
    this.userList = userList;
    crudMap.put("도서 대출", new CommandFunc()::create);
    crudMap.put("도서 반납", new CommandFunc()::delete);
    crudMap.put("대출 조회", new CommandFunc()::read);
    crudMap.put("대출 수정", new CommandFunc()::update);
  }

  @Override
  public void execute(Stack<String> menuPath) {
    while (true) {
      print.printTitle(menuTitle);
      print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, 4, "%s >>", getMenuPath(menuPath));

      if (menuNo == 0) {
        menuPath.pop();
        return;
      }
      CrudCommand command = crudMap.get(getMenuTitle(menuNo, menus));
      command.execute();
    }
  }

  private class CommandFunc {
    void create() {
      Rent rent = new Rent();

      // 대여 회원 입력
      User user;
      while (true) {
        userList.printUserListByNo();
        int userNo = prompt.inputInt("사용자 No 입력 >>");
        user = userList.userByNo(userNo);

        if (user == null) {
          System.out.println("잘못된 번호입니다.");
        } else {
          break;
        }
      }

      // 대여할 책 입력
      Book book;
      while(true) {
        bookList.printBookListByNo();
        int bookNo = prompt.inputInt("책 No 입력 >>");
        book = bookList.bookByISBN(bookNo);

        if (book == null) {
          System.out.println("잘못된 번호입니다.");
        } else {
          break;
        }
      }

      // 대여일 = 오늘
      LocalDate startDate = LocalDate.now();

      // 대여 기간 입력 (대여일 = 오늘)
      int period;
      while(true) {
        period = prompt.inputInt("대여 일수 입력 >>");

        if (period > 30) {
          print.printSystem("한 달 이상은 대여할 수 없습니다.");
        } else {
          break;
        }
      }

      rent.setUser(user);
      rent.setBook(book);
      rent.setStartDate(startDate);
      rent.setPeriod(period);

      rentList.add(rent);
    }

    void read() {
      rentList.printRentListByNo();
    }

    void update() {
      Rent rent;
      while(true) {
        rentList.printRentListByNo();

        int rentNo = prompt.inputInt("수정할 대여 정보 No [0 = 종료] >>");
        if (rentNo == 0) {
          System.out.println("입력을 종료합니다.");
          return;
        }
        rent = rentList.rentByNo(rentNo);

        if (rent == null) {
          System.out.println("존재하지 않는 대여 정보입니다.");
        } else {
          break;
        }
      }

      // 대여 회원 입력
      User user;
      while (true) {
        userList.printUserListByNo();
        int userNo = prompt.inputInt("사용자 No 입력 (%s) >>", rent.getUser().getName());
        user = userList.userByNo(userNo);

        if (user == null) {
          System.out.println("잘못된 번호입니다.");
        } else {
          break;
        }
      }

      // 대여할 책 입력
      Book book;
      while(true) {
        bookList.printBookListByNo();
        int bookNo = prompt.inputInt("책 No 입력 (%s) >>", rent.getBook().getTitle());
        book = bookList.bookByISBN(bookNo);

        if (book == null) {
          System.out.println("잘못된 번호입니다.");
        } else {
          break;
        }
      }

      // 대여 기간 입력 (대여일 = 오늘)
      int period;
      while(true) {
        System.out.println("현재 대여 정보");
        System.out.printf("대여 시작일 : %s\n", rent.getStartDate());
        System.out.printf("대여 종료일 : %s\n", rent.getEndDate());
        period = prompt.inputInt("대여 일수 (%d) 입력 >>", rent.getPeriod());

        if (period > 30) {
          print.printSystem("한 달 이상은 대여할 수 없습니다.");
        } else {
          break;
        }
      }

      rent.setUser(user);
      rent.setBook(book);
      rent.setPeriod(period);
    }

    void delete() {
      int rentNo = prompt.inputInt("반납할 대여 정보 No [0 = 종료] >>");

      if (rentNo == 0) {
        return;
      }
      Rent rent = rentList.rentByNo(rentNo);
      rentList.remove(rent);
    }
  }

}
