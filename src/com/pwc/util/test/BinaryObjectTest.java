package com.pwc.util.test;

import com.pwc.test.TestBase;
import com.pwc.util.BinaryObject;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BinaryObjectTest extends TestBase {
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

    @Test
    public void testGet() throws Exception {
        String source = "binary_get_test";
        gc.add(source);
        int length = 1024;
        int from = 0;
        int to = length * length * 10;
        BinaryObject.generateOrderNumber(from, to, source);
        int[] a = BinaryObject.get(source, from, length);
        for (int i = from; i < length / 4; i++) {
            assertEquals(i, a[i]);
        }
    }

    @Test
    public void testWrite() throws Exception {
        String source = "binary_get_test";
        gc.add(source);
        int length = 1024;
        int from = 0;
        int to = length * length * 10;
        BinaryObject.generateOrderNumber(from, to, source);
        int[] a = BinaryObject.get(source, from, length);
        for (int i = from; i < length / 4; i++) {
            assertEquals(i, a[i]);
        }
    }
}
