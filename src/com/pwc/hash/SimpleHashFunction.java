package com.pwc.hash;

public class SimpleHashFunction extends AbstractHashFunction {

    private final int seed;

    public SimpleHashFunction(int seed) {
        this.seed = seed;
    }

    @Override
    public int hash(String value) {
        int result = 0;
        for (int i = 0; i < value.length(); i++) {
            result = seed * result + value.charAt(i);
        }
        return BloomFilter.DEFAULT_SIZE - 1 & result;
    }
}
