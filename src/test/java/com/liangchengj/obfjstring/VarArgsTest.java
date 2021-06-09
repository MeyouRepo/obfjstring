package com.liangchengj.obfjstring;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class VarArgsTest {

  @Test
  void stringOf() {
    System.out.println(VarArgs.stringOf((byte) 2, (byte) 2, (byte) 2));
  }

  @Test
  void testStringOf() {
    System.out.println(VarArgs.stringOf('A', 'B', 'C'));
  }

  @Test
  void arrayOf() {

    System.out.println(Arrays.toString());
  }
}
