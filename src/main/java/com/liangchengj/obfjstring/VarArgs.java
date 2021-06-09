package com.liangchengj.obfjstring;

/**
 * Created at 2021/6/9 17:35.
 *
 * @author Liangcheng Juves
 */
public final class VarArgs {
  private VarArgs() {}

  public static String stringOf(byte... bytes) {
    return new String(bytes);
  }

  public static String stringOf(char... chars) {
    return new String(chars);
  }

  public static <E> E[] arrayOf(E... es) {
    return es;
  }

  public static byte[] arrayOf(byte... bytes) {
    return bytes;
  }

  public static short[] arrayOf(short... shorts) {
    return shorts;
  }

  public static int[] arrayOf(int... ints) {
    return ints;
  }

  public static float[] arrayOf(float... floats) {
    return floats;
  }

  public static double[] arrayOf(double... doubles) {
    return doubles;
  }

  public static long[] arrayOf(long... longs) {
    return longs;
  }

  public static boolean[] arrayOf(boolean... booleans) {
    return booleans;
  }

  public static char[] arrayOf(char... chars) {
    return chars;
  }
}
