package bitcamp.project3;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.project3.command.book.BookCreateCommand;
import bitcamp.project3.command.book.BookDeleteCommand;
import bitcamp.project3.command.book.BookReadCommand;
import bitcamp.project3.command.book.BookUpdateCommand;
import bitcamp.project3.command.rent.RentCreateCommand;
import bitcamp.project3.command.rent.RentDeleteCommand;
import bitcamp.project3.command.rent.RentReadCommand;
import bitcamp.project3.command.rent.RentUpdateCommand;
import bitcamp.project3.command.user.UserCreateCommand;
import bitcamp.project3.command.user.UserDeleteCommand;
import bitcamp.project3.command.user.UserReadCommand;
import bitcamp.project3.command.user.UserUpdateCommand;
import bitcamp.project3.vo.BookList;
import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;
import bitcamp.project3.vo.RentList;
import bitcamp.project3.vo.UserList;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.User;
import java.util.Stack;

public class App {
  private String menuTitle = "메인 메뉴";
  private Stack<String> menuPath = new Stack<>();
  private PromptLibrary prompt = new PromptLibrary();
  private Print print = new Print();
  private MenuGroup mainMenu = new MenuGroup("메인");

  App() {
    UserList<User> userList = new UserList<>();
    BookList<Book> bookList = new BookList<>();
    RentList<Rent> rentList = new RentList<>();

    mainMenu.setExitMenuTitle("종료");
    MenuGroup rent = new MenuGroup("대출 관리");
    MenuItem rentCreate = new MenuItem("도서 대출", new RentCreateCommand(rentList, userList, bookList));
    MenuItem rentDelete = new MenuItem("도서 반납", new RentDeleteCommand(rentList));
    MenuItem rentUpdate = new MenuItem("대출 수정", new RentUpdateCommand(rentList, userList, bookList));
    MenuItem rentRead = new MenuItem("대출 조회", new RentReadCommand(rentList));
    mainMenu.add(rent);
    rent.add(rentCreate);
    rent.add(rentDelete);
    rent.add(rentUpdate);
    rent.add(rentRead);

    MenuGroup book = new MenuGroup("도서 관리");
    MenuItem bookCreate = new MenuItem("도서 등록", new BookCreateCommand(bookList));
    MenuItem bookDelete = new MenuItem("도서 삭제", new BookDeleteCommand(bookList));
    MenuItem bookUpdate = new MenuItem("도서 수정", new BookUpdateCommand(bookList));
    MenuItem bookRead = new MenuItem("도서 조회", new BookReadCommand(bookList));
    mainMenu.add(book);
    book.add(bookCreate);
    book.add(bookDelete);
    book.add(bookUpdate);
    book.add(bookRead);

    MenuGroup user = new MenuGroup("회원 관리");
    MenuItem userCreate = new MenuItem("회원 정보 등록", new UserCreateCommand(userList));
    MenuItem userDelete = new MenuItem("회원 정보 삭제", new UserDeleteCommand(userList));
    MenuItem userUpdate = new MenuItem("회원 정보 수정", new UserUpdateCommand(userList));
    MenuItem userRead = new MenuItem("회원 조회", new UserReadCommand(userList));
    mainMenu.add(user);
    user.add(userCreate);
    user.add(userDelete);
    user.add(userUpdate);
    user.add(userRead);
  }

  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    menuPath.push("메인");
    while(true) {

      print.printTitle(menuTitle);

      try {
        mainMenu.execute();
      } catch (Exception e) {
        System.out.println("프로그램 오류!");
      }

      System.out.println("프로그램을 종료합니다.");
      prompt.close();
      return;
    }
  }
}// Class HomePage END
