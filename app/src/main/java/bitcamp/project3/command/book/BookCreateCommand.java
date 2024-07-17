package bitcamp.project3.command.book;

import bitcamp.project3.command.Command;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.BookList;
import bitcamp.util.PromptLibrary;

public class BookCreateCommand implements Command {

  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();

  public BookCreateCommand(BookList<Book> bookList) {
    this.bookList = bookList;
  }

  public void execute(String title) {
    Book book = new Book();

    book.setTitle(prompt.input("책 제목 >>"));
    book.setAuthor(prompt.input("책 저자 >>"));
    book.setPublishYear(prompt.inputIntWithRange(1300, 2025, "출판년도 >>"));
    book.setAmount(prompt.inputIntWithRange(0, 20, "책 개수>>"));

    bookList.add(book);
    System.out.println("등록되었습니다.");
  }
}

