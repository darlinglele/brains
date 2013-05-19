package com.pwc.util;

public class Convert {
    public static byte[] intToByte(int i) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (i & 0xff);
        bytes[2] = (byte) ((i >> 8) & 0xff);
        bytes[1] = (byte) ((i >> 16) & 0xff);
        bytes[0] = (byte) ((i >> 24) & 0xff);
        return bytes;
    }

    public static int[] BytesToIntArray(byte[] bytes) throws Exception {
        if (bytes.length % 4 != 0) {
            throw new Exception("");
        }
        int[] array = new int[bytes.length / 4];
        for (int i = 0; i < bytes.length; i += 4) {
            array[i / 4] = (((int) bytes[i] & 0xff) << 24) +
                    (((int) bytes[i + 1] & 0xff) << 16) +
                    (((int) bytes[i + 2] & 0xff) << 8) +
                    (((int) bytes[i + 3] & 0xff) << 0);
        }
        return array;
    }
}
