package com.pwc.array.test;

import com.pwc.array.Array;
import com.pwc.sort.MergeSort;
import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayTest {

    @Test
    public void testShuffle() {
        int[] array = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] result = new int[10][10];

        int time = 10000000;
        int average = time / 10;
        for (int i = 0; i < time; i++) {
            Array.shuffle(array);
            for (int j = 0; j < array.length; j++) {
                result[array[j]][j]++;
            }
        }
        //每个int在任何一个位置出现是等可能得，通过多次运行结果的统计来判断
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                //检查误差不超过1/100
                Assert.assertTrue(Math.abs(result[i][j] - average) < (average / 100));
            }
        }
    }

    @Test
    public void testSort() {
        int[] array = {0, 3, 2, 1, 7, 6, 5, 4, 9, 8};
        int[] expect = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] actual = MergeSort.sort(array);
        assertEquals(expect.length, actual.length);
        assertTrue(Array.compare(expect, actual));
    }

    @Test
    public void test() {
        byte a = 81;
        for (; a > 0; --a) {
            if (a / 9 % 3 == a % 3) {
                continue;
            }
            System.out.println(a / 9 + ": " + a % 9);
        }
    }

    @Test
    public void test2() {
        byte a = 9;
        for (; a > 0; --a) {
            byte b = 9;
            for (; b > 0; --b) {
                if (b % a == 0) {
                    continue;
                }
                System.out.println(a + ": " + b);
            }
        }
    }


}
