package com.pwc.brains;

import com.pwc.brains.btree.ObjectSerializationException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Util {
    public static String getUuid() {
        return UUID.randomUUID().toString();
    }

    public static ArrayList<Integer> randomRange(int s, int e) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = s; i < e; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        return list;
    }

    public static void serialize(String fileName, Object node) throws ObjectSerializationException {
        FileOutputStream os = null;
        ObjectOutputStream o = null;
        try {
            os = new FileOutputStream(fileName);
            o = new ObjectOutputStream(os);
        } catch (FileNotFoundException e) {
            throw new ObjectSerializationException("Unable to find or access the file:" + fileName, e);

        } catch (IOException e) {
            throw new ObjectSerializationException("Unable to write the file:" + fileName, e);
        }

        try {
            o.writeObject(node);
            o.flush();
        } catch (IOException e) {
            throw new ObjectSerializationException(e.getMessage(), e);
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                // I don't how to handle this kind error, throw a checked exception?
            }
        }
    }

    public static void serialize(String fileName, Object node, String type) throws ObjectSerializationException {
        if (type == "xml") {

        }
    }

    public static Object deserialize(String fileName) throws ObjectSerializationException {
        Object object = null;
        FileInputStream input = null;
        try {
            input = new FileInputStream(fileName);
            ObjectInputStream objInput = new ObjectInputStream(input);
            object = (Object) objInput.readObject();
            return object;
        } catch (Exception e) {
            throw new ObjectSerializationException("Unable to loadDictionary the node from file:", e);
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            return object;
        }
    }

    public static boolean isFileExists(String path) {
        File f = new File(path);
        return f.exists();
    }

    public static void Print(int[][] prices) {
        int h = prices[0].length;
        int v = prices.length;
        for (int i = -1; i < v; i++) {
            if (i == -1) {
                printHead(h);
            } else {
                print(prices[i]);
            }
            System.out.println("");
        }

    }

    private static void printHead(int h) {
        for (int i = 0; i < h; i++) {
            System.out.print(i + blank(String.valueOf(i)));
        }
    }

    private static void print(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + blank(String.valueOf(array[i])));
        }
    }

    private static String blank(String s) {
        int total = 5;
        String result = "";
        for (int i = 0; i < total - s.length(); i++) {
            result += " ";
        }
        return result;
    }


}
