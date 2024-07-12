package bitcamp.project3.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PromptLibrary extends Prompt {

  public static int inputInt(String format, Object... args) {
    while (true) {
      try {
        return Integer.parseInt(input(format, args));
      } catch (NumberFormatException e) {
        System.out.println("[System] 숫자로 입력해 주세요.");
      }
    }
  }

  public static int inputIntWithRange(int min, int max, String format, Object... args) {
    while (true) {
      int inputNum = inputInt(format, args);

      if (min <= inputNum && inputNum <= max) {
        return inputNum;
      } else {
        System.out.println("[System] 올바른 항목을 입력해 주세요.");
      }
    }
  }

  public Calendar inputCalendar(Calendar defaultCalendar) {
    int year = 0;
    int month = 0;

    int defaultYear = defaultCalendar.get(Calendar.YEAR);
    int defaultMonth = defaultCalendar.get(Calendar.MONTH);
    int defaultDay = defaultCalendar.get(Calendar.DATE);

    String defaultDate = defaultYear + "-" + (defaultMonth + 1) + "-" + defaultDay;

    Date date = defaultCalendar.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    while (true) {
      System.out.println("------<< 날짜 입력 [0 = 종료] >>------");
      System.out.println("1. 자동 입력 (" + dateFormat.format(date) + ")");
      System.out.println("2. 수동 입력");
      int selectInputMethod = inputInt("날짜 입력 방식 선택 >>");

      switch (selectInputMethod) {
        case 0:
          return null;
        case 1:
          System.out.println("");
          return defaultCalendar;
        case 2:
          String inputYearMessage =
              "연도 입력(2000 ~ 2100, default = " + defaultYear + ") >>";
          year = inputYearWithDefault(inputYearMessage, defaultYear);

          if (year == defaultYear) {
            String inputMonthMessage =
                "월 입력(1 ~ 12, default = " + (defaultMonth + 1) + ") >>";
            month = inputMonthWithDefault(inputMonthMessage, defaultMonth);

            if (month == defaultMonth) {
              Calendar calendar = Print.printCalendar(year, month);

              String inputDayMessage =
                  "일 입력 (1 ~ " + getMaxDay(calendar) + ", default = " + defaultDay
                      + ") >>";
              inputDayWithDefault(inputDayMessage, defaultDay, calendar);

              System.out.println("");
              return calendar;
            } else {
              Calendar calendar = Print.printCalendar(year, month);

              String inputDayMessage = "일 입력 (1 ~ " + getMaxDay(calendar) + ") >>";
              inputDayWithoutDefault(inputDayMessage, calendar);

              System.out.println("");
              return calendar;
            }
          } else {
            String inputMonthMessage = "월 입력(1 ~ 12) >>";
            month = inputMonthWithoutDefault(inputMonthMessage);
            Calendar calendar = Print.printCalendar(year, month);

            String inputDayMessage = "일 입력( ~ " + getMaxDay(calendar) + ") >>";
            inputDayWithoutDefault(inputDayMessage, calendar);

            System.out.println("");
            return calendar;
          }
        default:
          System.out.println("올바른 값을 입력하세요.");
      }
    }
  }

  public int inputMonthWithoutDefault(String message) {
    int month;
    while (true) {
      try {
        String command = input(message);
        if (command.isEmpty()) {
          System.out.println("값을 입력해 주세요.");
          continue;
        } else {
          month = Integer.parseInt(command) - 1;
        }

        if (!isInRange(month, -1, 11)) {
          System.out.println("유효한 월을 입력해 주세요.");
          continue;
        }
        return month;
      } catch (NumberFormatException e) {
        System.out.println("유효한 값이 아닙니다.");
      }

    }
  }

  public void inputDayWithoutDefault(String message, Calendar calendar) {
    while (true) {
      try {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day;
        String command = input(message);
        if (command.isEmpty()) {
          System.out.println("값을 입력해 주세요.");
          continue;
        } else {
          day = Integer.parseInt(command);
        }

        if (!isValidDay(year, month, day)) {
          System.out.println("유효한 날짜를 입력해 주세요.");
        } else {
          calendar.set(year, month, day);
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("유효한 값이 아닙니다.");
      }

    }
  }

  public int inputYearWithDefault(String message, int defaultYear) {
    int year;
    while (true) {
      try {
        String command = input(message);
        if (command.isEmpty()) {
          year = defaultYear;
        } else {
          year = Integer.parseInt(command);
        }

        if (!isInRange(year, 2000 - 1, 2100)) {
          System.out.println("유효한 년도를 입력해 주세요.");
          continue;
        }
        return year;
      } catch (NumberFormatException e) {
        System.out.println("숫자로 입력해 주세요.");
      }
    }

  }

  public int inputMonthWithDefault(String message, int defaultMonth) {
    int month;
    while (true) {
      try {
        String command = input(message);
        if (command.isEmpty()) {
          month = defaultMonth;
        } else {
          month = Integer.parseInt(command) - 1;
        }

        if (!isInRange(month, -1, 11)) {
          System.out.println("유효한 월을 입력해 주세요.");
          continue;
        }
        return month;
      } catch (NumberFormatException e) {
        System.out.println("유효한 값이 아닙니다.");
      }

    }
  }

  public void inputDayWithDefault(String message, int defaultDay, Calendar calendar) {
    while (true) {
      try {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day;
        String command = input(message);
        if (command.isEmpty()) {
          day = defaultDay;
        } else {
          day = Integer.parseInt(command);
        }

        if (!isValidDay(year, month, day)) {
          System.out.println("유효한 날짜를 입력해 주세요.");
          continue;
        } else {
          calendar.set(year, month, day);
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("유효한 값이 아닙니다.");
      }

    }
  }

  public boolean isInRange(int value, int min, int max) {
    return value > min && value <= max; // 월은 0부터 11까지 유효 (0: 1월, 11: 12월)
  }

  public boolean isValidDay(int year, int month, int day) {
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, 1);
    int maxDay = getMaxDay(calendar);
    return day >= 1 && day <= maxDay;
  }

  public int getMaxDay(Calendar calendar) {
    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  }
}
