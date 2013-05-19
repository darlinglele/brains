package com.pwc.sort;

import com.pwc.util.BinaryObject;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExternalSorting {
    private final String source;
    private final String out;
    private int size = 1024 * 1024;// 1MB

    public ExternalSorting(String source) {
        this.source = source;
        this.out = this.source + "-sorted";
    }

    public ExternalSorting(String source, int size) {
        this(source);
        this.size = size;
    }

    public String sort() throws Exception {
        divideAndConquer();
        merge();
        return this.out;
    }

    private void merge() {

    }

    private void divideAndConquer() throws Exception {
        int total = BinaryObject.getSizeOfFile(this.source);
        int pages = total / size;
        for (int i = 0; i < pages; i++) {
            divide(source, size, i);
        }
        if ((total % size) != 0) {
            divide(source, total % size, pages);
        } else {
            divide(source, size, pages);
        }
    }

    private void divide(String source, int size, int number) throws Exception {
        int[] array = BinaryObject.get(source, size * number, size);
        array = MergeSort.sort(array);
        BinaryObject.write(array, String.valueOf(number));
    }
}
