package bitcamp.project3;

import bitcamp.project3.util.Print;

public class App {

  static String[] mainMenus = {"대출 관리", "도서 관리", "사용자 관리", "종료"};
  static String menuTitle = "메인 메뉴";

  public static void main(String[] args) {
    Print.printProgramTitle();
    Print.printTitle(menuTitle);
    Print.printMenus(mainMenus);


  }


}// Class HomePage END
