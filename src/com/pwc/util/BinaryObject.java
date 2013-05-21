package com.pwc.util;

import com.pwc.array.Array;
import com.pwc.io.DataInputBufferStream;

import java.io.*;

public class BinaryObject {

    public static int getSizeOfFile(String fileName) throws IOException {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        int length = 0;
        try {
            length = (int) file.length() / 4;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            file.close();
        }
        if (length == 0) {
            throw new RuntimeException();
        }
        return length;
    }

    public static void write(int[] a, String fileName) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
        int off = 0;
        int BUFFER_SIZE = 1024 * 1024;            // 1M
        byte[] buffer = new byte[BUFFER_SIZE * 4];  // 4MB
        for (int i = 0; i < a.length; i++) {
            Data.addIntToByteArray(a[i], buffer, off);
            off++;
            if (off == BUFFER_SIZE) {
                off = 0;
                out.write(buffer);
                continue;
            }
            if (i == a.length - 1) {
                out.write(buffer, 0, off * 4);
                break;
            }
        }
        out.flush();
        out.close();
    }

    public static int[] loadAsIntArray(String filename) throws IOException {
        int length = getSizeOfFile(filename);
        DataInputBufferStream in = new DataInputBufferStream(new FileInputStream(filename));
        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = in.getInt();
        }
        in.close();
        return a;
    }

    public static int[] loadAsIntArray2(String fileName) throws IOException {
        int length = getSizeOfFile(fileName);
        DataInputBufferStream in = new DataInputBufferStream(new FileInputStream(fileName));

        int[] a = new int[length];
        for (int i = 0; i < length; i++) {
            a[i] = in.getInt();
        }
        in.close();
        return a;
    }

    public static void generateRandomNumber(int from, int to, String fileName) throws IOException {
        int[] a = getIntArray(from, to);
        Array.shuffle(a);
        BinaryObject.write(a, fileName);
    }

    public static void generateOrderNumber(int from, int to, String fileName) throws IOException {
        int[] a = getIntArray(from, to);
        BinaryObject.write(a, fileName);
    }

    private static int[] getIntArray(int from, int to) {
        int[] a = new int[to - from + 1];
        for (int i = from; i <= to; i++) {
            a[i] = i;
        }
        return a;
    }

    public static int[] get(String fileName, int off, int length) throws Exception {
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        byte[] bytes = new byte[length];
        try {
            file.seek(off);
            file.read(bytes);
        } catch (IOException e) {

        } finally {
            try {
                file.close();
            } catch (IOException e) {

            }
        }
        return Convert.BytesToIntArray(bytes);
    }


}
