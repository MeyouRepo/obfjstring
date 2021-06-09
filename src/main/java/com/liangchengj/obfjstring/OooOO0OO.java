package com.liangchengj.obfjstring;

/**
 * Created at 2021/6/9 11:10.
 *
 * @author Liangcheng Juves
 */
public final class OooOO0OO {
  private OooOO0OO() {}

  //  public static byte[] encrypt(String s, String pubKey) {
  //    ByteArrayInputStream bais = new
  // ByteArrayInputStream(pubKey.getBytes(StandardCharsets.UTF_8));
  //    try {
  //      RSA.PublicKey publicKey = RSA.PublicKey.form(bais);
  //      return JavaStringObfuscator.rsaEncrypt(s, publicKey);
  //    } catch (IOException e) {
  //      throw new AssertionError(e);
  //    }
  //  }
  //
  //  public static String OooOOoo0oo(byte[] bytes, String priKey) {
  //    ByteArrayInputStream bais = new
  // ByteArrayInputStream(priKey.getBytes(StandardCharsets.UTF_8));
  //    try {
  //      RSA.PrivateKey privateKey = RSA.PrivateKey.form(bais);
  //      return JavaStringObfuscator.rsaDecrypt(bytes, privateKey);
  //    } catch (IOException e) {
  //      throw new AssertionError(e);
  //    }
  //  }

  //  public static byte[] encrypt(String s, String key) {
  //    return JavaStringObfuscator.aesEncrypt(s, key);
  //  }
  //
  //  public static String OooOOoo0oo(byte[] bytes, String key) {
  //    return JavaStringObfuscator.aesDecrypt(bytes, key);
  //  }

  public static byte[] encrypt(String s, String key, String iv) {
    return JavaStringObfuscator.aesEncrypt(s, key, iv);
  }

  public static String OooOOoo0oo(byte[] bytes, String key, String iv) {
    return JavaStringObfuscator.aesDecrypt(bytes, key, iv);
  }
}
