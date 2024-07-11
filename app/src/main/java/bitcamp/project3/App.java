package bitcamp.project3;

import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import java.util.Stack;

public class App {

  String[] mainMenus = {"대출 관리", "도서 관리", "사용자 관리", "종료"};
  String menuTitle = "메인 메뉴";
  Stack menuPath = new Stack();

  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    Print.printProgramTitle();
    Print.printTitle(menuTitle);
    Print.printMenus(mainMenus);

    PromptLibrary.inputIntWithRange(0, mainMenus.length - 1, "%s>>", getMenuPath);

  }


}// Class HomePage END
