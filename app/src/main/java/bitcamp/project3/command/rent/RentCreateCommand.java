package bitcamp.project3.command.rent;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.BookList;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.RentList;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;
import java.time.LocalDate;

public class RentCreateCommand implements Command {

  private RentList<Rent> rentList;
  private PromptLibrary prompt = new PromptLibrary();
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
      int userID = prompt.inputInt("사용자 ID 입력 [0 = 취소] >>");
      if (userID == 0) {
        print.printSystem("입력을 취소하였습니다.");
        return;
      }
      user = userList.userByID(userID);

      if (!user.isBorrowable()){
        print.printSystem("더 이상 대여할 수 없습니다.");
        continue;
      }

      if (user == null) {
        print.printSystem("잘못된 번호입니다.");
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

      if (!book.borrowable()){
        print.printSystem("재고가 없습니다.");
      } else if (book == null) {
        print.printSystem("잘못된 번호입니다.");
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
    user.setRentLimit(user.getRentLimit() - 1);
    book.setAmount(book.getAmount() - 1);
  }
  }