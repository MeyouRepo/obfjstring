package com.liangchengj.obfjstring;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Created at 2021/6/8 19:02.
 *
 * @author Liangcheng Juves
 */
public final class JavaStringObfuscator {

  public static final String JAVA_CLASS_FILE_EXT = ".class";

  private JavaStringObfuscator() {}

  public static byte[] rsaEncrypt(String string, RSA.PublicKey publicKey) {
    return RSA.encrypt(string.getBytes(StandardCharsets.UTF_8), publicKey);
  }

  public static String rsaDecrypt(byte[] bytes, RSA.PrivateKey privateKey) {
    return new String(RSA.decrypt(bytes, privateKey), StandardCharsets.UTF_8);
  }

  public static byte[] aesEncrypt(String string, String key, String iv) {
    return AES.encrypt(string.getBytes(StandardCharsets.UTF_8), key, iv);
  }

  public static String aesDecrypt(byte[] bytes, String key, String iv) {
    return new String(AES.decrypt(bytes, key, iv), StandardCharsets.UTF_8);
  }

  public static String getJNIStyleClassName(Class<?> clazz) {
    return clazz.getName().replace('.', '/');
  }

  public static String getJNIStyleShortClassName(Class<?> clazz) {
    String jniStyleClassName = getJNIStyleClassName(clazz);
    return jniStyleClassName.substring(jniStyleClassName.lastIndexOf('/') + 1);
  }

  public static String genUUIDForAESKey() {
    return genUUID(16);
  }

  public static String genUUID(int len) {
    String uuid = UUID.randomUUID().toString().replace("-", "");
    if (len <= 0) {
      return uuid;
    }
    return uuid.substring(0, len);
  }
}
