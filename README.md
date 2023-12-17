# RSA Encryption Algorithm

## Overview

RSA is a widely used asymmetric cryptographic algorithm that provides a secure method for transmitting and storing sensitive information. It is named after its inventors Ron Rivest, Adi Shamir, and Leonard Adleman, who introduced it in 1977.

Asymmetric cryptography involves the use of a pair of keys: a public key for encryption and a private key for decryption. RSA is based on the mathematical properties of large prime numbers, making it difficult to break using traditional methods.

## Key Generation

1. **Choose Two Large Prime Numbers (p and q):**
   - Select two large prime numbers, p and q.
   - Calculate their product, n = p * q.

2. **Compute Euler's Totient Function (φ):**
   - Calculate φ(n) = (p - 1) * (q - 1).

3. **Select Public Key (e):**
   - Choose a public exponent (e) that is relatively prime to φ(n), often a small prime number like 65537.

4. **Compute Private Key (d):**
   - Calculate the private exponent (d) such that (e * d) % φ(n) = 1.

## Encryption Process

1. **Convert Message to Numeric Value:**
   - Represent the plaintext message as a numeric value M.

2. **Encrypt Message:**
   - Compute the ciphertext C = M^e % n.

3. **Transmit or Store Ciphertext:**
   - Send or store the ciphertext C.

## Decryption Process

1. **Decrypt Ciphertext:**
   - Compute the original message M = C^d % n.

2. **Convert Numeric Value to Message:**
   - Represent the numeric value M as the decrypted plaintext message.

## Security Considerations

- The security of RSA relies on the difficulty of factoring the product of two large prime numbers.
- Longer key lengths (e.g., 2048 or 4096 bits) provide higher security but may require more computation.

## Implementation

- Use reliable cryptographic libraries or APIs for RSA implementation.
- Ensure the secure generation and storage of keys.

## Example Code

```java
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
