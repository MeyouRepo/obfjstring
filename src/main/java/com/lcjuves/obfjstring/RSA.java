package com.lcjuves.obfjstring;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * Created at 2020/8/11 18:11.
 *
 * @author Liangcheng Juves
 */
public abstract class RSA {

    private RSA() {
    }

    private static final int DEFAULT_KEY_SIZE = 4096;

    private static final String SIGNATURE_ALGORITHM = "SHA512withRSA";

    public static <T> T instanceOf(Class<T> classOfT)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getInstance = classOfT.getDeclaredMethod("getInstance", String.class);
        return (T) getInstance.invoke(classOfT, "RSA");
    }

    public static KeyPair genKeyPair() {
        return genKeyPair(DEFAULT_KEY_SIZE);
    }

    public static KeyPair genKeyPair(int keySize) {
        try {
            KeyPairGenerator keyPairGenerator = instanceOf(KeyPairGenerator.class);
            keyPairGenerator.initialize(keySize);
            java.security.KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey =
                    new PublicKey(Base64.encodeToString(keyPair.getPublic().getEncoded())) {
                        @Override
                        public int hashCode() {
                            return super.hashCode();
                        }
                    };
            publicKey.setKeySize(keySize);
            PrivateKey privateKey =
                    new PrivateKey(Base64.encodeToString(keyPair.getPrivate().getEncoded())) {
                        @Override
                        public int hashCode() {
                            return super.hashCode();
                        }
                    };
            privateKey.setKeySize(keySize);
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


    private static synchronized int computeCryptSize(int keySize, boolean encrypt) {
        int decryptBlockSize = keySize / 8;
        int encryptBlockSize = decryptBlockSize - 11;
        return encrypt ? encryptBlockSize : decryptBlockSize;
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
            byte[] encodedKey = Base64.decodeToBytes(publicKey.toString());
            Cipher cipher = instanceOf(Cipher.class);
            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    instanceOf(KeyFactory.class).generatePublic(new X509EncodedKeySpec(encodedKey)));
            int cryptSize = computeCryptSize(publicKey.getKeySize(), true);
            return Base64.encodeToBytes(loopCrypt(src, cipher, cryptSize));
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
            byte[] encodedKey = Base64.decodeToBytes(privateKey.toString());
            Cipher cipher = instanceOf(Cipher.class);
            cipher.init(
                    Cipher.DECRYPT_MODE,
                    instanceOf(KeyFactory.class).generatePrivate(new PKCS8EncodedKeySpec(encodedKey)));
            int cryptSize = computeCryptSize(privateKey.getKeySize(), false);
            return loopCrypt(Base64.decodeToBytes(src), cipher, cryptSize);
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
            byte[] encodedKey = Base64.decodeToBytes(publicKey.toString());
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(
                    instanceOf(KeyFactory.class).generatePublic(new X509EncodedKeySpec(encodedKey)));
            signature.update(src);
            return signature.verify(Base64.decodeToBytes(signed));
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
            byte[] encodedKey = Base64.decodeToBytes(privateKey.toString());
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(
                    instanceOf(KeyFactory.class).generatePrivate(new PKCS8EncodedKeySpec(encodedKey)));
            signature.update(src);
            return Base64.encodeToBytes(signature.sign());
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
        private final PublicKey publicKey;
        private final PrivateKey privateKey;

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
                boolean saveDirWritable = saveDir.setWritable(true);
                if (!saveDirWritable) return;
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
        for (int len; (len = is.read(bytes)) != -1; ) {
            baos.write(bytes, 0, len);
            baos.flush();
        }
        baos.close();
        String result = baos.toString();
        baos.close();
        if (result.isEmpty()) {
            throw new NullPointerException("The content of Key cannot be empty.");
        }
        return result;
    }

    private abstract static class Key {
        private final String key;

        public int getKeySize() {
            return keySize;
        }

        public void setKeySize(int keySize) {
            this.keySize = keySize;
        }

        private int keySize = DEFAULT_KEY_SIZE;

        private Key(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return key;
        }

        public static Key form(InputStream is) throws IOException {
            return new Key(readKey(is)) {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            };
        }
    }

    public abstract static class PublicKey extends Key {
        private PublicKey(String key) {
            super(key);
        }
    }

    public abstract static class PrivateKey extends Key {

        private PrivateKey(String key) {
            super(key);
        }
    }
}
