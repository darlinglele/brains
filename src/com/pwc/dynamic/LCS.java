package com.pwc.dynamic;

public class LCS {
    public static int length(String a, String b) {
        int[][] c = new int[a.length() + 1][b.length() + 1];
        calculate(a, b, c);
        return c[a.length()][b.length()];
    }

    public static String get(String a, String b) {
        int[][] c = new int[a.length() + 1][b.length() + 1];
        calculate(a, b, c);
        StringBuilder result = new StringBuilder();
        getResult(c, a, a.length(), b.length(), result);
        return result.toString();

    }

    private static void getResult(int[][] c, String a, int i, int j, StringBuilder result) {
        if (i == 0 || j == 0) {
            return;
        }
        if (c[i][j] == 1) {
            result.append(a.charAt(i-1));
            getResult(c, a, i - 1, j - 1, result);
        } else if (c[i][j] == 0) {
            getResult(c, a, i - 1, j, result);
        } else {
            getResult(c, a, i, j - 1, result);
        }
    }

    private static void calculate(String a, String b, int[][] c) {
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                }
                if (a.charAt(i - 1) != b.charAt(j - 1)) {
                    c[i][j] = c[i][j - 1] > c[i - 1][j] ? c[i][j - 1] : c[i - 1][j];
                }
            }
        }
    }

}
