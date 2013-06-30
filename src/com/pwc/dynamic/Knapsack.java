package com.pwc.dynamic;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
    private final int capacity;
    protected int[][] records;

    public Knapsack(int capacity) {
        this.capacity = capacity;
    }

    public int capacity() {
        return this.capacity;
    }

    public synchronized int calculate(Goods[] goods) {
        records = new int[goods.length + 1][capacity + 1];
        for (int i = 1; i <= goods.length; i++) {
            int size = goods[i - 1].size();
            int price = goods[i - 1].price();
            for (int c = 0; c <= capacity; c++) {
                if (c < size) {
                    records[i][c] = records[i - 1][c];
                } else {
                    records[i][c] = max(i, size, price, c);
                }
            }
        }
        return records[goods.length][capacity];
    }

    protected int max(int index, int size, int price, int capacity) {
        int a = records[index - 1][capacity];
        int b = records[index - 1][capacity - size] + price;
        return a > b ? a : b;
    }

    public synchronized Goods[] best(Goods[] goods) {
        calculate(goods);
        int indexOfCapacity = records[0].length - 1;
        int indexOfGoods = records.length - 1;
        List<Goods> result = new ArrayList<Goods>();
        for (int i = indexOfGoods; i > 0; i--) {
            if (goods[i - 1].size() > indexOfCapacity)
                continue;
            else if ((records[i - 1][indexOfCapacity - goods[i - 1].size()] + goods[i - 1].price()) > records[i - 1][indexOfCapacity]) {
                result.add(goods[i - 1]);
                indexOfCapacity -= goods[i - 1].size();
            }
        }
        return result.toArray(new Goods[result.size()]);
    }
}
