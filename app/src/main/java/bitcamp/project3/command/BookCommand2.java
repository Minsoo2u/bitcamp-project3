package bitcamp.project3.command;

import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.vo.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BookCommand2 extends AbstractCommand {

  private String menuTitle = "도서 관리";
  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"도서 등록", "도서 수정", "도서 조회", "도서 삭제", "이전"};
  private Map<String, CrudCommand> crudMap = new HashMap<>();
  private Print print = new Print();

  public BookCommand2(BookList<Book> bookList) {
    this.bookList = bookList;
    crudMap.put("도서 대출", new CommandFunc()::create);
    crudMap.put("도서 반납", new CommandFunc()::delete);
    crudMap.put("대출 조회", new CommandFunc()::read);
    crudMap.put("대출 수정", new CommandFunc()::update);
  }

  public void execute(Stack<String> menuPath) {
    menuPath.push("도서 관리");
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
      Book book = new Book();

      book.setTitle(prompt.input("책 제목 >>"));
      book.setAuthor(prompt.input("책 저자 >>"));
      book.setPublishYear(prompt.inputIntWithRange(1300, 2025, "출판년도 >>"));

      bookList.add(book);
      System.out.println("등록되었습니다.");
    }

    protected void update() {
      bookList.printBookListByNo();

      int isbnNo = prompt.inputInt("ISBN 번호 선택 >>");
      Book book = bookList.bookByISBN(isbnNo);

      book.setTitle(prompt.input("책 제목 (%s) >>", book.getTitle()));
      book.setAuthor(prompt.input("책 저자 (%s) >>", book.getAuthor()));
      book.setPublishYear(
          prompt.inputIntWithRange(1300, 2025, "출판년도 (%d) >>", book.getPublishYear()));

      System.out.println("등록되었습니다.");

    }

    protected void read() {
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
      }
    }

    protected void delete() {
      print.printTitle("도서 삭제");
      bookList.printBookListByNo();

      while (true) {
        int isbnNo = prompt.inputInt("ISBN 번호 입력 >>");
        int indexNo = bookList.indexByISBN(isbnNo);

        if (isbnNo == -1) {
          System.out.println("입력한 번호는 유효하지 않은 번호입니다.");
        } else {
          bookList.remove(indexNo);
          return;
        }
      }
    }
  }
}

