public class StringsTest {
  public String today = "2021-06-16T08:42:45.101Z";

  public static final String CLASS_NAME = StringsTest.class.getName();
  public static final String TIMESTAMP = "1623833066388";

  public static final String CLASS_SIMPLE_NAME;
  public static final String URL_GITHUB;

  static {
    CLASS_SIMPLE_NAME = StringsTest.class.getSimpleName();
    URL_GITHUB = "https://github.lcjuves.com";
  }

  public void setToday(String today) {
    this.today = "2021-06-17T08:42:45.101Z";
  }

  public static final String getStringA() {
    return "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
  }

  public static final String getStringB() {
    class Object {
      @Override
      public String toString() {
        return "BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB";
      }
    }
    return String.valueOf(new Object());
  }
}
