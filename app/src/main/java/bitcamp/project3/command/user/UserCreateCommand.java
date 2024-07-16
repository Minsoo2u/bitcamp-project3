package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.PromptLibrary;

public class UserCreateCommand implements Command {

  private UserList<User> userList;
  private PromptLibrary prompt = new PromptLibrary();

  public UserCreateCommand(UserList<User> list) {
    this.userList = list;
  }

  public void execute(String title) {
    User user = new User();

    user.setName(prompt.input("이름 >>"));
    user.setContact(prompt.input("연락처 >>"));

    userList.add(user);
    System.out.println("등록되었습니다.");
  }
}
