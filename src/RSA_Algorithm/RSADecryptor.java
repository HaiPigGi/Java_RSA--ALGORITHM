package RSA_Algorithm;

import java.math.BigInteger;

public class RSADecryptor {
    private BigInteger d;
    private BigInteger n;

    public RSADecryptor(BigInteger d, BigInteger n) {
        this.d = d;
        this.n = n;
    }

    public String decrypt(BigInteger[] encryptedBlocks) {
        StringBuilder decryptedText = new StringBuilder();

        for (BigInteger block : encryptedBlocks) {
            BigInteger decryptedValue = block.modPow(d, n);
            decryptedText.append((char) decryptedValue.intValue());
        }

        return decryptedText.toString();
    }

    public void printDecryptedResult(String decryptedText) {
        System.out.println("Hasil Dekripsi M = " + decryptedText);
    }
}
