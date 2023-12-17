package RSA_main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import RSA_Algorithm.RSADecryptor;
import RSA_Algorithm.RSAEncryptor;
import RSA_Algorithm.RSAKeyPair;

public class RSA_Main {
    public static void main(String[] args) {
          // Parameter RSA
        BigInteger p = BigInteger.valueOf(47);
        BigInteger q = BigInteger.valueOf(71);
        BigInteger e = BigInteger.valueOf(79);

        // Generate RSA key pair
        RSAKeyPair keyPair = new RSAKeyPair(p, q, e);
        keyPair.printKeyDetails();

        // Pesan yang akan dienkripsi
        String plainText = "HARIINI";

        // Enkripsi pesan
        RSAEncryptor encryptor = new RSAEncryptor(keyPair.getPublicKey(), keyPair.getModulus());
        BigInteger[] encryptedBlocks = encryptor.encrypt(plainText);
        encryptor.printEncryptedResult(encryptedBlocks);

        // Dekripsi pesan
        RSADecryptor decryptor = new RSADecryptor(keyPair.getPrivateKey(), keyPair.getModulus());
        String decryptedText = decryptor.decrypt(encryptedBlocks);
        decryptor.printDecryptedResult(decryptedText);
    }
    }

