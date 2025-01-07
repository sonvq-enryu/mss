package com.mss.core.cipher;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class RSACipherHandler {
    private final String secret;
    private final KeyPair keyPair;

    public RSACipherHandler(final String secret) throws NoSuchAlgorithmException {
        this.secret = secret;
        keyPair = createKeyPair();
    }

    private KeyPair createKeyPair() throws NoSuchAlgorithmException {
        var messageDigest = MessageDigest.getInstance("SHA-256");
        var seed = messageDigest.digest(secret.getBytes(StandardCharsets.UTF_8));

        var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        var secureRandom = new SecureRandom(seed);
        keyPairGenerator.initialize(2048, secureRandom);

        return keyPairGenerator.generateKeyPair();
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }
}
