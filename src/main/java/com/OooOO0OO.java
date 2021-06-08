package com;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import me.liangchengj.obfjstring.JavaStringObfuscator;
import me.liangchengj.obfjstring.RSA;

/** Created by qtfreet on 2017/2/24. */
public final class OooOO0OO {
  private OooOO0OO() {}

  public static byte[] encrypt(String s, String key) {
    ByteArrayInputStream bais = new ByteArrayInputStream(key.getBytes());
    try {
      RSA.PublicKey publicKey = RSA.PublicKey.form(bais);
      return JavaStringObfuscator.encrypt(s, publicKey);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public static String OooOOoo0oo(byte[] bytes, String key) {
    ByteArrayInputStream bais = new ByteArrayInputStream(key.getBytes());
    try {
      RSA.PrivateKey privateKey = RSA.PrivateKey.form(bais);
      return JavaStringObfuscator.decrypt(bytes, privateKey);
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }
}
