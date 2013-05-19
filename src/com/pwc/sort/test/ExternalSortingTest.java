package com.pwc.sort.test;

import com.pwc.sort.BitMap;
import com.pwc.sort.ExternalSorting;
import com.pwc.test.TestBase;
import com.pwc.util.BinaryObject;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class ExternalSortingTest extends TestBase {
    @Test
    public void testSort() throws Exception {
        final int from = 0;
        final int to = 1024 * 1024 * 10 - 1;
        final String source = "external_sorting_test";

        BinaryObject.generateRandomNumber(from, to, source);
        gc.add(source);
        ExternalSorting externalSorting = new ExternalSorting(source);
        String out = externalSorting.sort();
        gc.add(out);
        int[] a = BinaryObject.loadAsIntArray(out);
        for (int i = from; i <= to; i++) {
            assertEquals(i, a[i]);
        }
    }
}
