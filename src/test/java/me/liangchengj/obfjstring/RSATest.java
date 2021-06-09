package me.liangchengj.obfjstring;

import org.junit.jupiter.api.Test;

class RSATest {

  private static final RSA.KeyPair keypair = RSA.genKeyPair();
  private static final String string = "Liangcheng Juves";

  @Test
  void test() {
    String encrypted = new String(RSA.encrypt(string.getBytes(), keypair.getPublicKey()));
    System.out.println(encrypted);

    String decrypted = new String(RSA.decrypt(encrypted.getBytes(), keypair.getPrivateKey()));

    System.out.println(decrypted);
  }
}
