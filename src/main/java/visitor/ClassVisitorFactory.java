package visitor;

import com.liangchengj.obfjstring.JavaStringObfuscator;
import com.liangchengj.obfjstring.OooOO0OO;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

/**
 * A factory creates {@link ClassVisitor}.
 *
 * @author Megatron King
 * @since 2017/3/7 19:56
 */
public final class ClassVisitorFactory {

  private ClassVisitorFactory() {}

  public static ClassVisitor create(String className, ClassWriter cw) {
    if (JavaStringObfuscator.getJNIStyleClassName(OooOO0OO.class).equals(className)) {
      return createEmpty(cw);
    }
    if (WhiteList.inWhiteList(className, WhiteList.FLAG_PACKAGE)
        || WhiteList.inWhiteList(className, WhiteList.FLAG_CLASS)) {
      return createEmpty(cw);
    }
    return new StringFieldClassVisitor(cw);
  }

  public static ClassVisitor createEmpty(ClassWriter cw) {
    return new ClassVisitor(Opcodes.ASM5, cw) {};
  }
}
