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

    String testDecrypt =
        OooOO0OO.OooOOoo0oo(
            new byte[] {
              86, 105, 56, 98, 75, 85, 89, 116, 70, 115, 86, 103, 109, 100, 56, 82, 83, 73, 119,
              106, 68, 115, 50, 78, 117, 69, 108, 50, 51, 122, 104, 55, 84, 57, 111, 119, 53, 78,
              118, 109, 102, 112, 54, 49, 74, 75, 75, 102, 51, 104, 71, 114, 51, 72, 81, 77, 103,
              100, 56, 66, 97, 110, 110, 55, 90, 48, 105, 119, 48, 119, 118, 51, 97, 100, 105, 87,
              105, 118, 101, 66, 115, 48, 54, 105, 118, 47, 86, 90, 89, 78, 43, 66, 82, 120, 105,
              72, 52, 113, 77, 51, 109, 69, 113, 88, 43, 108, 116, 78, 68, 89, 102, 53, 88, 86, 48,
              90, 98, 67, 105, 71, 73, 69, 108, 83, 104, 73, 47, 70, 80, 121, 122, 122, 119, 101,
              50, 84, 107, 100, 77, 51, 109, 97, 50, 98, 105, 85, 117, 48, 109, 53, 77, 54, 47, 122,
              73, 57, 50, 88, 47, 104, 104, 120, 73, 90, 118, 56, 101, 111, 104, 53, 115, 61
            },
            "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKvbmWXrRWjESGWOfpqnQQ0C5Txl2dnBpdN03Bq3RlvPv10mTH1u/+CaowUGYdMnq3f0bAJzabs0EbhxM6new8tk/o95++Qd9f3w+TUHS3xJ/RaJzB8ilfDE6VmYmp+PpXSW7fJWOAfXC6Ifz4y2vukWRwy0Zy0ClTpfTLzBpd+9AgMBAAECgYEAinqAuKdDXpAK5gpT6borqJhUrr5DNDvdqu3XDY/wmbpksGJT8B6pAAqaoUnrOYIVoKrK/Y6R+86RGNXek1p02POqzWcq2bCijboTqYYnOW7nF6AKclfC02MUDp/5JB4Zm93vgAiFiVHsUsj3V9pA1kVne01RI7/xmwgYzQz4ceECQQDpZHSJp2Od7AzOcY6SEcQj2pxJ7He1HmSxbtuEBd82v+/ZTNUlnP7js7uhu+y3qe40y5jPX5UP9nk/QK4O9aXLAkEAvIE9o5447jKrxSzEO9MQvHOAEi2r/kEBIV15rbGL6uv8TtAWwH+lXGpMioPvBsHoCpAyme0ZqxSFWpbfPViflwJAWJUCDkTz8DppWemLvTD1Cs6sRvpzLNEOUrHKqz83SyZqOEWLGK2PqIjNDEOxQrxCZtNnej3C674WosU/yvm9JQJBAIxBNRWaUg2ZEgkhJ9jDUD6HSZE8/i2tPCQRIsnDwrFGaHJTjboov2aliNG/HIfQms/RPnUc3u5V1Gr0uqEwYX0CQGABvH/hJqkvWK8wDrjzKAxfd3sCQDE05946+VB0c5iooDlWbA/V/t4b3sIfuylWhoS1tACAvrUk65g4WzaAymc=");

    System.out.println(testDecrypt);
  }
}
