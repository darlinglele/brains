package com.pwc.dynamic;

//F(i,x) = MIN(F(i-1, x-k*C(i))+k
//C(i) is the value of i

public class Change {

    private final int total;
    protected int[][] records;

    public Change(int total) {
        this.total = total;
    }

    public int total() {
        return this.total;
    }

    public synchronized int calculate(int[] values) {
        records = new int[values.length + 1][total + 1];
        for (int i = 0; i < total + 1; i++) {
            records[0][i] = i * 10;
        }

        for (int i = 1; i <= values.length; i++) {
            for (int c = 0; c <= total; c++) {
                if (c < values[i - 1]) {
                    records[i][c] = records[i - 1][c];
                } else {
                    records[i][c] = min(i, values[i - 1], c);
                }
            }
        }
        return records[values.length][total];
    }

    private int min(int index, int value, int total) {
        int sum = Integer.MAX_VALUE;
        for (int k = 0; k * value <= total; k++) {
            if (records[index - 1][total - k * value] + k < sum) {
                sum = records[index - 1][total - k * value] + k;
            }
        }
        return sum;
    }


    public int[] best(int[] values) {
        calculate(values);
        for (int i = records.length; i > 0; i--) {
            if (records[i][total] > records[i - 1][total]) {

            }
        }
        return null;
    }
}
