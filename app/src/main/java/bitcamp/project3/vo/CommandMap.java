package bitcamp.project3.vo;

import bitcamp.project3.command.Command;
import java.util.HashMap;
import java.util.Map;

public class CommandMap<K, V> extends HashMap<K, V> implements Map<K, V> {

  public void commandExecute(K Key) {
    if (this.containsKey(Key)){
      ((Command) this.get(Key)).execute();
    } else {
      System.out.println("존재하지 않는 메뉴입니다.");
    }
  }
}
