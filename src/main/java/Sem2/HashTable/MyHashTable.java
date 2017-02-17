package Sem2.HashTable;

import java.util.ArrayList;

public class MyHashTable {
    ArrayList<Bucket> buckets = new ArrayList<>();

    public MyHashTable(int n) {
        for (int i = 0; i < n; i++) {
           buckets.add(new Bucket());
        }
    }

    private Bucket getBucket(String k) {
        int h = k.hashCode();
        int index = Math.abs(h % buckets.size());
        return buckets.get(index);
    }
    public String getK(String k) {
        Bucket b = getBucket(k);
        for (Pair p : b.pairs) {
            if (p.key.equals(k)){
                return p.value;
            }
        }
        return  null;
    }

    public void put(String k, String v) {
        Bucket b = getBucket(k);
        for (Pair p : b.pairs) {
            if (p.key.equals(k)){
                 p.value = v;
                return;
            }
        }
        b.pairs.add(new Pair(k, v));
    }
}
