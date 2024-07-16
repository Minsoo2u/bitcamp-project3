package bitcamp.project3.command.rent;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.Rent;
import bitcamp.project3.vo.RentList;

public class RentReadCommand implements Command {

  private RentList<Rent> rentList;

  public RentReadCommand(RentList<Rent> rentList) {
    this.rentList = rentList;

  }

  @Override
  public void execute(String title) {
    rentList.printRentListByNo();
  }
}
