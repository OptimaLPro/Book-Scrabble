package test;

import java.util.BitSet;
import java.security.MessageDigest;
import java.math.BigInteger;

public class BloomFilter {
    private BitSet bitSet;
    private int size;
    private MessageDigest[] algs;

    public BloomFilter(int size, String... algs) {
        this.size = size;
        this.bitSet = new BitSet();
        try {
            this.algs = new MessageDigest[algs.length];
            for (int i = 0; i < algs.length; i++) {
                this.algs[i] = MessageDigest.getInstance(algs[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize BloomFilter");
        }
    }

    public void add(String word) {
        for (MessageDigest alg : algs) {
            byte[] digest = alg.digest(word.getBytes());
            BigInteger hashValue = new BigInteger(1, digest);
            int hash = Math.abs(hashValue.intValue()) % size;
            bitSet.set(hash);
        }
    }

    public boolean contains(String word) {
        for (MessageDigest alg : algs) {
            byte[] digest = alg.digest(word.getBytes());
            BigInteger hashValue = new BigInteger(1, digest);
            int hash = Math.abs(hashValue.intValue()) % size;
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < bitSet.length(); i++) {
            s += bitSet.get(i) ? "1" : "0";
        }
        return s;
    }

}
