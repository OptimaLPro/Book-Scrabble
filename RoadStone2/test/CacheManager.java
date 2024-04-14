package test;

import java.util.HashSet;

public class CacheManager {
    private CacheReplacementPolicy crp;
    private int size;
    HashSet<String> cache;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
        cache = new HashSet<>();
    }

    public boolean query(String word) {
        if (cache.contains(word)) {
            return true;
        }
        if (cache.size() == size) {
            String removed = crp.remove();
            cache.remove(removed);
        }
        crp.add(word);
        cache.add(word);
        return false;
    }

    public void add(String word) {
        if (cache.size() == size) {
            String removed = crp.remove();
            cache.remove(removed);
        }
        crp.add(word);
        cache.add(word);
    }
	

}
