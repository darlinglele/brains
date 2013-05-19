package com.pwc.sort.test;

import com.pwc.array.Array;
import com.pwc.sort.MergeSort;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MergeSortTest {
    @Test
    public void testSort() throws Exception {
        int[] array = {0, 3, 2, 1, 7, 6, 5, 4, 9, 8};
        int[] expect = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actual = MergeSort.sort(array);
        assertEquals(expect.length, actual.length);
        assertTrue(Array.compare(expect, actual));
    }

    @Test
    public void testSortAnEmptyArray() throws Exception {
        int[] array = {};
        int[] expect = {};
        int[] actual = MergeSort.sort(array);
        assertEquals(expect.length, actual.length);
        assertTrue(Array.compare(expect, actual));
    }

    @Test
    public void testSortArrayWithOneElements() throws Exception {
        int[] array = {1};
        int[] expect = {1};
        int[] actual = MergeSort.sort(array);
        assertEquals(expect.length, actual.length);
        assertTrue(Array.compare(expect, actual));
    }

    @Test
    public void testSortArrayWithOddElements() throws Exception {
        int[] array = {1, 3, 2};
        int[] expect = {1, 2, 3};
        int[] actual = MergeSort.sort(array);
        assertEquals(expect.length, actual.length);
        assertTrue(Array.compare(expect, actual));
    }

}


