package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;

import java.util.List;

public class BookCommand extends AbstractCommand {

  private List<Book> bookList;
  private Prompt prompt = new Prompt();

  private String[] menus = {"등록", "수정", "조회", "삭제"};

  public BookCommand(List<Book> list) {
    this.bookList = list;
    }

  protected void processMenu(String menuName) {
    System.out.printf("[%s]\n", menuName);
    switch (menuName) {
      case "등록":
        this.createBook();
        break;
      case "수정":
        this.updateBook();
        break;
      case "조회":
        this.readBook();
        break;
      case "삭제":
        this.deleteBook();
        break;
    }
  }

  protected void createBook() {
    Book book = new Book();
    book.setTitle(prompt.input("제목?"));
    book.setAuthor(prompt.input("저자?"));
    book.setPublishYear(prompt.inputInt("출판년도?"));

    bookList.add(book);
    System.out.println("등록되었습니다.");
  }

  protected void updateBook() {
  }

  protected void readBook() {
  }

  protected void deleteBook() {
  }

  }

