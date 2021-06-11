import com.liangchengj.obfjstring.AES;
import com.liangchengj.obfjstring.Base64;
import com.liangchengj.obfjstring.JavaStringObfuscator;
import com.liangchengj.obfjstring.OooOO0OO;
import com.liangchengj.obfjstring.RSA;
import java.util.LinkedList;

/**
 * Created at 2021/6/11 11:06
 *
 * @author Liangcheng Juves
 */
final class DepClassDescList extends LinkedList<Object> {
  private static volatile DepClassDescList depClassDescList;

  private DepClassDescList() {
    this.add(OooOO0OO.class);
    this.add(JavaStringObfuscator.class);

    Class<?> base64Class = Base64.class;
    this.add(base64Class);
    this.add(
        String.format(
            "%s$%s%s",
            JavaStringObfuscator.getJNIStyleClassName(base64Class),
            "1FieldReflection",
            JavaStringObfuscator.JAVA_CLASS_FILE_EXT));

    this.add(Base64.JDKBase64CodecType.class);
    this.add(Base64.AndroidBase64CodecType.class);
    this.add(Base64.AndroidBase64Flag.class);

    this.add(RSA.class);
    this.add(RSA.KeyPair.class);
    this.add(RSA.PrivateKey.class);
    this.add(RSA.PublicKey.class);

    this.add(AES.class);
  }

  static DepClassDescList get() {
    DepClassDescList tmp = depClassDescList;
    if (null == tmp) {
      synchronized (DepClassDescList.class) {
        tmp = depClassDescList;
        if (null == tmp) {
          tmp = new DepClassDescList();
          depClassDescList = tmp;
        }
      }
    }
    return depClassDescList;
  }
}
