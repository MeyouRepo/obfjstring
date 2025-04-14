package com.lcjuves.obfjstring;

import org.junit.jupiter.api.Test;

class JavaStringObfuscatorTest {

  @Test
  void genUUIDForAESKey() {
    System.out.println(JavaStringObfuscator.genUUIDForAESKey());
  }

  @Test
  void genUUID() {
    System.out.println(JavaStringObfuscator.genUUID(-1));
    System.out.println(JavaStringObfuscator.genUUID(20));
  }

  @Test
  void getJNIStyleClassName() {
    System.out.println(JavaStringObfuscator.getJNIStyleClassName(Base64.AndroidBase64Flag.class));
  }

  @Test
  void getJNIStyleShortClassName() {
    System.out.println(
        JavaStringObfuscator.getJNIStyleShortClassName(Base64.AndroidBase64Flag.class));
  }
}
