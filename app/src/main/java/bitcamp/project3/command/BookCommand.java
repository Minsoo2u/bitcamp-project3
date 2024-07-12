package bitcamp.project3.command;

import bitcamp.project3.util.BookList;
import bitcamp.project3.util.Print;
import bitcamp.project3.util.PromptLibrary;
import bitcamp.project3.vo.Book;

import java.util.List;

public class BookCommand extends AbstractCommand {

  private String menuTitle = "도서 관리";
  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();
  private String[] menus = {"등록", "수정", "조회", "삭제", "이전"};

  public BookCommand(BookList<Book> list) {
    this.bookList = list;
    }

  public void execute() {
    while(true) {
      Print.printTitle(menuTitle);
      Print.printMenus(menus);

      int menuNo = prompt.inputIntWithRange(0, 4, "메뉴 선택 >>");

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
    // 전체 도서를 띄우는 메서드 추가

    int isbnNo = prompt.inputInt("ISBN 번호 선택 >>");
    Book book = bookList.bookByISBN(isbnNo);

    book.setTitle(prompt.input("책 제목 >>"));
    book.setAuthor(prompt.input("책 저자 >>"));
    book.setPublishYear(prompt.inputIntWithRange(1300, 2025, "출판년도 >>"));

    System.out.println("등록되었습니다.");

  }

  protected void readBook() {
    String[] menus = {"번호순 조회", "이름순 조회", "이전"};

    Print.printTitle("도서 조회");
    Print.printMenus(menus);

    int menuNo = prompt.inputIntWithRange(0, menus.length-1, "메뉴 선택 >>");

    switch(menuNo) {
      case 1:
        System.out.println("ISBN | 제목 | 저자 | 출판년도");
        for (int i = 0; i < bookList.size(); i++) {
          int isbnNo = bookList.get(i).getISBN();
          String title = bookList.get(i).getTitle();
          String author = bookList.get(i).getAuthor();
          int publishYear = bookList.get(i).getPublishYear();

          System.out.printf("%d | %s | %s | %d \n", isbnNo, title, author, publishYear);

        }
        break;

      case 2:
        System.out.println("제목 | ISBN | 저자 | 출판년도");
        for (int i = 0; i < bookList.size(); i++) {
          int isbnNo = bookList.get(i).getISBN();
          String title = bookList.get(i).getTitle();
          String author = bookList.get(i).getAuthor();
          int publishYear = bookList.get(i).getPublishYear();

          System.out.printf("%s | %d | %s | %d \n", title, isbnNo, author, publishYear);

        }
        break;


    }
  }

  protected void deleteBook() {
    Print.printTitle("도서 삭제");

    System.out.println("ISBN | 제목 | 저자 | 출판년도");
    for (int i = 0; i < bookList.size(); i++) {
      int isbnNo = bookList.get(i).getISBN();
      String title = bookList.get(i).getTitle();
      String author = bookList.get(i).getAuthor();
      int publishYear = bookList.get(i).getPublishYear();

      System.out.printf("%d | %s | %s | %d \n", isbnNo, title, author, publishYear);

    }

    while(true) {
      int isbnNo = prompt.inputInt("ISBN 번호 입력 >>");
      int indexNo = bookList.indexByISBN(isbnNo);

      if (isbnNo == -1){
        System.out.println("입력한 번호는 유효하지 않은 번호입니다.");
      } else {
        bookList.remove(indexNo);
        return;
      }

    }
  }

  }

