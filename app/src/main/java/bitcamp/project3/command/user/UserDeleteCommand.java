package bitcamp.project3.command.user;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;

public class UserDeleteCommand implements Command {

  private UserList<User> userList;
  private PromptLibrary prompt = new PromptLibrary();
  private Print print = new Print();

  public UserDeleteCommand(UserList<User> list) {
    this.userList = list;
  }

  public void execute(String title) {
    print.printTitle("사용자 삭제");

    userList.printUserListByNo();

    while (true) {
      int userID = prompt.inputInt("사용자 ID 입력 >>");
      int indexNo = userList.indexByID(userID);

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
