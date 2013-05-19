package com.pwc.array;

import com.pwc.sort.MergeSort;

import java.util.Random;

public class Array {
    private static final Random random = new Random();

    public static void shuffle(int[] array) {
        int size = array.length;
        for (int i = size; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    public static int[] sort(int[] array) {
        return MergeSort.sort(array);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static boolean compare(int[] expect, int[] actual) {
        if (expect.length != actual.length) {
            return false;
        }
        for (int i = 0; i < expect.length; i++) {
            if (expect[i] != actual[i]) {
                return false;
            }
        }
        return true;
    }
}
