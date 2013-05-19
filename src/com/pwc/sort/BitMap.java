package com.pwc.sort;

import com.pwc.io.DataInputBufferStream;
import com.pwc.io.DataOutputBufferStream;
import com.pwc.util.BinaryObject;

import java.io.*;
import java.util.BitSet;

public class BitMap {
    private int length;
    private BitSet bitSet = null;
    private final String fileName;

    public BitMap(String fileName) throws IOException {
        this.fileName = fileName;
        length = BinaryObject.getSizeOfFile(fileName);
        bitSet = new BitSet(length);
    }

    private void mapIntToBit() throws IOException {
        DataInputBufferStream in = new DataInputBufferStream(new FileInputStream(this.fileName));
        for (int i = 0; i < length; i++) {
            bitSet.set(in.getInt(), true);
        }
        in.close();
    }

    public String sort() throws IOException {
        mapIntToBit();
        readBitToInt();
        return fileName + "-sorted";
    }

    private void readBitToInt() throws IOException {
        DataOutputBufferStream out = new DataOutputBufferStream(new FileOutputStream(fileName + "-sorted"));
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                out.write(i);
            }
        }
        out.flush();
        out.close();
    }
}


