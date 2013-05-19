package com.pwc.io;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInputBufferStream extends DataInputStream {

    private int off = 0;
    private int size = 0;
    private final int bufferSize = 1024 * 1024; // 1M
    private byte[] buffer = new byte[bufferSize * 4];


    /**
     * Creates a DataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param in the specified input stream
     */
    public DataInputBufferStream(InputStream in) {
        super(in);
    }


    public int getInt() throws IOException {
        if (off * 4 < size) {
            int i = ((int) buffer[off * 4 + 0] << 24) +
                    ((int) (buffer[off * 4 + 1] & 0xff) << 16) +
                    ((int) (buffer[off * 4 + 2] & 0xff) << 8) +
                    (int) (buffer[off * 4 + 3] & 0xff << 0);
            off++;
            return i;
        } else {
            loadBuffer();
            return getInt();
        }
    }


    private void loadBuffer() throws IOException {
        size = in.read(buffer);
        off = 0;
    }
}
