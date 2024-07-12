package bitcamp.project3.command;

import bitcamp.project3.util.UserList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.vo.User;

import java.util.List;

public class UserCommand extends AbstractCommand {

  private String menuTitle = "사용자 관리";
  private UserList<User> userList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"등록", "수정", "조회", "삭제", "이전"};

  public UserCommand(UserList<User> list) {
    this.userList = list;
  }

  public void execute() {
    while(true) {
      Print.printTitle(menuTitle);
      Print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, 4, "메뉴 선택 >>");

      switch (menuNo) {
        case 1:
          this.createUser();
          break;
        case 2:
          this.updateUser();
          break;
        case 3:
          this.readUser();
          break;
        case 4:
          this.deleteUser();
          break;
        case 0:
          return;
      }
    }
  }

  protected void createUser() {
    User user = new User();

    user.setName(prompt.input("이름 >>"));
    user.setContact(prompt.input("연락처 >>"));

    userList.add(user);
    System.out.println("등록되었습니다.");
  }

  protected void updateUser() {
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

  protected void readUser() {
    String[] menus = {"번호순 조회", "이름순 조회", "이전"};

    Print.printTitle("사용자 조회");
    Print.printMenus(menus);

    int menuNo = prompt.inputIntWithRange(0, menus.length-1, "메뉴 선택 >>");

    switch(menuNo) {
      case 1:
        System.out.println("ID | 이름 | 연락처");
        for (int i = 0; i < userList.size(); i++) {
          User user = userList.get(i);
          System.out.printf("%d | %s | %s \n", user.getId(), user.getName(), user.getContact());
        }
        break;

      case 2:
        System.out.println("이름 | ID | 연락처");
        for (int i = 0; i < userList.size(); i++) {
          User user = userList.get(i);
          System.out.printf("%s | %d | %s \n", user.getName(), user.getId(), user.getContact());
        }
        break;
    }
  }

  protected void deleteUser() {
    Print.printTitle("사용자 삭제");

    System.out.println("ID | 이름 | 연락처");
    for (int i = 0; i < userList.size(); i++) {
      User user = userList.get(i);
      System.out.printf("%d | %s | %s \n", user.getId(), user.getName(), user.getContact());
    }

    while(true) {
      int userID = prompt.inputInt("사용자 ID 입력 >>");
      int indexNo = indexByID(userID);

      if (indexNo == -1){
        System.out.println("입력한 번호는 유효하지 않은 번호입니다.");
      } else {
        userList.remove(indexNo);
        System.out.println("삭제되었습니다.");
        return;
      }
    }
  }

  private User userByID(int id) {
    for (User user : userList) {
      if (user.getId() == id) {
        return user;
      }
    }
    return null;
  }

  private int indexByID(int id) {
    for (int i = 0; i < userList.size(); i++) {
      if (userList.get(i).getId() == id) {
        return i;
      }
    }
    return -1;
  }
}
