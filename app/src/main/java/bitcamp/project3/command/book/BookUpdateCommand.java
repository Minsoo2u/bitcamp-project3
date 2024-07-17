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

public class BookUpdateCommand implements Command {

  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();

  public BookUpdateCommand(BookList<Book> bookList) {
    this.bookList = bookList;
  }

  public void execute(String title) {
    bookList.printBookListByNo();

    int isbnNo = prompt.inputInt("ISBN 번호 선택 >>");
    Book book = bookList.bookByISBN(isbnNo);

    book.setTitle(prompt.input("책 제목 (%s) >>", book.getTitle()));
    book.setAuthor(prompt.input("책 저자 (%s) >>", book.getAuthor()));
    book.setPublishYear(
        prompt.inputIntWithRange(1300, 2025, "출판년도 (%d) >>", book.getPublishYear()));
    book.setAmount(prompt.inputIntWithRange(0, 20, "책 개수 (%d) >>", book.getAmount()));

    System.out.println("등록되었습니다.");
  }
}


