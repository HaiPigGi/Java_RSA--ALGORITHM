package RSA_Algorithm;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSAEncryptor {
     private BigInteger e;
    private BigInteger n;
    
    public RSAEncryptor(BigInteger e, BigInteger n) {
        this.e = e;
        this.n = n;
    }

    public BigInteger[] encrypt(String plainText) {
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.US_ASCII);
        BigInteger[] blocks = new BigInteger[plainTextBytes.length];

        for (int i = 0; i < plainTextBytes.length; i++) {
            blocks[i] = BigInteger.valueOf(plainTextBytes[i]).modPow(e, n);
        }

        return blocks;
    }

    public void printEncryptedResult(BigInteger[] encryptedBlocks) {
        System.out.print("Hasil Enkripsi C = ");
        for (BigInteger block : encryptedBlocks) {
            System.out.print(block.toString() + " ");
        }
        System.out.println();
    }
}
