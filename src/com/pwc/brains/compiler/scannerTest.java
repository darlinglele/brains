package com.pwc.brains.compiler;


import com.pwc.io.File;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class scannerTest {
    @Test
    public void scanVariableTest() {
        String content = "String fdsafasdf + - ++ += -= --++ if else * % ( ) 'c' ** {} dfd == 1212 str1 = \"lit\\'eral\"  ";
        Scanner scanner = new Scanner(content);
        System.out.println("[" + content + "]");
        String next;
        while ((next = scanner.next()) != null) {
            System.out.println("[" + next + "]");
        }
//        assertEquals(Type.Symbol, first.type);
//        assertEquals("String", first.value);
//        Token second = scanner.next();
//        assertEquals(Type.ID, second.type);
//        assertEquals("str1", second);
//        Token third = scanner.next();
//        assertEquals(Type.Assignment, third.type);
//        assertEquals("=", third.value);
//        Token fourth= scanner.next();
//        assertEquals(Type.Literal, third.type);
//        assertEquals("literal", third.value);
    }

    @Test
    public void testExpression() throws IOException {
        String content = File.readFileAsString("code.java");
        Scanner scanner = new Scanner(content);
        String next;
        while ((next = scanner.next()) != null) {
            System.out.println("[" + next + "]");
        }
    }

    @Test
    public void test() {

        System.out.println(f(1, 1).length);
        for (int i : f(1, 1)) {
            System.out.println(i);
        }
        System.out.println(cal(1, 100, 100000));

    }


    @Test
    public void teste() {
        System.out.println(palindrome("abcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxy"));
        System.out.println(palindrome(""));
        System.out.println("abcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxy".length());
    }


    public static int palindrome(String s) {
        if (s == null || s.length() > 100 || s.length() < 1) {
            return 0;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        if (!can(counts)) {
            return 0;
        }
        BigInteger divisor = BigInteger.ONE;
        for (int count : counts) {
            divisor = divisor.multiply(permutation(count / 2));
        }
        BigInteger result = permutation(s.length() / 2).divide(divisor);
        return result.mod(BigInteger.valueOf(1000000007)).intValue();
    }

    private static BigInteger permutation(int n) {
        if (n < 0)
            return BigInteger.ZERO;
        if (n == 0)
            return BigInteger.ONE;
        BigInteger result = BigInteger.ONE;
        while (n > 0) {
            result = result.multiply(BigInteger.valueOf(n));
            n--;
        }
        return result;
    }

    private static boolean can(int[] counts) {
        int oddCount = 0;
        for (int count : counts) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }

    private Integer[] f(int a, int b) {
        List<Integer> result = new ArrayList<>();
        result.add(a);
        if (!result.contains(b))
            result.add(b);
        Stack<Integer> temp = new Stack<Integer>();
        temp.add(0);

        while (true) {
            if (temp.size() <= 0)
                break;
            Integer x = temp.pop();

            int r1 = Math.abs(a - (b - x)) % a;

            if (!temp.contains(r1) && !result.contains(r1)) {
                temp.push(r1);
                result.add(r1);
            }

            int r2 = Math.abs(b - (a - x)) % b;

            if (!temp.contains(r2) && !result.contains(r2)) {
                temp.push(r2);
                result.add(r2);
            }
        }
        Integer[] t = result.toArray(new Integer[result.size()]);
        Arrays.sort(t);
        return t;
    }


    private boolean cal(int a, int b, int c) {
        return false;
    }


}

