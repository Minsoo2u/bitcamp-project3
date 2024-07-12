package bitcamp.project3;

import bitcamp.project3.command.AbstractCommand;
import bitcamp.project3.command.BookCommand;
import bitcamp.project3.command.Command;
import bitcamp.project3.command.RentCommand;
import bitcamp.project3.command.UserCommand;
import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.util.RentList;
import bitcamp.project3.util.UserList;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.CommandMap;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.User;
import java.util.Stack;

public class App {

  private String[] mainMenus = {"대출 관리", "도서 관리", "사용자 관리", "종료"};
  private String menuTitle = "메인 메뉴";
  private Stack<String> menuPath = new Stack<>();
  private CommandMap<String, Command> commandMap = new CommandMap<>();
  private PromptLibrary prompt = new PromptLibrary();

  App() {
    UserList<User> userList = new UserList<>();
    BookList<Book> bookList = new BookList<>();
    RentList<Rent> rentList = new RentList<>();

    commandMap.put("대출 관리", new RentCommand(rentList));
    commandMap.put("도서 관리", new BookCommand(bookList));
    commandMap.put("사용자 관리", new UserCommand(userList));
  }

  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    menuPath.push("메인");

    while(true) {

      Print.printTitle(menuTitle);
      Print.printMenus(mainMenus);
      int menuNo = prompt.inputIntWithRange(0, mainMenus.length - 1, "%s>>", AbstractCommand.getMenuPath(menuPath));

      commandMap.commandExecute(AbstractCommand.getMenuTitle(menuNo, mainMenus), menuPath);

      if (menuNo == 0) {
        System.out.println("프로그램을 종료합니다.");
        prompt.close();
        return;
      }
    }
  }


}// Class HomePage END

