package com.pwc.util;

import com.pwc.test.TestBase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BinaryObjectTest extends TestBase {
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
}
