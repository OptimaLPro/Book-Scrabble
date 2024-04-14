package test;

import java.util.BitSet;
import java.security.MessageDigest;
import java.math.BigInteger;
;

public class BloomFilter {
    private BitSet bitSet;
    private int size;
    // private int hashNum;
    private MessageDigest[] algs;
    private BigInteger[] hashValues;

    public BloomFilter(int size, String... algs) {
        this.size = size;
        this.bitSet = new BitSet(size);
        this.algs = new MessageDigest[algs.length];
        this.hashValues = new BigInteger[algs.length];
        try {
            for (int i = 0; i < algs.length; i++) {
                this.algs[i] = MessageDigest.getInstance(algs[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add(String word) {
        for (int i = 0; i < algs.length; i++) {
            algs[i].update(word.getBytes());
            byte[] digest = algs[i].digest();
            hashValues[i] = new BigInteger(1, digest);
            int hash = hashValues[i].intValue();
            hash = Math.abs(hash);
            hash = hash % size;
            bitSet.set(hash);
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < algs.length; i++) {
            algs[i].update(word.getBytes());
            byte[] digest = algs[i].digest();
            hashValues[i] = new BigInteger(1, digest);
            int hash = hashValues[i].intValue();
            hash = Math.abs(hash);
            hash = hash % size;
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return bitSet.toString();
    }


}
