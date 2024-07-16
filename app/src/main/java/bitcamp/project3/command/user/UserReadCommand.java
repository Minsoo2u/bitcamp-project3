package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;

public class UserReadCommand implements Command {

  private UserList<User> userList;
  private PromptLibrary prompt = new PromptLibrary();
  private Print print = new Print();

  public UserReadCommand(UserList<User> list) {
    this.userList = list;
  }

  public void execute(String title) {
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
}
