package com.pwc.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.pwc.util.Convert.intToByte;

public class DataOutputBufferStream extends DataOutputStream {
    private final int bufferSize = 1024 * 1024;
    private byte[] buffer = new byte[bufferSize * 4];
    private int size = 0;

    public DataOutputBufferStream(OutputStream out) {
        super(out);
    }

    public synchronized void write(int i) throws IOException {
        if (isBufferFull()) {
            this.flush();
            this.put(i);
        } else {
            put(i);
        }
    }

    private void put(int i) {
        byte[] temp = intToByte(i);
        buffer[size * 4] = temp[0];
        buffer[size * 4 + 1] = temp[1];
        buffer[size * 4 + 2] = temp[2];
        buffer[size * 4 + 3] = temp[3];
        this.size++;
    }


    @Override
    public synchronized void flush() throws IOException {
        if (this.size != 0) {
            out.write(buffer, 0, size * 4);
            this.size = 0;
        }
        out.flush();
    }

    private boolean isBufferFull() {
        return this.size == bufferSize;
    }

}
