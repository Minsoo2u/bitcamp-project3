package bitcamp.project3.command;

import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.BookList;
import bitcamp.project3.vo.User;
import bitcamp.project3.vo.UserList;
import bitcamp.util.Print;

public class AddDummyData implements Command{
  UserList<User> userList;
  BookList<Book> bookList;
  Print print = new Print();

  public AddDummyData(UserList<User> userList, BookList<Book> bookList){
    this.userList = userList;
    this.bookList = bookList;
  }

  @Override
  public void execute(String title) {
    userList.add(new User("최동인", "01088772267"));
    userList.add(new User("김재정", "01065487785"));
    userList.add(new User("김민수", "01065487844"));
    userList.add(new User("이순신", "01011226548"));
    userList.add(new User("유관순", "01033154487"));
    userList.add(new User("장영실", "01094875545"));

    bookList.add(new Book("JAVA 1주일 컷", "엄진영", 2023, 3));
    bookList.add(new Book("최동인 영웅기", "최동인", 2023, 3));
    bookList.add(new Book("김재정 영웅기", "김재정", 2020, 3));
    bookList.add(new Book("김민수 영웅기", "김민수", 2021, 3));
    bookList.add(new Book("일본군 이기는 법", "이순신", 1592, 3));
    bookList.add(new Book("대한민국 만세", "유관순", 1950, 3));
    bookList.add(new Book("측우기 만드는 법", "장영실", 2023, 3));

    print.printSystem("더미 데이터 생성 완료");
  }
}
