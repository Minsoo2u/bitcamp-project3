package bitcamp.project3.util;

import java.util.Scanner;

public class Prompt {

   Scanner keyboardScanner = new Scanner(System.in);

  public  String input(String format, Object... args) {
    System.out.printf(format + " ", args);
    return keyboardScanner.nextLine();
  }


  public  int inputInt(String format, Object... args) {
    return Integer.parseInt(input(format, args));
  }

  public  void close() {
    keyboardScanner.close();
  }
}
