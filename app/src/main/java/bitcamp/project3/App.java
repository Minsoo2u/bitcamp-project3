package bitcamp.project3;

import bitcamp.project3.command.AbstractCommand;
import bitcamp.project3.command.BookCommand;
import bitcamp.project3.command.Command;
import bitcamp.project3.command.RentCommand;
import bitcamp.project3.command.UserCommand;
import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.util.UserList;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.CommandMap;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.User;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.checkerframework.checker.units.qual.A;

public class App {

  private String[] mainMenus = {"대출 관리", "도서 관리", "사용자 관리", "종료"};
  private String menuTitle = "메인 메뉴";
  private Stack<String> menuPath = new Stack<>();
  private CommandMap<String, Command> commandMap = new CommandMap<>();
  private PromptLibrary prompt = new PromptLibrary();

  App() {
    UserList<User> userList = new UserList<>();
    BookList<Book> bookList = new BookList<>();
    List<Rent> rentList = new LinkedList<>();

    commandMap.put("대출 관리", new RentCommand());
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

      commandMap.commandExecute(getMenuTitle(menuNo));

      if (menuNo == 0) {
        System.out.println("프로그램을 종료합니다.");
        prompt.close();
        return;
      }
    }
  }

  String getMenuTitle(int menuNo) {
    if (menuNo == 0) {
      menuNo = mainMenus.length - 1;
    } else {
      menuNo -= 1;
    }

    return mainMenus[menuNo];
  }

  void processMenu(int menuNo) {
    String menuTitle = getMenuTitle(menuNo);
    Command command = commandMap.get(menuTitle);

    if (command == null) {
      System.out.println("존재하지 않는 메뉴입니다.");
      return;
    }

    command.execute();
  }


}// Class HomePage END
