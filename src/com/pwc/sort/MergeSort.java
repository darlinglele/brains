package com.pwc.sort;

public class MergeSort {
    public static int[] sort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int haft = array.length / 2;
        int[] left = new int[haft];
        int[] right = new int[array.length - haft];
        System.arraycopy(array, 0, left, 0, left.length);
        System.arraycopy(array, haft, right, 0, right.length);
        left = sort(left);
        right = sort(right);
        return MergeSort.merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (true) {
            if (i == left.length) {
                System.arraycopy(right, j, result, k, result.length - k);
                break;
            }
            if (j == right.length) {
                System.arraycopy(left, i, result, k, result.length - k);
                break;
            }
            if (left[i] <= right[j]) {
                result[k] = left[i];
                i++;
            } else {
                result[k] = right[j];
                j++;
            }
            k++;
        }
        return result;
    }
}
