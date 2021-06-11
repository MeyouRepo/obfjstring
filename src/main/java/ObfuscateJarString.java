import com.liangchengj.obfjstring.JavaStringObfuscator;
import com.liangchengj.obfjstring.Resource;
import com.liangchengj.obfjstring.io.Stream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created at 2021/6/11 11:17
 *
 * @author Liangcheng Juves
 */
public class ObfuscateJarString {

  public static void main(String[] args) throws IOException {
    System.out.println("请输入jar包路径:");
    Scanner scanner = new Scanner(System.in);
    String path = scanner.next();
    if (!path.endsWith(".jar")) {
      System.out.println("请输入正确的jar包路径");
      System.exit(0);
    }
    int index = path.lastIndexOf(".jar");
    File jarIn = new File(path);
    File jarOut = new File(path.substring(0, index) + "obfuscated.jar");
    try {
      processJar(jarIn, jarOut, Charset.forName("UTF-8"), Charset.forName("UTF-8"));
    } catch (IllegalArgumentException e) {
      if ("MALFORMED".equals(e.getMessage())) {
        processJar(jarIn, jarOut, Charset.forName("GBK"), Charset.forName("UTF-8"));
      } else {
        throw e;
      }
    }
    System.out.println("混淆完成");
  }

  private static void processJar(File jarIn, File jarOut, Charset charsetIn, Charset charsetOut)
      throws IOException {
    ZipInputStream zis = null;
    ZipOutputStream zos = null;
    try {
      zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(jarIn)), charsetIn);
      zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(jarOut)), charsetOut);
      ZipEntry entryIn;
      Map<String, Integer> processedEntryNamesMap = new HashMap<>();
      while ((entryIn = zis.getNextEntry()) != null) {
        final String entryName = entryIn.getName();
        if (!processedEntryNamesMap.containsKey(entryName)) {
          ZipEntry entryOut = new ZipEntry(entryIn);
          entryOut.setCompressedSize(-1);
          zos.putNextEntry(entryOut);
          if (!entryIn.isDirectory()) {
            if (entryName.endsWith(JavaStringObfuscator.JAVA_CLASS_FILE_EXT)) {
              ObfuscateClassString.obfuscateClass(zis, zos);
            } else {
              Stream.readAndWrite(zis, zos);
            }
          }
          zos.closeEntry();
          processedEntryNamesMap.put(entryName, 1);
        }
      }

      for (Object depClassDesc : DepClassDescList.get()) {
        if (depClassDesc instanceof String) {
          writeDepClassToJar(zos, (String) depClassDesc);
        } else if (depClassDesc instanceof Class) {
          writeDepClassToJar(zos, (Class<?>) depClassDesc);
        }
      }

    } finally {
      zos.close();
      zis.close();
    }
  }

  private static void writeDepClassToJar(ZipOutputStream zos, String classFilePath)
      throws IOException {

    InputStream in = Resource.getAsStream(classFilePath);
    if (null == in) {
      throw new NullPointerException("null == in");
    }
    System.out.println("classFilePath >> " + classFilePath);

    ZipEntry classFile = new ZipEntry(classFilePath);
    zos.putNextEntry(classFile);
    zos.write(ObfuscateClassString.obfuscateClassToBytes(in));
    zos.flush();
    zos.closeEntry();
    in.close();
  }

  private static void writeDepClassToJar(ZipOutputStream zos, Class<?> clazz) throws IOException {
    String classFilePath =
        JavaStringObfuscator.getJNIStyleClassName(clazz) + JavaStringObfuscator.JAVA_CLASS_FILE_EXT;
    writeDepClassToJar(zos, classFilePath);

    int i = 1;
    while (true) {
      try {
        String tryClassFilePath =
            String.format(
                "%s$%d%s",
                classFilePath.substring(0, classFilePath.lastIndexOf('.')),
                i,
                JavaStringObfuscator.JAVA_CLASS_FILE_EXT);
        writeDepClassToJar(zos, tryClassFilePath);
        i++;
      } catch (Throwable tr) {
        return;
      }
    }
  }
}
