package me.liangchengj.obfjstring;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created at 2021/6/9 11:10.
 *
 * @author Liangcheng Juves
 */
public final class OooOO0OO {
  private OooOO0OO() {}

  public static byte[] encrypt(String s, String pubKey) {
    ByteArrayInputStream bais = new ByteArrayInputStream(pubKey.getBytes(StandardCharsets.UTF_8));
    try {
      RSA.PublicKey publicKey = RSA.PublicKey.form(bais);
      return JavaStringObfuscator.encrypt(s, publicKey);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public static String OooOOoo0oo(byte[] bytes, String priKey) {
    ByteArrayInputStream bais = new ByteArrayInputStream(priKey.getBytes(StandardCharsets.UTF_8));
    try {
      RSA.PrivateKey privateKey = RSA.PrivateKey.form(bais);
      return JavaStringObfuscator.decrypt(bytes, privateKey);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
}
