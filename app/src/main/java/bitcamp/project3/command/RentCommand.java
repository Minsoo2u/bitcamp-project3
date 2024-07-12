package bitcamp.project3.command;

import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.util.RentList;
import bitcamp.project3.util.UserList;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RentCommand extends AbstractCommand {

  private String menuTitle = "대출 관리";
  private RentList<Rent> rentList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"도서 대출", "도서 반납", "대출 조회", "대출 수정", "이전"};
  private Stack<String> menuPath = new Stack<>();
  private Map<String, CrudCommand> crudMap = new HashMap<>();
  private UserList<User> userList;
  private BookList<Book> bookList;

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
      Print.printTitle(menuTitle);
      Print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, 4, "%s >>", AbstractCommand.getMenuPath(menuPath));

      CrudCommand command = crudMap.get(getMenuTitle(menuNo, menus));
      command.execute();


    }
  }

  public class CommandFunc {

    void create() {
      while (true) {
        Rent rent = new Rent();

        userList.printUserListByNo();
        int userNo = prompt.inputInt("사용자 No 입력 >>");
        User user = userList.userByNo(userNo);

        if (user == null) {
          System.out.println("잘못된 번호입니다.");
          continue;
        }

        bookList.printBookListByNo();
        int bookNo = prompt.inputInt("책 No 입력 >>");
        Book book = bookList.bookByISBN(bookNo);

        if (book == null) {
          System.out.println("잘못된 번호입니다.");
          continue;
        }

        rent.setUser(user);
        rent.setBook(book);

      }
    }

    void read() {

    }

    void update() {

    }

    void delete() {

    }
  }

  @FunctionalInterface
  interface CrudCommand {

    void execute();
  }

}
