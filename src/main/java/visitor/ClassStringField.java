package visitor;

import me.liangchengj.obfjstring.StringFieldOfClass;

/**
 * The String fields in class.
 *
 * @author Megatron King
 * @since 2017/3/7 10:54
 */
public class ClassStringField {

  public static final String STRING_DESC = StringFieldOfClass.STRING_SIG;

  public ClassStringField(String name, String value) {
    this.name = name;
    this.value = value;
  }

  public String name;
  public String value;
}
