package bitcamp.menu;

import bitcamp.util.PromptLibrary;
import java.util.ArrayList;
import java.util.Stack;
import bitcamp.util.Print;

public class MenuGroup extends AbstractMenu {
  private PromptLibrary prompt = new PromptLibrary();

  private MenuGroup parent;
  private Stack<String> menuPath;
  private ArrayList<Menu> children = new ArrayList<>();
  private String exitMenuTitle = "이전";

  public MenuGroup(String title) {
    super(title);
    menuPath = new Stack<>();
  }

  @Override
  public void execute() {
    menuPath.push(title);

    printMenus();

    while(true) {
      String command = prompt.input("%s >>", getMenuPathTitle());
      if (command.equals("menu")) {
        printMenus();
        continue;
      } else if (command.equals("0")) {
        menuPath.pop();
        return;
      }

      try {
        int menuNo = Integer.parseInt(command);
        Menu menu = getMenu(menuNo - 1);

        if (menu == null) {
          System.out.println("유효하지 않은 메뉴입니다.");
          continue;
        }
        menu.execute();

      } catch (NumberFormatException e) {
        System.out.println("숫자로 메뉴 번호를 입력하세요.");
      }
    }
  }

  private void printMenus() {
    int i = 1;
    for (Menu menu : children) {
      System.out.println(i + ". " + menu.getTitle());
    }
    System.out.println("0. " + exitMenuTitle);
  }

  private String getMenuPathTitle() {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < menuPath.size(); i++) {
      stringBuilder.append(menuPath.get(i));

      if (i != menuPath.size() - 1) {
        stringBuilder.append("/");
      }
    }

    return stringBuilder.toString();
  }

  private Menu getMenu(int index) {
    try {
      return children.get(index);
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  private void setParent(MenuGroup parent) {
    this.parent = parent;
    this.menuPath = parent.menuPath;
  }

  public void add(Menu child) {
    if (child instanceof MenuGroup) {
      ((MenuGroup) child).setParent(this);
    }
    children.add(child);
  }

  public void setExitMenuTitle(String title) {
    exitMenuTitle = title;
  }

  public void remove(Menu child) {
    children.remove(child);
  }

  public int countMenus() {
    return children.size();
  }



}
