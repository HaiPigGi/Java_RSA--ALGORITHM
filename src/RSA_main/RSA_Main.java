package RSA_main;

import java.math.BigInteger;
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

        System.out.println("iteration for Enkripsi");
        // Panggil iterasi untuk enkripsi
        encryptIteration(keyPair, encryptor, plainText);
        System.out.println();
        System.out.println("iteration for Dekripsi");
       // Panggil iterasi untuk dekripsi
        decryptIteration(keyPair, decryptor, encryptedBlocks);
    }

   // Iteration for Encryption
    private static void encryptIteration(RSAKeyPair keyPair, RSAEncryptor encryptor, String plainText) {
        System.out.println("Encrypting in iterations:");
        int blockSize = 3; // Change the block size as needed
        for (int i = 0; i < plainText.length(); i += blockSize) {
            String block = plainText.substring(i, Math.min(i + blockSize, plainText.length()));
            BigInteger[] encryptedBlock = encryptor.encrypt(block);

            // Display the formula for encryption
            System.out.println("Encrypting block '" + block + "':");
            System.out.println("C = " + block + "^" + keyPair.getPublicKey() + " % " + keyPair.getModulus());
            encryptor.printEncryptedResult(encryptedBlock);
        }
    }


    // Iteration for Decryption
    private static void decryptIteration(RSAKeyPair keyPair, RSADecryptor decryptor, BigInteger[] encryptedBlocks) {
        System.out.println("Decrypting in iterations:");
        for (BigInteger block : encryptedBlocks) {
            String decryptedText = decryptor.decrypt(new BigInteger[]{block});

            // Display the formula for decryption
            System.out.println("Decrypting block '" + block + "':");
            System.out.println("M = " + block + "^" + keyPair.getPrivateKey() + " % " + keyPair.getModulus());
            decryptor.printDecryptedResult(decryptedText);
        }
    }

}
