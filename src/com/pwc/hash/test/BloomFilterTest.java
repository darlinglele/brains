package com.pwc.hash.test;

import com.pwc.hash.BloomFilter;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BloomFilterTest {
    @Test
    public void testContains() throws Exception {
        String email = "maninbehind@gmail.com";
        BloomFilter filter = new BloomFilter();
        filter.add(email);
        assertTrue(filter.contains(email));
    }

    @Test
    public void testNotContains() throws Exception {
        String email = "maninbehind@gmail.com";
        BloomFilter filter = new BloomFilter();
        assertFalse(filter.contains(email));
    }

    @Test
    public void test() throws Exception {

    }
}
