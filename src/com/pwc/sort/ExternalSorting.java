package com.pwc.sort;

import com.pwc.io.DataOutputBufferStream;
import com.pwc.util.BinaryObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

public class ExternalSorting {
    private final String in;
    private final String out;
    private int blockSize = 1024 * 1024;// 1MB
    private List<Queue<Integer>> queues;

    public ExternalSorting(String in) {
        this.in = in;
        this.out = this.in + "-sorted";
    }

    public ExternalSorting(String in, int blockSize) {
        this(in);
        this.blockSize = blockSize;
    }

    public String sort() throws Exception {
        int total = BinaryObject.getSizeOfFile(this.in);
        int blocks = total / blockSize;
        if (total % blockSize != 0) {
            blocks++;
        }
        if (blocks == 1) {
            int[] array = BinaryObject.get(this.in, 0, total);
            array = MergeSort.sort(array);
            BinaryObject.write(array, this.in + "-out");
            return this.out;
        } else {
            divideAndConquer(blocks, total);
        }

        merge(blocks);

        return this.out;
    }

    private void merge(int blocks) throws IOException {
//        DataOutputBufferStream out = new DataOutputBufferStream(new FileOutputStream(this.out));
//        while (true) {
//            out.write(min(blocks));
//        }
//
//        out.flush();
    }

    private int min(int blocks) {
        int min = 0;
        for (int i = 0; i < blocks; i++) {
            Integer head = queues.get(i).poll();
            if (head < min) {
                min = head;
            }
        }
        return min;
    }

    private void put() {

    }

    private void flush() {
//        outBuffer
    }

    private boolean isBufferFull() {

        return false;
    }

    private void divideAndConquer(int blocks, int total) throws Exception {
        for (int index = 0; index < blocks; index++) {
            int off = blockSize * index;
            divide(in, off, blockSize);
        }
        // last block
        int off = (blocks - 1) * blockSize;
        int length = total - off;
        divide(in, off, length);
    }

    private void divide(String in, int off, int length) throws Exception {
        int[] array = BinaryObject.get(in, off, length);
        array = MergeSort.sort(array);
        BinaryObject.write(array, String.valueOf(off));
    }
}
