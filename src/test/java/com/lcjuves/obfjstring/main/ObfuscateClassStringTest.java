package com.lcjuves.obfjstring.main;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class ObfuscateClassStringTest {

  @Test
  void main() throws IOException {
    String projectDir = new File("").getAbsolutePath();
    String[] args =
        new String[] {"", String.format("%s%s%s", projectDir, File.separator, "classes")};
    ObfuscateClassString.main(args);
  }
}
