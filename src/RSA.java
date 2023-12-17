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
        String cipherText = new String (cipherTextBytes);

        //Dekripsi Pesan
        byte [] plainTextDecyptedBytes = decypt(cipherTextBytes, d, n);
        String plainTextDecrypted = new String(plainTextDecyptedBytes);


        //result
        System.out.println("Pesan Asli : "+plainText);
        System.out.println("kunci public : "+e);
        System.out.println("Kunci Private : "+d);
        System.out.println("CipherText : "+cipherText);
        System.out.println("Plain Text Dekripsi : "+plainTextDecrypted);

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
