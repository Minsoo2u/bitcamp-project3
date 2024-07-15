package bitcamp.project3.command;

import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.vo.Book;

import java.util.Stack;

public class BookCommand extends AbstractCommand {

  private String menuTitle = "도서 관리";
  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"등록", "수정", "조회", "삭제", "이전"};
  private Print print = new Print();

  public BookCommand(BookList<Book> list) {
    this.bookList = list;
  }

  public void execute(Stack<String> menuPath) {
    menuPath.push("도서 관리");
    while (true) {
      print.printTitle(menuTitle);
      print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, 4, "%s >>", AbstractCommand.getMenuPath(menuPath));

      switch (menuNo) {
        case 1:
          this.createBook();
          break;
        case 2:
          this.updateBook();
          break;
        case 3:
          this.readBook();
          break;
        case 4:
          this.deleteBook();
          break;
        case 0:
          menuPath.pop();
          return;

      }
    }
  }

  protected void createBook() {
    Book book = new Book();

    book.setTitle(prompt.input("책 제목 >>"));
    book.setAuthor(prompt.input("책 저자 >>"));
    book.setPublishYear(prompt.inputIntWithRange(1300, 2025, "출판년도 >>"));

    bookList.add(book);
    System.out.println("등록되었습니다.");
  }

  protected void updateBook() {
    bookList.printBookListByNo();

    int isbnNo = prompt.inputInt("ISBN 번호 선택 >>");
    Book book = bookList.bookByISBN(isbnNo);

    book.setTitle(prompt.input("책 제목 (%s) >>", book.getTitle()));
    book.setAuthor(prompt.input("책 저자 (%s) >>", book.getAuthor()));
    book.setPublishYear(
        prompt.inputIntWithRange(1300, 2025, "출판년도 (%d) >>", book.getPublishYear()));

    System.out.println("등록되었습니다.");
  }

  protected void readBook() {
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

  protected void deleteBook() {
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

