package bitcamp.project3;

import bitcamp.project3.command.AbstractCommand;
import bitcamp.project3.command.BookCommand;
import bitcamp.project3.command.Command;
import bitcamp.project3.command.RentCommand;
import bitcamp.project3.command.UserCommand;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class App {

  String[] mainMenus = {"대출 관리", "도서 관리", "사용자 관리", "종료"};
  String menuTitle = "메인 메뉴";
  Stack menuPath = new Stack();
  AbstractCommand abstractCommand;

  Map<String, Command> commandMap = new HashMap<>();
  PromptLibrary prompt;

  App() {
    List<User> userList = new LinkedList<>();
    List<Book> bookList = new LinkedList<>();
    List<Rent> rentList = new LinkedList<>();

    commandMap.put("대출 관리", new RentCommand());
    commandMap.put("도서 관리", new BookCommand());
    commandMap.put("사용자 관리", new UserCommand());
  }

  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    Print.printProgramTitle();
    Print.printTitle(menuTitle);
    Print.printMenus(mainMenus);

    prompt.inputIntWithRange(0, mainMenus.length - 1, "%s>>",
        abstractCommand.getMenuPath(menuPath));
  }


}// Class HomePage END
