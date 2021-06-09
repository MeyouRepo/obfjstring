package com;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import me.liangchengj.obfjstring.RSA;
import org.junit.jupiter.api.Test;

class OooOO0OOTest {

  private static final RSA.KeyPair keypair = RSA.genKeyPair();
  private static final String string = "Liangcheng Juves";

  public static final String URI_STRING =
      OooOO0OO.OooOOoo0oo(
          new byte[] {
            68, 74, 87, 70, 89, 119, 112, 68, 100, 115, 65, 77, 74, 49, 53, 73, 69, 89, 71, 66, 47,
            52, 84, 43, 106, 66, 105, 82, 111, 121, 70, 117, 55, 57, 56, 43, 84, 66, 43, 87, 111,
            81, 70, 86, 85, 110, 70, 97, 115, 100, 122, 99, 106, 84, 111, 75, 102, 76, 50, 88, 53,
            47, 69, 74, 120, 48, 67, 43, 49, 88, 51, 70, 76, 75, 97, 105, 116, 101, 101, 85, 83, 48,
            71, 102, 65, 56, 65, 116, 104, 70, 118, 77, 76, 74, 76, 54, 70, 78, 72, 106, 52, 84, 89,
            76, 77, 84, 102, 111, 118, 71, 56, 88, 101, 70, 56, 57, 73, 49, 119, 112, 65, 56, 73,
            75, 82, 98, 115, 100, 72, 43, 67, 111, 84, 107, 113, 104, 107, 101, 52, 115, 87, 100,
            48, 107, 79, 86, 77, 115, 66, 122, 51, 49, 111, 106, 98, 88, 72, 79, 122, 114, 52, 116,
            115, 105, 90, 120, 97, 86, 65, 99, 52, 61
          },
          "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJKJ5MsXNTsKBHF7S2tvbpzDhR2stei22V+5qmPaSP4qeIgHf0HYjGwZOFWrHPCYCaMeTB2ou5C8+/R0R7DfRJg/z9Y5c/u2Uh/zh/hrAwlGkvL94CfU3OZifvv8rHXJKLIuLB1M0Honkxq22p6Ypg8CToVQdtMJ9GWQNkW8OLWvAgMBAAECgYBLNF2pJPX6oL97Bg3Ey2F3LpjIi/Snw/93MzARItvHJUDKfx5+SoZ2i9LvlGu7xitb/ZaOuiPYukSMRojsawkmVA39NidQzlaMxXI+uKO2gLJNH5AXlBmSQpmBjIKxhaVIlS44x5PygizpAfKpA23gJd4o0JUa01viCpZfybVqYQJBAPwDg92FXHTytw6onGk8NOpp+lMN6Egd2ghZeD+WE6HleYzyB3KUOqmzbWkdwJ5MXXBCP/QPvLQ41Nk38YDYA7cCQQCU20am2qxNyLojN4coEtuxuH4E4ntvNJxAPULIhJCkETpoRc6ghUVGf32yHmQ9y5u2cWun1Oxod2cTweRf/o3JAkEA5Quc0bzfpzrhvWTpo4ug3Fytssa1ErZu75/BdqlD9qTFsKBYvH6UQqBdFGiCJF1rnxfXYE7dRz9P1IzP5jZuSQJAI40LHqZzKFCcQ/TrDqMSXSuWzUvZ9wE1oBIBdSpT8Rs8O9MQOj4aAXBJcNTZU/jgw33rHDY6D+kUcR+Jj3OYIQJBAOA874nL0fWsDtRprkju4TwJJsshfn9CF4WTvjmbRCudqEl+VJvzAaiJvLOejxKPduszIqxXQ1qSPMBJzi5wxQ4=");
  public static final String METHOD_GET_TICKET =
      OooOO0OO.OooOOoo0oo(
          new byte[] {
            80, 69, 57, 98, 104, 117, 57, 54, 57, 65, 122, 75, 112, 101, 56, 55, 52, 85, 49, 69,
            113, 104, 76, 111, 117, 72, 53, 52, 115, 106, 70, 89, 56, 100, 109, 120, 85, 121, 105,
            105, 107, 106, 98, 48, 111, 55, 78, 89, 106, 84, 73, 80, 113, 110, 81, 102, 85, 87, 120,
            112, 82, 76, 104, 73, 116, 67, 66, 121, 66, 71, 65, 47, 54, 51, 87, 118, 88, 67, 53, 72,
            55, 114, 86, 102, 54, 109, 88, 52, 106, 80, 105, 84, 88, 86, 119, 79, 68, 107, 102, 54,
            111, 52, 105, 118, 82, 76, 111, 100, 55, 97, 66, 75, 78, 52, 51, 48, 82, 84, 115, 74,
            79, 112, 121, 88, 107, 103, 122, 115, 117, 99, 70, 104, 121, 111, 113, 75, 117, 83, 114,
            68, 47, 52, 118, 90, 73, 87, 75, 121, 84, 71, 98, 110, 99, 72, 86, 85, 71, 121, 74, 53,
            98, 120, 80, 117, 76, 101, 118, 69, 84, 68, 119, 61
          },
          "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOPd2oxIM9A9twuQHKTz3KYKFK6bbdEyP7J6gYjvaDVSNmwbS45CzR+B3/TIUAahGcKMkvQys3+zKzLAwQQYUwN20qtwO/kt+SPqUXeejwtZhnAaalD8r1J0z59DYeJofKsBwMavWNkmU1AbrYc/pMY3tcZXIrOv3stbcrTU43GJAgMBAAECgYA2gCq0oNRbu1cbmeVwAq/EuuaFeMGEZqtbifePhAt9rWM86i5hIVMkdRDW4E5bt46MvMitRa8vHPB5HCdOTm8UEl5rX1ir5muieiWCK14t3sUYFDFlhbxpTgezvEaey/VKfp3A2pKlyA547Z70Zt0uywjsHbFNhBGFWlpCrMYWgQJBAP0s/ZEjcrRsUFhCItBHntwlZclilbp8k5Lzf1nK2jIEnqbGB/l479+ATQ/cQPmu5j1HofBpXDZ8YWYkULwC7ZECQQDmaJYc1Dz/LxmrgUXMhpt+S1JRh1oyaBFulyB8A7f3KmqGlMc69CxSwjTwJ0bt9JtMuwNigOmBqf0w39dM+qh5AkEAyLMOvCzetHqc4qpjELuGCry2yQOW8IPrNPZEwXjWqgoRfuHD1pdxiLsb2/PdqlFlAbU0gfH0ANlvGNq3CaREkQJAUpdC1+qsQrzbcsLQBlRqAId3ZooiJtdvCDlcB5g9pXeq37sM/3DvQmsjFsK9hiacVJDk0bZr/XK1s4NJZeqj4QJAZ2GxgZ+f/doy114/OTnRaM2CXS0Kc5K1dqHRYLQQj+ClY5HEI6VmXOwDd4PuaQi/VoGMVeQxOQEfqC9bTTd6gw==");
  public static final String BUNDLE_KEY_TICKET =
      OooOO0OO.OooOOoo0oo(
          new byte[] {
            89, 112, 99, 66, 82, 50, 65, 75, 120, 78, 106, 118, 75, 90, 74, 100, 53, 53, 49, 84,
            110, 77, 51, 83, 100, 67, 70, 48, 112, 100, 69, 88, 109, 121, 112, 65, 71, 105, 80, 50,
            107, 85, 87, 88, 102, 73, 56, 79, 76, 114, 75, 76, 50, 53, 69, 90, 77, 117, 76, 100, 88,
            51, 54, 113, 104, 113, 103, 104, 76, 76, 50, 57, 85, 43, 115, 83, 69, 69, 116, 97, 83,
            112, 72, 99, 55, 66, 90, 88, 122, 121, 74, 51, 82, 110, 113, 117, 69, 118, 47, 118, 69,
            76, 51, 84, 56, 65, 88, 104, 68, 115, 121, 65, 47, 73, 115, 114, 48, 68, 49, 52, 70,
            113, 65, 119, 104, 70, 122, 111, 87, 73, 57, 57, 82, 78, 108, 71, 90, 79, 112, 121, 53,
            84, 54, 50, 104, 97, 49, 105, 100, 98, 114, 80, 66, 98, 48, 51, 80, 57, 81, 79, 74, 49,
            108, 111, 113, 66, 48, 104, 73, 87, 111, 61
          },
          "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJH/Mr6uF5JjVyCR6yUy5SCQrrWk0qAIEkdp9djqLsIgkKG17CiPUBKe8aL+vaLEEIDnJwBxEEkzgznkW1oxkmj9IdLIs1BDWkRgpysjiBc/mX5BgVrminJ/VrKjiRqN26ZJdS0FI3ZwlObPyInRbaPBBaYyc7j8a/+TLblY6wJbAgMBAAECgYA4JsTnnaGnRdnexwB/oCGL8tmfNzNjJXeSS+iocvlk5I18BLI9r1EGuGcLX5qkHXCTg1P20YWIAe34uCGCgQpkCIOqWkr3ZLHL73r8fkfKq6OG3ysCgMs6RmbOOGPvHClZ3FQLuJ0T3LO7jV+iJySuQ40KYycyVj3+xwuMzqjpUQJBAP6kRkH9WL4q2I1IBb9Z1LlV98W4C+r1sjlrq9zsxic8+l1huMqczf/iGbtCMdlt0wQT8w5ByG6apmcGGVx4Ut8CQQCSxpBi00f7ZIMSFnKLzsfnJynpb2NMH5O2AzEyXhjNem8CA7tGhczNU//e7ZPC35ChCqokE9I7oSgLxew3nxwFAkEAro/w9gX2Uw9OOrQX9JBhathLLci4qs7Z6dEtFRtfz8BWpbN7GbKa6YKXPy7mu3Y1RUNjyO9BtsjfsBqXQjPeIwJAUvu61HT6BJMeW9KfLpZZhUIqOxXEAVz1CXoijYSP51SqgGwVrNinIwuz5ZlS6mG2Bqbd7Qnb2eTSnF1poUTu9QJBALBGvw2N1bl9uzjKLKZFJh9rKTmA67rZM4W4359gZlnw2JEwzhedduZT2/ufimmihnqRjzMCA7UT7k1O02cSyRQ=");

  @Test
  void test() throws IOException {
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

    System.out.println(
        OooOO0OO.OooOOoo0oo(
            new byte[] {
              70, 114, 122, 112, 71, 108, 78, 82, 69, 107, 106, 118, 107, 118, 100, 109, 65, 97,
              105, 116, 52, 66, 98, 79, 105, 74, 89, 88, 85, 71, 56, 114, 105, 100, 47, 65, 67, 52,
              114, 88, 119, 119, 102, 118, 117, 76, 53, 122, 104, 113, 76, 65, 106, 75, 74, 47, 69,
              83, 49, 100, 115, 80, 122, 53, 97, 119, 55, 54, 77, 83, 89, 87, 48, 43, 114, 72, 73,
              119, 90, 57, 55, 47, 107, 107, 86, 51, 106, 69, 86, 113, 53, 80, 89, 104, 75, 48, 104,
              116, 65, 49, 88, 97, 109, 103, 100, 110, 86, 81, 54, 121, 97, 117, 112, 118, 109, 74,
              79, 50, 77, 68, 76, 113, 106, 52, 47, 97, 75, 69, 117, 73, 110, 70, 69, 102, 110, 98,
              105, 54, 98, 109, 122, 52, 122, 107, 57, 53, 122, 87, 110, 73, 49, 113, 113, 112, 109,
              49, 49, 88, 97, 78, 78, 89, 105, 101, 102, 117, 98, 98, 84, 98, 73, 61
            },
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ2mYmeNegllKcxe3dvi7hu50SsEAtIkuFcHBN+Veeaf0btKi4HCKF6zM2HX2W/wflJE/Yv0VlzkRasrixXTgYyWMrhQ6q/OiFLtKc6Kd5WJeh25lHuTl1eBuoFwn003TdQ5GEffE2nS8sodnZlGgWxxaWNpv5DVGvYA7u4EbG33AgMBAAECgYB7I1k9tT4bVI9feMImXIzLz0pYJRvm9JOgNnVSBrlesHrrpgBt7DAIOETsKeNHEjw5GnZI+SL0D8FzP+G1vK003CAqAtsLt1N7QT7EgF2lNj2UpkUkO8J27AfGZ3AzAsm3jw1yo4jvqzRtcL0FvFGU6A6T1r0R/Czo1ORvu6ZJMQJBAOTvrt1Jnqw5ovjREpNLTZ8l+tHCyx13EGE+btT/THr2kvjozj+yz9OCrvW715oxQQrDsO4VU3wqVy1j05FegTsCQQCwSVuDOhgNsoYfZ6iOWg1N8bPidYdecB65/bxgABbilazjTWHpu+dDHuMRqksX3hnjrJvsDWpXCPrqUkyRnDp1AkBfwbEOdgCcYO89y5VNbq3k52nl2uhCRs7fHdIV3UMMG56V5ip+kjhDa+HqfstLf1aJPhj/PIRS5xLXtW2E3FaJAkBX4YoUZj6dfXTtbQCTToM5axfw+gQs6Rn4QGzLMuA1vsX8XpREMB8+z8QFB27+DAO+A0uLprhvgRONAtAW+toNAkBtbtKZNpGjFV4wlR2rHiircbzcEOHQpsKA1Tmhuo80DfU+Ga76mqwIBItap+pci+89aMn7A4FHADjp5jHIruJj"));

    System.out.println(OooOO0OOTest.URI_STRING);
    System.out.println(OooOO0OOTest.METHOD_GET_TICKET);
    System.out.println(OooOO0OOTest.BUNDLE_KEY_TICKET);

    System.out.println(
        new String(
            RSA.decrypt(
                new byte[] {
                  70, 114, 122, 112, 71, 108, 78, 82, 69, 107, 106, 118, 107, 118, 100, 109, 65, 97,
                  105, 116, 52, 66, 98, 79, 105, 74, 89, 88, 85, 71, 56, 114, 105, 100, 47, 65, 67,
                  52, 114, 88, 119, 119, 102, 118, 117, 76, 53, 122, 104, 113, 76, 65, 106, 75, 74,
                  47, 69, 83, 49, 100, 115, 80, 122, 53, 97, 119, 55, 54, 77, 83, 89, 87, 48, 43,
                  114, 72, 73, 119, 90, 57, 55, 47, 107, 107, 86, 51, 106, 69, 86, 113, 53, 80, 89,
                  104, 75, 48, 104, 116, 65, 49, 88, 97, 109, 103, 100, 110, 86, 81, 54, 121, 97,
                  117, 112, 118, 109, 74, 79, 50, 77, 68, 76, 113, 106, 52, 47, 97, 75, 69, 117, 73,
                  110, 70, 69, 102, 110, 98, 105, 54, 98, 109, 122, 52, 122, 107, 57, 53, 122, 87,
                  110, 73, 49, 113, 113, 112, 109, 49, 49, 88, 97, 78, 78, 89, 105, 101, 102, 117,
                  98, 98, 84, 98, 73, 61
                },
                RSA.PrivateKey.form(
                    new ByteArrayInputStream(
                        "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ2mYmeNegllKcxe3dvi7hu50SsEAtIkuFcHBN+Veeaf0btKi4HCKF6zM2HX2W/wflJE/Yv0VlzkRasrixXTgYyWMrhQ6q/OiFLtKc6Kd5WJeh25lHuTl1eBuoFwn003TdQ5GEffE2nS8sodnZlGgWxxaWNpv5DVGvYA7u4EbG33AgMBAAECgYB7I1k9tT4bVI9feMImXIzLz0pYJRvm9JOgNnVSBrlesHrrpgBt7DAIOETsKeNHEjw5GnZI+SL0D8FzP+G1vK003CAqAtsLt1N7QT7EgF2lNj2UpkUkO8J27AfGZ3AzAsm3jw1yo4jvqzRtcL0FvFGU6A6T1r0R/Czo1ORvu6ZJMQJBAOTvrt1Jnqw5ovjREpNLTZ8l+tHCyx13EGE+btT/THr2kvjozj+yz9OCrvW715oxQQrDsO4VU3wqVy1j05FegTsCQQCwSVuDOhgNsoYfZ6iOWg1N8bPidYdecB65/bxgABbilazjTWHpu+dDHuMRqksX3hnjrJvsDWpXCPrqUkyRnDp1AkBfwbEOdgCcYO89y5VNbq3k52nl2uhCRs7fHdIV3UMMG56V5ip+kjhDa+HqfstLf1aJPhj/PIRS5xLXtW2E3FaJAkBX4YoUZj6dfXTtbQCTToM5axfw+gQs6Rn4QGzLMuA1vsX8XpREMB8+z8QFB27+DAO+A0uLprhvgRONAtAW+toNAkBtbtKZNpGjFV4wlR2rHiircbzcEOHQpsKA1Tmhuo80DfU+Ga76mqwIBItap+pci+89aMn7A4FHADjp5jHIruJj"
                            .getBytes())))));

    System.out.println(
        new String(
            RSA.decrypt(
                "FrzpGlNREkjvkvdmAait4BbOiJYXUG8rid/AC4rXwwfvuL5zhqLAjKJ/ES1dsPz5aw76MSYW0+rHIwZ97/kkV3jEVq5PYhK0htA1XamgdnVQ6yaupvmJO2MDLqj4/aKEuInFEfnbi6bmz4zk95zWnI1qqpm11XaNNYiefubbTbI="
                    .getBytes(),
                RSA.PrivateKey.form(
                    new ByteArrayInputStream(
                        "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ2mYmeNegllKcxe3dvi7hu50SsEAtIkuFcHBN+Veeaf0btKi4HCKF6zM2HX2W/wflJE/Yv0VlzkRasrixXTgYyWMrhQ6q/OiFLtKc6Kd5WJeh25lHuTl1eBuoFwn003TdQ5GEffE2nS8sodnZlGgWxxaWNpv5DVGvYA7u4EbG33AgMBAAECgYB7I1k9tT4bVI9feMImXIzLz0pYJRvm9JOgNnVSBrlesHrrpgBt7DAIOETsKeNHEjw5GnZI+SL0D8FzP+G1vK003CAqAtsLt1N7QT7EgF2lNj2UpkUkO8J27AfGZ3AzAsm3jw1yo4jvqzRtcL0FvFGU6A6T1r0R/Czo1ORvu6ZJMQJBAOTvrt1Jnqw5ovjREpNLTZ8l+tHCyx13EGE+btT/THr2kvjozj+yz9OCrvW715oxQQrDsO4VU3wqVy1j05FegTsCQQCwSVuDOhgNsoYfZ6iOWg1N8bPidYdecB65/bxgABbilazjTWHpu+dDHuMRqksX3hnjrJvsDWpXCPrqUkyRnDp1AkBfwbEOdgCcYO89y5VNbq3k52nl2uhCRs7fHdIV3UMMG56V5ip+kjhDa+HqfstLf1aJPhj/PIRS5xLXtW2E3FaJAkBX4YoUZj6dfXTtbQCTToM5axfw+gQs6Rn4QGzLMuA1vsX8XpREMB8+z8QFB27+DAO+A0uLprhvgRONAtAW+toNAkBtbtKZNpGjFV4wlR2rHiircbzcEOHQpsKA1Tmhuo80DfU+Ga76mqwIBItap+pci+89aMn7A4FHADjp5jHIruJj"
                            .getBytes())))));
  }
}
