package com.lcjuves.obfjstring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RSATest {

    private static final RSA.KeyPair keypair = RSA.genKeyPair();
    private static final String string = "Liangcheng Juves";

    @Test
    void test() {
        String encrypted = new String(RSA.encrypt(string.getBytes(), keypair.getPublicKey()));
        System.out.println(encrypted);

        String decrypted = new String(RSA.decrypt(encrypted.getBytes(), keypair.getPrivateKey()));
        System.out.println(decrypted);
        assertEquals(string, decrypted);

        String signed = new String(RSA.sign(string.getBytes(), keypair.getPrivateKey()));
        System.out.println(signed);

        boolean verifyResult = RSA.verify(string.getBytes(), signed.getBytes(), keypair.getPublicKey());
        System.out.println(verifyResult);
        assertTrue(verifyResult);
    }
}
