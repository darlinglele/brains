package com.pwc.dynamic;

public class Backpack {
    private final int capacity;

    public Backpack(int capacity) {
        this.capacity = capacity;
    }

    public int capacity() {
        return this.capacity;
    }

    public int max(Goods[] goods) {

        int[][] prices = new int[goods.length + 1][capacity + 1];

        for (int i = 1; i < goods.length + 1; i++) {
            int v = goods[i - 1].volume();
            int p = goods[i - 1].price();
            for (int c = 0; c < capacity + 1; c++) {
                if (c < v) {
                    prices[i][c] = prices[i - 1][c];
                } else {
                    prices[i][c] = max(prices[i - 1][c], prices[i - 1][c - v] + p);
                }
            }
        }
        return prices[goods.length][capacity];
    }


    private int max(int i, int j) {
        return i > j ? i : j;
    }
}
