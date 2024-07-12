package bitcamp.project3.command;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.util.Print;

import java.util.Stack;

public class AbstractCommand implements Command {

  @Override
  public void execute() {
  }

  @Override
  public void create() {
  }

  @Override
  public void read() {
  }

  @Override
  public void update() {
  }

  @Override
  public void delete() {
  }

  public String getMenuPath(Stack<String> menuPath) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < menuPath.size(); i++) {
      stringBuilder.append(menuPath.get(i));
      if (i == menuPath.size() - 1) {
        stringBuilder.append("/");
      }
    }

    return stringBuilder.toString();
  }
}