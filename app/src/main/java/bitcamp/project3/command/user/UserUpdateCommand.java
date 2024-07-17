package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.PromptLibrary;

public class UserUpdateCommand implements Command {

  private UserList<User> userList;
  private PromptLibrary prompt = new PromptLibrary();

  public UserUpdateCommand(UserList<User> list) {
    this.userList = list;
  }

  public void execute(String title) {
    userList.printUserListByNo();

    int userID = prompt.inputInt("사용자 ID 선택 >>");
    User user = userList.userByID(userID);

    if (user == null) {
      System.out.println("해당 ID의 사용자가 없습니다.");
      return;
    }

    user.setName(prompt.input("이름 (%s) >>", user.getName()));
    user.setContact(prompt.input("연락처 (%s) >>", user.getContact()));

    System.out.println("수정되었습니다.");
  }
}
