package com.pwc.hash;

import java.io.Serializable;
import java.util.BitSet;

public class BloomFilter implements Serializable {
    private final long serialVersionUID = 112343854;
    public static final int DEFAULT_SIZE = 1 << 24;
    private BitSet bits = new BitSet(DEFAULT_SIZE);
    private AbstractHashFunction[] function = new AbstractHashFunction[]{new SimpleHashFunction(13), new SimpleHashFunction(17)};

    public Boolean contains(String value) {
        if (value == null)
            return false;
        boolean result = true;
        for (AbstractHashFunction f : function) {
            result = result && bits.get(f.hash(value));
        }
        return result;
    }

    public void add(String value) {
        if (value == null)
            return;
        for (AbstractHashFunction f : function) {
            bits.set(f.hash(value), true);
        }
    }
}
