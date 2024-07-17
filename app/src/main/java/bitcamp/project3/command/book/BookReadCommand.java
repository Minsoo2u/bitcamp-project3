package bitcamp.project3.command.book;

import bitcamp.project3.command.Command;
import bitcamp.project3.command.CrudCommand;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.BookList;
import bitcamp.util.Print;
import bitcamp.util.PromptLibrary;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BookReadCommand implements Command {

  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();
  private Print print = new Print();

  public BookReadCommand(BookList<Book> bookList) {
    this.bookList = bookList;
  }

  public void execute(String title) {
    String[] menus = {"번호순 조회", "제목순 조회", "이전"};

    print.printTitle("도서 조회");
    print.printMenus(menus);

    int menuNo = prompt.inputIntWithRange(0, menus.length - 1, "메뉴 선택 >>");

    switch (menuNo) {
      case 1:
        bookList.printBookListByNo();
        break;

      case 2:
        bookList.printBookListByTitle();
        break;

      case 0:
        break;
    }
  }
}

