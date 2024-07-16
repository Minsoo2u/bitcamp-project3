package bitcamp.project3.command.rent;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.RentList;
import bitcamp.util.PromptLibrary;

public class RentDeleteCommand implements Command {

  private RentList<Rent> rentList;
  private PromptLibrary prompt = new PromptLibrary();

  public RentDeleteCommand(RentList<Rent> rentList) {
    this.rentList = rentList;
  }

  @Override
  public void execute(String title) {
    int rentNo = prompt.inputInt("반납할 대여 정보 No [0 = 종료] >>");

    if (rentNo == 0) {
      return;
    }
    Rent rent = rentList.rentByNo(rentNo);
    rentList.remove(rent);
  }
}
