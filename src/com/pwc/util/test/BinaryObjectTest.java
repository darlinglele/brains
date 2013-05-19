package com.pwc.util.test;

import com.pwc.util.BinaryObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BinaryObjectTest {
    @Test
    public void testLoadAsIntArray() throws Exception {
        int from = 0;
        int to = 1023;
        String fileName = "binary_load_as_int_array";
        BinaryObject.generateRandomNumber(from, to, fileName);
        int[] b = BinaryObject.loadAsIntArray2(fileName);
        int[] a = BinaryObject.loadAsIntArray(fileName);
        for (int i = from; i <= to; i++) {
            assertEquals(a[i], b[i]);
        }
    }
}
