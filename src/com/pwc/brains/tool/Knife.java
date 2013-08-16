package com.pwc.brains.tool;

import com.pwc.brains.btree.ObjectSerializationException;
import com.pwc.brains.dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class Knife {
    public static String[] cut(String content) throws ObjectSerializationException {
        Dictionary dic = Dictionary.load("chinese.dic");
        int start = 0, bound = 1, end = 0;
        List<String> results = new ArrayList<String>();
        while (start < content.length() - 1) {
            String current = content.substring(start, end + 1);
            if (dic.isPrefix(current) && dic.isBound(current)) {
                end++;
                bound = end;
                continue;
            } else if (dic.isPrefix(current)) {
                end++;
                continue;
            } else if (dic.isBound(current)) {
                results.add(content.substring(start, end + 1));
                end++;
                start=end;
                bound=start+1;
            } else {
                results.add(content.substring(start,bound));
                start = bound;
                end = start;
                bound++;
            }

        }
        return results.toArray(new String[]{});
    }

}
