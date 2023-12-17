import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class RSAExample {

    public static void main(String[] args) {
        // Parameter RSA
        BigInteger p = BigInteger.valueOf(47);
        BigInteger q = BigInteger.valueOf(71);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = BigInteger.valueOf(79);

        // Hitung kunci privat d
        BigInteger d = calculatePrivateKey(e, phi);

        // Tampilkan nilai n, e, dan d
        System.out.println("Nilai n: " + n);
        System.out.println("Kunci Publik e: " + e);
        System.out.println("Kunci Privat d: " + d);

        // Pesan yang akan dienkripsi
        String plainText = "HARIINI";

        // Konversi pesan menjadi blok-blok 3 digit ASCII
        BigInteger[] blocks = convertToBlocks(plainText);

        // Enkripsi setiap blok
        BigInteger[] encryptedBlocks = encryptBlocks(blocks, e, n);

        // Tampilkan hasil enkripsi
        System.out.print("Hasil Enkripsi C = ");
        for (BigInteger block : encryptedBlocks) {
            System.out.print(block.toString() + " ");
        }
        System.out.println();

        // Iterasi untuk Enkripsi
        encryptIteration(blocks, e, n);

        // Dekripsi menggunakan kunci privat d
        BigInteger[] decryptedBlocks = decryptBlocks(encryptedBlocks, d, n);

        // Tampilkan hasil dekripsi
        System.out.print("Hasil Dekripsi M = ");
        for (BigInteger block : decryptedBlocks) {
            System.out.print(block.toString() + " ");
        }
        System.out.println();

        // Iterasi untuk Dekripsi
        decryptIteration(decryptedBlocks, d, n);
    }

    // Menghitung kunci privat d
    private static BigInteger calculatePrivateKey(BigInteger e, BigInteger phi) {
        BigInteger k = BigInteger.ONE;
        while (true) {
            BigInteger ed = e.multiply(k);
            if (ed.mod(phi).equals(BigInteger.ONE)) {
                return k;
            }
            k = k.add(BigInteger.ONE);
        }
    }

    // Konversi pesan menjadi blok-blok 3 digit ASCII
    private static BigInteger[] convertToBlocks(String plainText) {
        byte[] plainTextBytes = plainText.getBytes(StandardCharsets.US_ASCII);
        BigInteger[] blocks = new BigInteger[plainTextBytes.length];

        for (int i = 0; i < plainTextBytes.length; i++) {
            blocks[i] = BigInteger.valueOf(plainTextBytes[i]);
        }

        return blocks;
    }

    // Enkripsi setiap blok
    private static BigInteger[] encryptBlocks(BigInteger[] blocks, BigInteger e, BigInteger n) {
        BigInteger[] encryptedBlocks = new BigInteger[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            encryptedBlocks[i] = blocks[i].modPow(e, n);
        }
        return encryptedBlocks;
    }

    // Dekripsi setiap blok
    private static BigInteger[] decryptBlocks(BigInteger[] encryptedBlocks, BigInteger d, BigInteger n) {
        BigInteger[] decryptedBlocks = new BigInteger[encryptedBlocks.length];
        for (int i = 0; i < encryptedBlocks.length; i++) {
            decryptedBlocks[i] = encryptedBlocks[i].modPow(d, n);
        }
        return decryptedBlocks;
    }

    // Menggabungkan blok-blok menjadi teks
    private static String combineBlocks(BigInteger[] blocks) {
        byte[] decryptedBytes = new byte[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            decryptedBytes[i] = blocks[i].byteValue();
        }
        return new String(decryptedBytes, StandardCharsets.US_ASCII);
    }

    // Iterasi untuk Enkripsi
    private static void encryptIteration(BigInteger[] blocks, BigInteger e, BigInteger n) {
        System.out.println("Iterasi untuk Enkripsi:");
        for (int i = 0; i < blocks.length; i++) {
            System.out.println("Enkripsi blok '" + blocks[i] + "':");
            System.out.println("C = " + blocks[i] + "^" + e + " % " + n);
            System.out.println("Hasil Enkripsi C = " + blocks[i].modPow(e, n));
        }
        System.out.println();
    }

    // Iterasi untuk Dekripsi
    private static void decryptIteration(BigInteger[] blocks, BigInteger d, BigInteger n) {
        System.out.println("Iterasi untuk Dekripsi:");
        for (int i = 0; i < blocks.length; i++) {
            System.out.println("Dekripsi blok '" + blocks[i] + "':");
            System.out.println("M = " + blocks[i] + "^" + d + " % " + n);
            System.out.println("Hasil Dekripsi M = " + blocks[i].modPow(d, n));
        }
    }
}
