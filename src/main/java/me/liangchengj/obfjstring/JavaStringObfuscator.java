package me.liangchengj.obfjstring;

/**
 * Cated at 2021/6/8 19:02.
 *
 * @author Liangcheng Juves
 */
public final class JavaStringObfuscator {
  private JavaStringObfuscator() {}

  public static byte[] encrypt(String string, RSA.PublicKey publicKey) {
    return RSA.encrypt(string.getBytes(), publicKey);
  }

  public static String decrypt(byte[] bytes, RSA.PrivateKey privateKey) {
    return new String(RSA.decrypt(bytes, privateKey));
  }
}
