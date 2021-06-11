package com.liangchengj.obfjstring.main;

import com.liangchengj.obfjstring.JavaStringObfuscator;
import com.liangchengj.obfjstring.Resource;
import com.liangchengj.obfjstring.io.Stream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import visitor.ClassVisitorFactory;

/**
 * Created at 2021/6/11 09:57
 *
 * @author Liangcheng Juves
 */
public class ObfuscateClassString {

  private static final String OPTIONS_ORDER = "Options: [module] [variant]";
  private static final List<String> CLASS_FILE_PATH_LIST = new LinkedList<>();

  static class CmdOptions {
    String module;
    String variant;

    static CmdOptions from(String[] args) {
      CmdOptions cmdOptions = new CmdOptions();
      cmdOptions.module = args[0];
      cmdOptions.variant = args[1];
      return cmdOptions;
    }

    boolean checkIsOK() {
      return !variant.isEmpty();
    }
  }

  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println(OPTIONS_ORDER);
      System.exit(0);
    }

    CmdOptions cmdOptions = CmdOptions.from(args);

    if (!cmdOptions.checkIsOK()) {
      System.exit(0);
    }

    System.out.println(String.format("Start classes obfuscate:\n\t%s\n", cmdOptions.variant));

    collectClassFilePath(cmdOptions);
    if (CLASS_FILE_PATH_LIST.isEmpty()) {
      System.err.println("No class files are collected");
      return;
    }
    for (String path : CLASS_FILE_PATH_LIST) {
      processClassFile(path);
    }

    System.out.println();
    System.out.println("Start write dep classes ===================");
    writeDepClassToVariant(cmdOptions);
    System.out.println("End write dep classes ===================");
    System.out.println();
    System.out.println("Obfuscate string in java class file completed !!!");
  }

  public static void writeDepClassToVariant(CmdOptions cmdOptions) throws IOException {
    writeDepClassToVariant(cmdOptions.variant);
  }

  public static void writeDepClassToVariant(String variant) throws IOException {
    for (Object depClassDesc : DepClassDescList.get()) {
      if (depClassDesc instanceof String) {
        writeDepClassToVariant(variant, (String) depClassDesc);
      } else if (depClassDesc instanceof Class) {
        writeDepClassToVariant(variant, (Class<?>) depClassDesc);
      }
    }
  }

  private static void writeDepClassToVariant(CmdOptions cmdOptions, Class<?> clazz)
      throws IOException {
    writeDepClassToVariant(cmdOptions.variant, clazz);
  }

  private static void writeDepClassToVariant(String variant, Class<?> clazz) throws IOException {
    String classFilePath =
        JavaStringObfuscator.getJNIStyleClassName(clazz) + JavaStringObfuscator.JAVA_CLASS_FILE_EXT;

    writeDepClassToVariant(variant, classFilePath);

    int i = 1;
    while (true) {
      try {
        String tryClassFilePath =
            String.format(
                "%s$%d%s",
                classFilePath.substring(0, classFilePath.lastIndexOf('.')),
                i,
                JavaStringObfuscator.JAVA_CLASS_FILE_EXT);
        writeDepClassToVariant(variant, tryClassFilePath);
        i++;
      } catch (Throwable tr) {
        return;
      }
    }
  }

  private static void writeDepClassToVariant(CmdOptions cmdOptions, String classFilePath)
      throws IOException {
    writeDepClassToVariant(cmdOptions.variant, classFilePath);
  }

  private static void writeDepClassToVariant(String variant, String classFilePath)
      throws IOException {
    String separatorClassFilePath = classFilePath.replace("/", File.separator);
    String[] dirs = separatorClassFilePath.split(File.separator);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < dirs.length - 1; i++) {
      sb.append(dirs[i]);
      sb.append(File.separator);
      File file = new File(variant + File.separator + sb.toString());
      if (!file.exists()) {
        file.mkdirs();
      }
    }

    InputStream in = Resource.getAsStream(classFilePath);
    if (null == in) {
      throw new NullPointerException("null == in");
    }
    System.out.println(String.format("\tclassFilePath >> %s", classFilePath));
    String variantClassFilePath = variant + File.separator + separatorClassFilePath;
    System.out.println(String.format("\tvariantClassFilePath >> %s", variantClassFilePath));
    FileOutputStream fos = new FileOutputStream(variantClassFilePath);
    obfuscateClass(in, fos);
  }

  public static void processClassFile(String path) throws IOException {
    FileInputStream fis = new FileInputStream(path);
    File outFile = new File(path + ".tmp");
    FileOutputStream fos = new FileOutputStream(outFile);

    obfuscateClass(fis, fos);

    File file = new File(path);
    if (file.exists()) {
      file.delete();
      outFile.renameTo(file);
    }
  }

  public static InputStream obfuscateClassToInputStream(InputStream classIn) throws IOException {
    Objects.requireNonNull(classIn);
    ClassReader cr = new ClassReader(classIn);
    ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
    ClassVisitor aia = ClassVisitorFactory.create(cr.getClassName(), cw);
    cr.accept(aia, 0);
    final ByteArrayInputStream bais = new ByteArrayInputStream(cw.toByteArray());
    return bais;
  }

  public static byte[] obfuscateClassToBytes(InputStream classIn) throws IOException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Stream.readAndWrite(obfuscateClassToInputStream(classIn), baos);
    return baos.toByteArray();
  }

  public static void obfuscateClass(InputStream classIn, OutputStream out) throws IOException {
    Stream.readAndWrite(obfuscateClassToInputStream(classIn), out);
  }

  public static void obfuscateClass(byte[] bytes, OutputStream out) throws IOException {
    obfuscateClass(new ByteArrayInputStream(bytes), out);
  }

  /**
   * 收集所有类文件的路径
   *
   * @param dir 存放类文件的文件夹
   */
  static void collectClassFilePath(File dir) {
    if (!dir.isDirectory()) {
      return;
    }

    if (!CLASS_FILE_PATH_LIST.isEmpty()) {
      CLASS_FILE_PATH_LIST.clear();
    }

    System.out.println(String.format("Collect under folder： %s", dir.getAbsolutePath()));

    for (File file : dir.listFiles()) {
      if (file.isDirectory()) {
        collectClassFilePath(file);
      } else {
        if (file.getName().endsWith(JavaStringObfuscator.JAVA_CLASS_FILE_EXT)) {
          CLASS_FILE_PATH_LIST.add(file.getPath());
          System.out.println(String.format("\tCollected file: %s", file.getAbsolutePath()));
        }
      }
    }
  }

  static void collectClassFilePath(String dirPath) {
    collectClassFilePath(new File(dirPath));
  }

  static void collectClassFilePath(CmdOptions cmdOptions) {
    collectClassFilePath(cmdOptions.variant);
  }
}
