package com.lcjuves.obfjstring;

import com.lcjuves.obfjstring.io.Stream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created at 2021/6/9 18:33.
 *
 * @author Liangcheng Juves
 */
public final class Resource {

  private Resource() {}

  private static final ClassLoader classLoader = Resource.class.getClassLoader();

  public static InputStream getAsStream(String name) {
    return classLoader.getResourceAsStream(name);
  }

  public static URL get(String name) {
    return classLoader.getResource(name);
  }

  public static Enumeration<URL> getAsEnumeration(String name) throws IOException {
    return classLoader.getResources(name);
  }

  public static void readAndWrite(String name, OutputStream outputStream) {
    Stream.readAndWrite(getAsStream(name), outputStream);
  }
}
