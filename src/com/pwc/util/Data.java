package com.pwc.util;

import static com.pwc.util.Convert.intToByte;

public class Data {
    public static void addIntToByteArray(int i, byte[] b, int off) {
        byte[] temp = intToByte(i);
        b[off * 4] = temp[0];
        b[off * 4 + 1] = temp[1];
        b[off * 4 + 2] = temp[2];
        b[off * 4 + 3] = temp[3];
    }
}
