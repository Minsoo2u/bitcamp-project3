package bitcamp.project3.command;

import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;
import bitcamp.project3.vo.UserList;
import bitcamp.project3.vo.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class UserCommand extends AbstractCommand {

  private String menuTitle = "회원 관리";
  private UserList<User> userList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"회원 등록", "회원 수정", "회원 조회", "회원 삭제", "이전"};
  private Map<String, CrudCommand> crudMap = new HashMap<>();
  private Print print = new Print();

  public UserCommand(UserList<User> list) {
    this.userList = list;
    crudMap.put("회원 등록", new CommandFunc()::create);
    crudMap.put("회원 수정", new CommandFunc()::update);
    crudMap.put("회원 조회", new CommandFunc()::read);
    crudMap.put("회원 삭제", new CommandFunc()::delete);
  }

  public void execute(Stack<String> menuPath) {
    menuPath.push("회원 관리");
    while (true) {
      print.printTitle(menuTitle);
      print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, 4, "%s >>", getMenuPath(menuPath));

      if (menuNo == 0) {
        menuPath.pop();
        return;
      }
      CrudCommand command = crudMap.get(getMenuTitle(menuNo, menus));
      command.execute();

    }
  }

  public class CommandFunc {
    protected void create() {
      User user = new User();

      user.setName(prompt.input("이름 >>"));
      user.setContact(prompt.input("연락처 >>"));

      userList.add(user);
      System.out.println("등록되었습니다.");
    }

    protected void update() {
      userList.printUserListByNo();

      int userID = prompt.inputInt("사용자 ID 선택 >>");
      User user = userByID(userID);

      if (user == null) {
        System.out.println("해당 ID의 사용자가 없습니다.");
        return;
      }

      user.setName(prompt.input("이름 >>"));
      user.setContact(prompt.input("연락처 >>"));

      System.out.println("수정되었습니다.");
    }

    protected void read() {
      String[] menus = {"번호순 조회", "이름순 조회", "이전"};

      print.printTitle("사용자 조회");
      print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, menus.length - 1, "메뉴 선택 >>");

      switch (menuNo) {
        case 1:
          userList.printUserListByNo();
          break;

        case 2:
          userList.printUserListByName();
          break;
      }
    }

    protected void delete() {
      print.printTitle("사용자 삭제");

      userList.printUserListByNo();

      while (true) {
        int userID = prompt.inputInt("사용자 ID 입력 >>");
        int indexNo = indexByID(userID);

        if (indexNo == -1) {
          System.out.println("입력한 번호는 유효하지 않은 번호입니다.");
        } else {
          userList.remove(indexNo);
          System.out.println("삭제되었습니다.");
          return;
        }
      }
    }
  }

  private User userByID(int id) {
    for (User user : userList) {
      if (user.getNo() == id) {
        return user;
      }
    }
    return null;
  }

  private int indexByID(int id) {
    for (int i = 0; i < userList.size(); i++) {
      if (userList.get(i).getNo() == id) {
        return i;
      }
    }
    return -1;
  }
}
