package visitor;

import com.liangchengj.obfjstring.JavaStringObfuscator;
import com.liangchengj.obfjstring.OooOO0OO;
import com.liangchengj.obfjstring.StringFieldOfClass;
import com.liangchengj.obfjstring.util.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * Visit the class to execute string fog.
 *
 * @author Megatron King
 * @since 2017/3/6 20:37
 */
public class StringFieldClassVisitor extends ClassVisitor {

  private static final String IGNORE_ANNOTATION = "Landroid/support/annotation/Keep;";

  private boolean isClInitExists;

  private List<StringFieldOfClass> mStaticFinalFields = new ArrayList<>();
  private List<StringFieldOfClass> mStaticFields = new ArrayList<>();
  private List<StringFieldOfClass> mFinalFields = new ArrayList<>();
  private List<StringFieldOfClass> mFields = new ArrayList<>();

  private String mClassName;

  private boolean mIgnoreClass;

  public StringFieldClassVisitor(ClassWriter cw) {
    super(Opcodes.ASM5, cw);
  }

  private synchronized void doObfuscate(MethodVisitor mv, String str) {
    // RSA.KeyPair keyPair = RSA.genKeyPair();
    // byte[] encodeBytes = OooOO0OO.encrypt(str, keyPair.getPublicKey().toString());
    String key = JavaStringObfuscator.genUUIDForAESKey();
    String iv = JavaStringObfuscator.genUUIDForAESKey();
    byte[] encodeBytes = OooOO0OO.encrypt(str, key, iv);
    int encodeBytesLen = encodeBytes.length;
    mv.visitIntInsn(Opcodes.SIPUSH, encodeBytesLen);
    mv.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_BYTE);
    for (int i = 0; i < encodeBytesLen; i++) {
      mv.visitInsn(Opcodes.DUP);
      mv.visitIntInsn(Opcodes.SIPUSH, i);
      mv.visitIntInsn(Opcodes.BIPUSH, encodeBytes[i]);
      mv.visitInsn(Opcodes.BASTORE);
    }
    // mv.visitLdcInsn(keyPair.getPrivateKey().toString());
    mv.visitLdcInsn(key);
    mv.visitLdcInsn(iv);
    mv.visitMethodInsn(
        Opcodes.INVOKESTATIC,
        JavaStringObfuscator.getJniStyleClassName(OooOO0OO.class),
        "OooOOoo0oo",
        String.format(
            "(%s%s%s)%s",
            "[B",
            StringFieldOfClass.STRING_DESC,
            StringFieldOfClass.STRING_DESC,
            StringFieldOfClass.STRING_DESC),
        false);
  }

  @Override
  public void visit(
      int version,
      int access,
      String name,
      String signature,
      String superName,
      String[] interfaces) {
    this.mClassName = name;
    super.visit(version, access, name, signature, superName, interfaces);
  }

  @Override
  public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    mIgnoreClass = IGNORE_ANNOTATION.equals(desc);
    return super.visitAnnotation(desc, visible);
  }

  @Override
  public FieldVisitor visitField(
      int access, String name, String desc, String signature, Object value) {
    if (StringFieldOfClass.STRING_DESC.equals(desc) && name != null && !mIgnoreClass) {
      // static final, in this condition, the value is null or not null.
      if ((access & Opcodes.ACC_STATIC) != 0 && (access & Opcodes.ACC_FINAL) != 0) {
        mStaticFinalFields.add(new StringFieldOfClass(name, (String) value));
        value = null;
      }
      // static, in this condition, the value is null.
      if ((access & Opcodes.ACC_STATIC) != 0 && (access & Opcodes.ACC_FINAL) == 0) {
        mStaticFields.add(new StringFieldOfClass(name, (String) value));
        value = null;
      }

      // final, in this condition, the value is null or not null.
      if ((access & Opcodes.ACC_STATIC) == 0 && (access & Opcodes.ACC_FINAL) != 0) {
        mFinalFields.add(new StringFieldOfClass(name, (String) value));
        value = null;
      }

      // normal, in this condition, the value is null.
      if ((access & Opcodes.ACC_STATIC) != 0 && (access & Opcodes.ACC_FINAL) != 0) {
        mFields.add(new StringFieldOfClass(name, (String) value));
        value = null;
      }
    }
    return super.visitField(access, name, desc, signature, value);
  }

  @Override
  public MethodVisitor visitMethod(
      int access, String name, String desc, String signature, String[] exceptions) {

    MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
    if (mv != null && !mIgnoreClass) {
      if ("<clinit>".equals(name)) {
        isClInitExists = true;
        // If clinit exists meaning the static fields (not final) would have be inited here.
        mv =
            new MethodVisitor(Opcodes.ASM5, mv) {

              private String lastStashCst;

              @Override
              public void visitCode() {
                super.visitCode();
                // Here init static final fields.
                for (StringFieldOfClass field : mStaticFinalFields) {
                  if (field.getValue() == null) {
                    continue;
                  }
                  doObfuscate(super.mv, field.getValue());

                  super.visitFieldInsn(
                      Opcodes.PUTSTATIC,
                      mClassName,
                      field.getName(),
                      StringFieldOfClass.STRING_DESC);
                }
              }

              @Override
              public void visitLdcInsn(Object cst) {
                // Here init static or static final fields, but we must check field name int
                // 'visitFieldInsn'
                if (cst != null
                    && cst instanceof String
                    && !TextUtils.isEmptyAfterTrim((String) cst)) {
                  lastStashCst = (String) cst;
                  doObfuscate(super.mv, lastStashCst);

                } else {
                  lastStashCst = null;
                  super.visitLdcInsn(cst);
                }
              }

              @Override
              public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                if (mClassName.equals(owner) && lastStashCst != null) {
                  boolean isContain = false;
                  for (StringFieldOfClass field : mStaticFields) {
                    if (field.getName().equals(name)) {
                      isContain = true;
                      break;
                    }
                  }
                  if (!isContain) {
                    for (StringFieldOfClass field : mStaticFinalFields) {
                      if (field.getName().equals(name) && field.getValue() == null) {
                        field.setValue(lastStashCst);
                        break;
                      }
                    }
                  }
                }
                lastStashCst = null;
                super.visitFieldInsn(opcode, owner, name, desc);
              }
            };

      } else if ("<init>".equals(name)) {
        // Here init final(not static) and normal fields
        mv =
            new MethodVisitor(Opcodes.ASM5, mv) {
              @Override
              public void visitLdcInsn(Object cst) {
                // We don't care about whether the field is final or normal
                if (cst != null
                    && cst instanceof String
                    && !TextUtils.isEmptyAfterTrim((String) cst)) {
                  doObfuscate(super.mv, (String) cst);
                } else {
                  super.visitLdcInsn(cst);
                }
              }
            };
      } else {
        mv =
            new MethodVisitor(Opcodes.ASM5, mv) {

              @Override
              public void visitLdcInsn(Object cst) {
                if (cst != null
                    && cst instanceof String
                    && !TextUtils.isEmptyAfterTrim((String) cst)) {
                  // If the value is a static final field
                  for (StringFieldOfClass field : mStaticFinalFields) {
                    if (cst.equals(field.getValue())) {
                      super.visitFieldInsn(
                          Opcodes.GETSTATIC,
                          mClassName,
                          field.getName(),
                          StringFieldOfClass.STRING_DESC);
                      return;
                    }
                  }
                  // If the value is a final field (not static)
                  for (StringFieldOfClass field : mFinalFields) {
                    // if the value of a final field is null, we ignore it
                    if (cst.equals(field.getValue())) {
                      super.visitVarInsn(Opcodes.ALOAD, 0);
                      super.visitFieldInsn(
                          Opcodes.GETFIELD,
                          mClassName,
                          field.getName(),
                          StringFieldOfClass.STRING_DESC);
                      return;
                    }
                  }
                  doObfuscate(super.mv, (String) cst);
                  return;
                }
                super.visitLdcInsn(cst);
              }
            };
      }
    }
    return mv;
  }

  @Override
  public void visitEnd() {
    if (!mIgnoreClass && !isClInitExists && !mStaticFinalFields.isEmpty()) {
      MethodVisitor mv = super.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
      mv.visitCode();
      // Here init static final fields.
      for (StringFieldOfClass field : mStaticFinalFields) {
        if (field.getValue() == null) {
          continue; // It could not be happened
        }
        doObfuscate(mv, field.getValue());
        mv.visitFieldInsn(
            Opcodes.PUTSTATIC, mClassName, field.getName(), StringFieldOfClass.STRING_DESC);
      }
      mv.visitInsn(Opcodes.RETURN);
      mv.visitMaxs(1, 0);
      mv.visitEnd();
    }
    super.visitEnd();
  }
}
