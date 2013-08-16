package com.pwc.brains.tool;

import com.pwc.brains.btree.ObjectSerializationException;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class KnifeTest {

    @Test
    public void test() throws ObjectSerializationException {
        String content = "中华人民共和国公安部门";
        String[] expected = new String[]{"中华","人民","共和国","公安部门"};
        String[] result = Knife.cut(content);
        assertTrue(isArrayEquals(expected,result));
    }

    @Test
    public void test2() throws ObjectSerializationException {
        String content = "后缀是一种重要的构词法,通过后缀常常可以判断出一个词的词性。一个英语单词可以分为三个部分：前缀（prefix），词根（stem）及后缀（suffix）。单词中位于词根前面的";
        String[] result = Knife.cut(content);
        String[] expected =new String[]{"后缀","是","一种","重要","的","构词","法",",","通过","后缀","常常","可以","判断","出","一个","词","的","词性","。","一个","英语","单词","可以","分为","三个","部分","：","前缀","（","prefix","）","，","词根","（","stem","）","及","后缀","（","suffix","）","。","单词","中","位于","词根","前面"};
        assertTrue(isArrayEquals(expected,result));
    }

    public boolean isArrayEquals(String[] a1, String[] a2) {
        if (a1 == a2) {
            return true;
        }

        if (a1 == null || a2 == null) {
            return false;
        }

        if (a1.length != a2.length) {
            return false;
        }

        for (int i = 0; i < a1.length; i++) {
            if (!a1[i].equals(a2[i])) {
                return false;
            }
        }

        return true;
    }


}
