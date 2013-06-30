package com.pwc.dynamic;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ChangeTest {
    @Test
    public void testChange() {
        Change change = new Change(63);
        int[] values = new int[]{1, 2, 5, 10, 20, 50, 100};
        int best = change.calculate(values);
        assertEquals(4, best);
    }

    @Test
    public void testChange2() {
        Change change = new Change(99);
        int[] values = new int[]{1, 2, 5, 10, 20, 50, 100};
        int best = change.calculate(values);
        assertEquals(6, best);
    }

    @Test
    public void testChange3() {
        Change change = new Change(99);
        int[] values = new int[]{1, 2, 5, 20, 10, 50, 100};
        int best = change.calculate(values);
        assertEquals(6, best);
    }

    @Test
    public void testChange4() {
        Change change = new Change(63);
        int[] values = new int[]{1, 2, 5, 10, 20, 100};
        int best = change.calculate(values);
        assertEquals(5, best);
    }

    @Test
    public void testChange5() {
        Change change = new Change(63);
        int[] values = new int[]{1, 2, 20, 50, 100};
        int best = change.calculate(values);
        assertEquals(5, best);
    }

    @Test
    public void testBest() {
        Change change = new Change(99);
        int[] values = new int[]{1, 2, 5, 10, 20, 50, 100};
        int[] best = change.best(values);
        int[] expected = new int[]{0, 2, 1, 0, 2, 1, 0};
        assertEquals(expected.length, best.length);
        for (int i = 0; i < best.length; i++) {
            assertEquals(expected[i], best[i]);
        }
    }


}
