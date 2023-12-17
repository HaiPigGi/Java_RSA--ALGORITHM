package RSA_Algorithm;

import java.math.BigInteger;

public class RSAKeyPair {
      private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    public RSAKeyPair(BigInteger p, BigInteger q, BigInteger e) {
        this.p = p;
        this.q = q;
        this.n = p.multiply(q);
        this.phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        this.e = e;
        this.d = calculatePrivateKey(e, phi);
    }

    private BigInteger calculatePrivateKey(BigInteger e, BigInteger phi) {
        BigInteger k = BigInteger.ONE;
        while (true) {
            BigInteger ed = e.multiply(k);
            if (ed.mod(phi).equals(BigInteger.ONE)) {
                return k;
            }
            k = k.add(BigInteger.ONE);
        }
    }

    public BigInteger getModulus() {
        return n;
    }

    public BigInteger getPublicKey() {
        return e;
    }

    public BigInteger getPrivateKey() {
        return d;
    }

    public void printKeyDetails() {
        System.out.println("Nilai n: " + n);
        System.out.println("Kunci Publik e: " + e);
        System.out.println("Kunci Privat d: " + d);
    }
}
