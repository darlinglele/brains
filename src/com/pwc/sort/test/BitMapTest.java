package com.pwc.sort.test;

import com.pwc.sort.BitMap;
import com.pwc.test.TestBase;
import com.pwc.util.BinaryObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BitMapTest extends TestBase {
    @Test
    public void testSort() throws Exception {
        final int from = 0;
        final int to = 1024 * 1024 * 10 - 1;
        final String fileName = "bitmap_sort_test";

        BinaryObject.generateRandomNumber(from, to, fileName);
        gc.add(fileName);
        BitMap map = new BitMap(fileName);
        String file = map.sort();
        gc.add(file);
        int[] a = BinaryObject.loadAsIntArray(file);

        for (int i = from; i <= to; i++) {
            assertEquals(i, a[i]);
        }
    }
}
