package com.pwc.dynamic.test;

import com.pwc.dynamic.Goods;
import com.pwc.dynamic.Knapsack;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class KnapsackTest {
    @Test
    public void initializeKnapsack() {
        Knapsack bag = new Knapsack(100);
        assertEquals(100, bag.capacity());
    }

    @Test
    public void calculateAndReturnResult() {
        Knapsack knapsack = new Knapsack(10);
        Goods[] goods = new Goods[]{new Goods(3, 4), new Goods(4, 5), new Goods(5, 6)};
        int best = knapsack.calculate(goods);
        assertEquals(11, best);
    }

    @Test
    public void moreCalculateTest() {
        Knapsack knapsack = new Knapsack(20);
        Goods[] goods = new Goods[]{new Goods(2, 4), new Goods(5, 6), new Goods(1, 2), new Goods(3, 5), new Goods(4, 7), new Goods(6, 7), new Goods(7, 8)};
        int best = knapsack.calculate(goods);
        assertEquals(29, best);
    }

    @Test
    public void returnBestSolution() {
        Knapsack knapsack = new Knapsack(15);
        Goods[] goods = new Goods[]{new Goods(3, 4), new Goods(4, 5), new Goods(5, 6), new Goods(1, 2), new Goods(2, 3), new Goods(6, 7), new Goods(7, 8)};
        Goods[] result = knapsack.best(goods);

        Goods[] expected = new Goods[]{new Goods(2, 3), new Goods(1, 2), new Goods(5, 6), new Goods(4, 5), new Goods(3, 4)};
        assertEquals(expected.length, result.length);

        for (int i = 0; i < result.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }
}
