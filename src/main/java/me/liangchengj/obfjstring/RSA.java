package me.liangchengj.obfjstring;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 * Created at 2020/8/11 18:11.
 *
 * @author Liangcheng Juves
 */
public abstract class RSA {

  private RSA() {}

  private static final int KEY_SIZE = 1024;
  private static final int MAX_ENCRYPT_BLOCK = 117;
  private static final int MAX_DECRYPT_BLOCK = 128;

  private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";

  public static <T> T instOf(Class<T> classOfT)
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Method getInstance = classOfT.getDeclaredMethod("getInstance", String.class);
    return (T) getInstance.invoke(classOfT, "RSA");
  }

  public static KeyPair genKeyPair() {
    try {
      KeyPairGenerator keyPairGenerator = instOf(KeyPairGenerator.class);
      keyPairGenerator.initialize(KEY_SIZE);
      java.security.KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey =
          new PublicKey(Base64.encodeToString(keyPair.getPublic().getEncoded(), Base64.NO_WRAP)) {
            @Override
            public int hashCode() {
              return super.hashCode();
            }
          };
      PrivateKey privateKey =
          new PrivateKey(Base64.encodeToString(keyPair.getPrivate().getEncoded(), Base64.NO_WRAP)) {
            @Override
            public int hashCode() {
              return super.hashCode();
            }
          };
      return new KeyPair(publicKey, privateKey) {
        @Override
        public int hashCode() {
          return super.hashCode();
        }
      };
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      throw new AssertionError(e);
    }
  }

  private static synchronized byte[] loopCrypt(byte[] src, Cipher cipher, int maxBlock)
      throws IOException, BadPaddingException, IllegalBlockSizeException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    int srcLen = src.length;
    for (int ofs = 0; srcLen - ofs > 0; ) {
      if (srcLen - ofs >= maxBlock) {
        baos.write(cipher.doFinal(src, ofs, maxBlock));
      } else {
        baos.write(cipher.doFinal(src, ofs, srcLen - ofs));
      }
      ofs += maxBlock;
    }
    baos.flush();
    byte[] cpy = baos.toByteArray();
    baos.close();
    return cpy;
  }

  public static byte[] encrypt(byte[] src, PublicKey publicKey) {
    try {
      byte[] encodedKey = Base64.decode(publicKey.toString(), Base64.NO_WRAP);
      Cipher cipher = instOf(Cipher.class);
      cipher.init(
          Cipher.ENCRYPT_MODE,
          instOf(KeyFactory.class).generatePublic(new X509EncodedKeySpec(encodedKey)));
      return Base64.encode(loopCrypt(src, cipher, MAX_ENCRYPT_BLOCK), Base64.NO_WRAP);
    } catch (InvalidKeyException
        | IllegalBlockSizeException
        | BadPaddingException
        | InvalidKeySpecException
        | NoSuchMethodException
        | IllegalAccessException
        | InvocationTargetException
        | IOException e) {
      throw new AssertionError(e);
    }
  }

  public static byte[] decrypt(byte[] src, PrivateKey privateKey) {
    try {
      byte[] encodedKey = Base64.decode(privateKey.toString(), Base64.NO_WRAP);
      Cipher cipher = instOf(Cipher.class);
      cipher.init(
          Cipher.DECRYPT_MODE,
          instOf(KeyFactory.class).generatePrivate(new PKCS8EncodedKeySpec(encodedKey)));
      return loopCrypt(Base64.decode(src, Base64.NO_WRAP), cipher, MAX_DECRYPT_BLOCK);
    } catch (InvalidKeyException
        | IllegalBlockSizeException
        | BadPaddingException
        | InvalidKeySpecException
        | NoSuchMethodException
        | IllegalAccessException
        | InvocationTargetException
        | IOException e) {
      throw new AssertionError(e);
    }
  }

  public static boolean verify(byte[] src, byte[] signed, PublicKey publicKey) {
    try {
      byte[] encodedKey = Base64.decode(publicKey.toString(), Base64.NO_WRAP);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initVerify(
          instOf(KeyFactory.class).generatePublic(new X509EncodedKeySpec(encodedKey)));
      signature.update(src);
      return signature.verify(Base64.decode(signed, Base64.NO_WRAP));
    } catch (NoSuchAlgorithmException
        | InvocationTargetException
        | NoSuchMethodException
        | InvalidKeyException
        | IllegalAccessException
        | InvalidKeySpecException
        | SignatureException e) {
      throw new AssertionError(e);
    }
  }

  public static byte[] sign(byte[] src, PrivateKey privateKey) {
    try {
      byte[] encodedKey = Base64.decode(privateKey.toString(), Base64.NO_WRAP);
      Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
      signature.initSign(
          instOf(KeyFactory.class).generatePrivate(new PKCS8EncodedKeySpec(encodedKey)));
      signature.update(src);
      return Base64.encode(signature.sign(), Base64.NO_WRAP);
    } catch (NoSuchAlgorithmException
        | InvocationTargetException
        | NoSuchMethodException
        | InvalidKeyException
        | IllegalAccessException
        | InvalidKeySpecException
        | SignatureException e) {
      throw new AssertionError(e);
    }
  }

  public abstract static class KeyPair {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    KeyPair(PublicKey publicKey, PrivateKey privateKey) {
      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
      return publicKey;
    }

    public PrivateKey getPrivateKey() {
      return privateKey;
    }

    public void saveToDir(String dirpath) {
      Objects.requireNonNull(dirpath);
      File saveDir = new File(dirpath);
      if (saveDir.isDirectory()) {
        saveDir.setWritable(true);
        try (FileOutputStream publicFos = new FileOutputStream(new File(dirpath, "publicKey"));
            FileOutputStream privateFos = new FileOutputStream(new File(dirpath, "privateKey"))) {
          publicFos.write(publicKey.toString().getBytes());
          privateFos.write(privateKey.toString().getBytes());
        } catch (IOException e) {
          throw new AssertionError(e);
        }
      }
    }
  }

  private static String readKey(InputStream is) throws IOException {
    Objects.requireNonNull(is);
    final byte[] bytes = new byte[1024];
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    for (int len; (len = is.read(bytes)) != -1; baos.write(bytes, 0, len), baos.flush())
      ;
    String result = baos.toString();
    baos.close();
    if (result.isEmpty()) {
      throw new NullPointerException("The content of Key cannot be empty.");
    }
    return result;
  }

  public abstract static class PublicKey {

    private String key;

    private PublicKey(String key) {
      this.key = key;
    }

    @Override
    public String toString() {
      return key;
    }

    public static PublicKey form(InputStream is) throws IOException {
      return new PublicKey(readKey(is)) {
        @Override
        public int hashCode() {
          return super.hashCode();
        }
      };
    }
  }

  public abstract static class PrivateKey {

    private String key;

    private PrivateKey(String key) {
      this.key = key;
    }

    @Override
    public String toString() {
      return key;
    }

    public static PrivateKey form(InputStream is) throws IOException {
      return new PrivateKey(readKey(is)) {
        @Override
        public int hashCode() {
          return super.hashCode();
        }
      };
    }
  }
}
