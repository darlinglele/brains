package com.pwc.dynamic.test;

import com.pwc.dynamic.LCS;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class LCSTest {
    @Test
    public void LCS_0() {
        String a = "abcd";
        String b = "ebcd";
        int length = LCS.length(a, b);
        assertEquals(3, length);
    }

    @Test
    public void LCS_1() {
        String a = "abcd";
        String b = "";
        int length = LCS.length(a, b);
        assertEquals(0, length);
    }

    @Test
    public void LCS_2() {
        String a = "";
        String b = "abcd";
        int length = LCS.length(a, b);
        assertEquals(0, length);
    }

    @Test
    public void LCS_3() {
        String a = "acabcdd";
        String b = "abcd";
        int length = LCS.length(a, b);
        assertEquals(4, length);
    }

    @Test
    public void LCS_4() {
        String a = "abdcdbacded";
        String b = "adbcdcaede";
        String c = LCS.get(a, b);
        assertEquals("abcde", c);
    }
}
