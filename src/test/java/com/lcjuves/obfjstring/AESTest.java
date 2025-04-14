package com.lcjuves.obfjstring;

import org.junit.jupiter.api.Test;

class AESTest {

  private static final String KEY = JavaStringObfuscator.genUUIDForAESKey();
  private static final String IV = JavaStringObfuscator.genUUIDForAESKey();

  private static final String TEST_STRING =
      "https://app.yinxiang.com/Home.action?hpts=1623212761205&loginToken=S%3Ds59%3AU%3D1af0018%3AE%3D179ef3c0ed0%3AC%3D179ef052054%3AP%3D5fd%3AA%3Den-web%3AV%3D3%3AH%3D353f4c8012028589d46afa5f6f22f0cb&wechatLogin=true&login=true&hptsh=sYzaNjv6lwop5OHZQmAJSU5LNKQ%3D#n=99938cb7-cb85-4afc-b7c9-baee2e6a377a&s=s59&ses=4&sh=2&sds=5&";

  @Test
  void testCommon() {
    String encrypted = new String(AES.commonEncrypt(TEST_STRING.getBytes(), KEY));
    System.out.println(String.format("encrypted >>> %s", encrypted));

    String decrypted = new String(AES.commonDecrypt(encrypted.getBytes(), KEY));
    System.out.println(String.format("decrypted >>> %s", decrypted));
  }

  @Test
  void test() {
    String encrypted = new String(AES.encrypt(TEST_STRING.getBytes(), KEY, IV));
    System.out.println(String.format("encrypted >>> %s", encrypted));

    String decrypted = new String(AES.decrypt(encrypted.getBytes(), KEY, IV));
    System.out.println(String.format("decrypted >>> %s", decrypted));
  }
}
