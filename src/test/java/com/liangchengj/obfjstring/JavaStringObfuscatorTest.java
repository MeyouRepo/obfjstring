package com.liangchengj.obfjstring;

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
  void getJniStyleClassName() {
    System.out.println(JavaStringObfuscator.getJniStyleClassName(Base64.AndroidBase64Flag.class));
  }

  @Test
  void getJniStyleShortClassName() {
    System.out.println(JavaStringObfuscator.getJniStyleShortClassName(Base64.AndroidBase64Flag.class));
  }
}
