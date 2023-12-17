import java.math.BigInteger;

public class RSA {

    private static BigInteger p = BigInteger.valueOf(47);
    private static BigInteger q = BigInteger.valueOf(71);
     private static BigInteger n = p.multiply(q);
    private static BigInteger phi = (p.subtract((BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE)));
    public static void main(String[] args) {
        //Pilih Kunci Publik e
        BigInteger e = BigInteger.valueOf(79);
        
        // Hitung Kunci Private d
        BigInteger d = phi.modInverse(e);

        // pesan yang akan di enkripsi

        String plainText = "HARI INI";
        byte [] plainTextBytes= plainText.getBytes();

        //enkripsi pesan
        byte[] cipherTextBytes = encrypt(plainTextBytes, e, n);
    }



    private static byte [] encrypt (byte[] plainTextBytes, BigInteger e, BigInteger n) {
        BigInteger CipherText = new BigInteger(1, plainTextBytes);
        CipherText= CipherText.modPow(e, n);
        return CipherText.toByteArray();
    }

     private static byte [] decypt (byte[] cipherTextBytes, BigInteger d, BigInteger n) {
        BigInteger plainText = new BigInteger(1, cipherTextBytes);
        plainText= plainText.modPow(d, n);
        return plainText.toByteArray();
    }
    

}
