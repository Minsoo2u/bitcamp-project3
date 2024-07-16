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

public class BookDeleteCommand implements Command {

  private BookList<Book> bookList;
  private PromptLibrary prompt = new PromptLibrary();
  private Print print = new Print();

  public BookDeleteCommand(BookList<Book> bookList) {
    this.bookList = bookList;
  }

  public void execute(String title) {
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

