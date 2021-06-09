package com;

import me.liangchengj.obfjstring.RSA;
import org.junit.jupiter.api.Test;

class OooOO0OOTest {

  private static final RSA.KeyPair keypair = RSA.genKeyPair();
  private static final String string = "Liangcheng Juves";

  @Test
  void test() {
    String encrypted = new String(OooOO0OO.encrypt(string, keypair.getPublicKey().toString()));
    System.out.println(encrypted);

    String decrypted =
        OooOO0OO.OooOOoo0oo(encrypted.getBytes(), keypair.getPrivateKey().toString());

    System.out.println(decrypted);
  }
}
