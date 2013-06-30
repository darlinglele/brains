package com.pwc.dynamic;

public class Knapsack2 extends Knapsack {
    public Knapsack2(int capacity) {
        super(capacity);
    }

    @Override
    protected int max(int index, int size, int price, int capacity) {
        int sum = 0;
        for (int i = 0; i * size <= capacity; i++) {
            if (records[index - 1][capacity - i * size] + i * price > sum) {
                sum = records[index - 1][capacity - i * size] + i * price;
            }
        }
        return sum;
    }
}
