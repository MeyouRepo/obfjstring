import com.liangchengj.obfjstring.AES;
import com.liangchengj.obfjstring.Base64;
import com.liangchengj.obfjstring.JavaStringObfuscator;
import com.liangchengj.obfjstring.OooOO0OO;
import com.liangchengj.obfjstring.RSA;
import com.liangchengj.obfjstring.io.Stream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import visitor.ClassVisitorFactory;

/** Created by qtfreet on 2017/3/14. */
public class ObfuscateJavaString {
  //  private static final String encryptFile = OooOO0OO.class.getName().replace(".", "/") +
  // ".class";
  //  private static final String separator = File.separator;
  private static final String PARAMS_ORDER = "params [module] [variant]";
  private static List<String> filelist = new ArrayList();

  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("params [module] [variant]");
      System.exit(0);
      return;
    }
    //    byte b[] = readClass();
    //    String module = args[0];
    String variant = args[1];
    if (variant == null) {
      System.out.println("variant not detected; try " + PARAMS_ORDER);
      System.exit(0);
    }
    System.err.println(variant);
    if (filelist != null && filelist.size() > 0) {
      filelist.clear();
    }
    getFiles(variant);
    if (filelist.size() == 0) {
      System.out.println("no found any class files");
      System.exit(0);
    }
    for (String path : filelist) {
      processFile(path);
    }
    //    String encFilePath = variant + separator + encryptFile;
    //    write(encFilePath, b);

    writeDepClassToVariant(variant, OooOO0OO.class);
    writeDepClassToVariant(variant, JavaStringObfuscator.class);

    Class<?> base64Class = Base64.class;
    writeDepClassToVariant(variant, base64Class);
    writeDepClassToVariant(
        variant,
        base64Class.getClassLoader(),
        String.format(
            "%s$%s%s",
            JavaStringObfuscator.getJNIStyleClassName(base64Class),
            "1FieldReflection",
            JavaStringObfuscator.JAVA_CLASS_FILE_EXT));
    writeDepClassToVariant(variant, Base64.JDKBase64CodecType.class);
    writeDepClassToVariant(variant, Base64.AndroidBase64CodecType.class);
    writeDepClassToVariant(variant, Base64.AndroidBase64Flag.class);

    writeDepClassToVariant(variant, RSA.class);
    writeDepClassToVariant(variant, RSA.KeyPair.class);
    writeDepClassToVariant(variant, RSA.PrivateKey.class);
    writeDepClassToVariant(variant, RSA.PublicKey.class);

    writeDepClassToVariant(variant, AES.class);

    System.err.println("Task completed");
  }

  private static void writeDepClassToVariant(String variant, Class<?> clazz) throws IOException {
    String classFilePath =
        JavaStringObfuscator.getJNIStyleClassName(clazz) + JavaStringObfuscator.JAVA_CLASS_FILE_EXT;
    ClassLoader classLoader = clazz.getClassLoader();
    writeDepClassToVariant(variant, classLoader, classFilePath);

    int i = 1;
    while (true) {
      try {
        String tryClassFilePath =
            String.format(
                "%s$%d%s",
                classFilePath.substring(0, classFilePath.lastIndexOf('.')),
                i,
                JavaStringObfuscator.JAVA_CLASS_FILE_EXT);
        writeDepClassToVariant(variant, classLoader, tryClassFilePath);
        i++;
      } catch (Throwable tr) {
        return;
      }
    }
  }

  private static void writeDepClassToVariant(
      String variant, ClassLoader classLoader, String classFilePath) throws IOException {
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

    InputStream in = classLoader.getResourceAsStream(classFilePath);
    if (null == in) {
      throw new NullPointerException("null == in");
    }
    System.out.println("classFilePath >> " + classFilePath);
    String variantClassFilePath = variant + File.separator + separatorClassFilePath;
    System.out.println("variantClassFilePath >> " + variantClassFilePath);
    ByteArrayInputStream bais = new ByteArrayInputStream(processClass(in));
    FileOutputStream fos = new FileOutputStream(variantClassFilePath);
    Stream.readAndWrite(bais, fos);
  }

  //  private static byte[] readClass() {
  //    InputStream in = null;
  //    try {
  //      in = OooOO0OO.class.getClassLoader().getResourceAsStream(encryptFile);
  //      int len = in.available();
  //      byte[] b = new byte[len];
  //      in.read(b);
  //      in.close();
  //      return b;
  //    } catch (Exception e) {
  //      e.printStackTrace();
  //    }
  //    return null;
  //  }

  private static void processFile(String path) throws IOException {
    FileInputStream fis = new FileInputStream(path);
    byte[] content = processClass(fis);
    closeQuietly(fis);
    if (content == null) {
      return;
    }
    File outFile = new File(path + ".tmp");
    FileOutputStream fos = new FileOutputStream(outFile);
    fos.write(content);
    fos.flush();
    closeQuietly(fos);
    File file = new File(path);
    if (file.exists()) {
      file.delete();
      outFile.renameTo(new File(path));
    }
  }

  //  private static void processJar(
  //      File jarIn, File jarOut, Charset charsetIn, Charset charsetOut, byte[] out)
  //      throws IOException {
  //    ZipInputStream zis = null;
  //    ZipOutputStream zos = null;
  //    try {
  //      zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(jarIn)), charsetIn);
  //      zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(jarOut)),
  // charsetOut);
  //      ZipEntry entryIn;
  //      Map<String, Integer> processedEntryNamesMap = new HashMap<>();
  //      boolean flag = false;
  //      while ((entryIn = zis.getNextEntry()) != null) {
  //        final String entryName = entryIn.getName();
  //        if (!processedEntryNamesMap.containsKey(entryName)) {
  //          ZipEntry entryOut = new ZipEntry(entryIn);
  //          entryOut.setCompressedSize(-1);
  //          zos.putNextEntry(entryOut);
  //          if (!entryIn.isDirectory()) {
  //            if (entryName.endsWith(".class")) {
  //              if (entryName.equals(encryptFile)) {
  //                flag = true;
  //              }
  //              processClass(zis, zos);
  //            } else {
  //              copy(zis, zos);
  //            }
  //          }
  //          zos.closeEntry();
  //          processedEntryNamesMap.put(entryName, 1);
  //        }
  //      }
  //      if (!flag) {
  //        ZipEntry eninject = new ZipEntry(encryptFile);
  //        zos.putNextEntry(eninject);
  //        zos.write(out);
  //        zos.closeEntry();
  //      }
  //    } finally {
  //      closeQuietly(zos);
  //      closeQuietly(zis);
  //    }
  //  }

  private static void processClass(InputStream classIn, OutputStream classOut) throws IOException {
    ClassReader cr = new ClassReader(classIn);
    ClassWriter cw = new ClassWriter(1);
    ClassVisitor aia = ClassVisitorFactory.create(cr.getClassName(), cw);
    //   ClassVisitor aia = new TestClassVisitor("", cw);
    cr.accept(aia, 0);

    classOut.write(cw.toByteArray());
    classOut.flush();
  }

  private static byte[] processClass(InputStream classIn) throws IOException {
    if (classIn == null || classIn.available() == 0) {
      return null;
    }
    ClassReader cr = new ClassReader(classIn);
    ClassWriter cw = new ClassWriter(1);
    ClassVisitor aia = ClassVisitorFactory.create(cr.getClassName(), cw);
    //   ClassVisitor aia = new TestClassVisitor("", cw);
    cr.accept(aia, 0);
    return cw.toByteArray();
  }

  private static void closeQuietly(Closeable target) {
    if (target != null) {
      try {
        target.close();
      } catch (Exception e) {
        // Ignored.
      }
    }
  }

  private static void write(String path, byte[] out) throws IOException {
    FileOutputStream fos = new FileOutputStream(path);
    fos.write(out);
    fos.flush();
    fos.close();
  }

  /**
   * 遍历所有文件，添加到list中
   *
   * @param filePath 文件路径
   */
  private static void getFiles(String filePath) {
    File[] files = new File(filePath).listFiles();
    if (files == null) {
      return;
    }
    for (File file : files) {
      if (file.isDirectory()) {
        getFiles(file.getPath());
      } else {
        if (file.getName().endsWith(".class")) {
          filelist.add(file.getPath());
        }
      }
    }
  }

  private static int copy(InputStream in, OutputStream out) throws IOException {
    int total = 0;
    byte[] buffer = new byte[8192];
    int c;
    while ((c = in.read(buffer)) != -1) {
      total += c;
      out.write(buffer, 0, c);
    }
    return total;
  }
}
