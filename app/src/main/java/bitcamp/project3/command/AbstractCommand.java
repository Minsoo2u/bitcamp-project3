package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.util.Print;

import java.util.Stack;

public abstract class AbstractCommand implements Command {

  public static String getMenuPath(Stack<String> menuPath) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < menuPath.size(); i++) {
      stringBuilder.append(menuPath.get(i));
      if (i != menuPath.size() - 1) {
        stringBuilder.append("/");
      }
    }

    return stringBuilder.toString();
  }

  public static String getMenuTitle(int menuNo, String[] menus) {
    if (menuNo == 0) {
      menuNo = menus.length - 1;
    } else {
      menuNo -= 1;
    }

    return menus[menuNo];
  }
}