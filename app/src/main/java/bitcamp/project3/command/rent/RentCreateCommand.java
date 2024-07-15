package bitcamp.project3.command.rent;

import bitcamp.project3.command.Command;
import bitcamp.project3.command.CrudCommand;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.BookList;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.RentList;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RentCreateCommand implements Command {

  private String menuTitle = "대출 관리";
  private RentList<Rent> rentList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"도서 대출", "도서 반납", "대출 조회", "대출 수정", "이전"};
  private Map<String, CrudCommand> crudMap = new HashMap<>();
  private UserList<User> userList;
  private BookList<Book> bookList;
  private Print print = new Print();

  public RentCreateCommand(RentList<Rent> rentList, UserList<User> userList, BookList<Book> bookList) {
    this.rentList = rentList;
    this.bookList = bookList;
    this.userList = userList;
  }

  @Override
  public void execute(String title) {
    Rent rent = new Rent();

    // 대여 회원 입력
    User user;
    while (true) {
      userList.printUserListByNo();
      int userNo = prompt.inputInt("사용자 No 입력 [0 = 취소] >>");
      if (userNo == 0) {
        print.printSystem("입력을 취소하였습니다.");
        return;
      }
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
      int bookNo = prompt.inputInt("책 No 입력 [0 = 취소] >>");
      if (bookNo == 0) {
        print.printSystem("입력을 취소하였습니다.");
        return;
      }
      book = bookList.bookByISBN(bookNo);

      if (book.isBorrowed()){
        System.out.println("대출 중입니다.");
      } else if (book == null) {
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
      period = prompt.inputInt("대여 일수 입력 [0 = 취소] >>");

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

    book.setBorrowed(true);
  }
  }