package me.liangchengj.obfjstring;

import java.nio.charset.StandardCharsets;

/**
 * Cated at 2021/6/8 19:02.
 *
 * @author Liangcheng Juves
 */
public final class JavaStringObfuscator {
  private JavaStringObfuscator() {}

  public static byte[] encrypt(String string, RSA.PublicKey publicKey) {
    return RSA.encrypt(string.getBytes(StandardCharsets.UTF_8), publicKey);
  }

  public static String decrypt(byte[] bytes, RSA.PrivateKey privateKey) {
    return new String(RSA.decrypt(bytes, privateKey), StandardCharsets.UTF_8);
  }
}
