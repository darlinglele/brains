package com.pwc.brains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class Util {
    static String getUuid() {
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
}
