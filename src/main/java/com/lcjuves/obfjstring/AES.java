package com.lcjuves.obfjstring;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created at 2020/8/12 17:25.
 *
 * @author Liangcheng Juves
 */
public abstract class AES {

  private AES() {}

  public static byte[] commonEncrypt(byte[] src, String key) {
    if (key.length() != 16) {
      throw new SecurityException("The length of the key is not sixteen.");
    }
    try {
      SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, sks);
      return Base64.encodeToBytes(cipher.doFinal(src));
    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException e) {
      throw new AssertionError(e);
    }
  }

  public static byte[] commonDecrypt(byte[] encrypted, String key) {
    if (key.length() != 16) {
      throw new SecurityException("The length of the key is not sixteen.");
    }
    try {
      SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, sks);
      return cipher.doFinal(Base64.decodeToBytes(encrypted));
    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException e) {
      throw new AssertionError(e);
    }
  }

  public static byte[] encrypt(byte[] src, String key, String iv) {
    if (key.length() != 16) {
      throw new SecurityException("The length of the key is not sixteen.");
    }
    if (iv.length() != 16) {
      throw new SecurityException("The length of the iv is not sixteen.");
    }
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      int blockSize = cipher.getBlockSize();
      int rsltLen = src.length;
      if (rsltLen % blockSize != 0) {
        // Assign a new length to the array to prevent the array from throwing an exception out of
        // bounds.
        rsltLen += blockSize - rsltLen % blockSize;
      }

      byte[] rslt = new byte[rsltLen];
      System.arraycopy(src, 0, rslt, 0, src.length);

      SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
      IvParameterSpec ips = new IvParameterSpec(iv.getBytes());

      cipher.init(Cipher.ENCRYPT_MODE, sks, ips);
      return Base64.encodeToString(cipher.doFinal(rslt)).trim().getBytes();
    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException
        | InvalidAlgorithmParameterException e) {
      throw new AssertionError(e);
    }
  }

  public static byte[] decrypt(byte[] src, String key, String iv) {
    if (key.length() != 16) {
      throw new SecurityException("The length of the key is not sixteen.");
    }
    if (iv.length() != 16) {
      throw new SecurityException("The length of the iv is not sixteen.");
    }
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");

      SecretKeySpec sks = new SecretKeySpec(key.getBytes(), "AES");
      IvParameterSpec ips = new IvParameterSpec(iv.getBytes());

      cipher.init(Cipher.DECRYPT_MODE, sks, ips);
      return new String(cipher.doFinal(Base64.decodeToBytes(src))).trim().getBytes();
    } catch (NoSuchAlgorithmException
        | NoSuchPaddingException
        | InvalidKeyException
        | BadPaddingException
        | IllegalBlockSizeException
        | InvalidAlgorithmParameterException e) {
      throw new AssertionError(e);
    }
  }
}
