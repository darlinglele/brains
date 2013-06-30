package com.pwc.dynamic;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class Knapsack2Test {
    @Test
    public void testCalculate() throws Exception {
        Knapsack2 knapsack2 = new Knapsack2(10);
        Goods[] goods = new Goods[]{new Goods(3, 4), new Goods(4, 5), new Goods(5, 6), new Goods(6, 7)};
        int best = knapsack2.calculate(goods);
        assertEquals(13, best);
    }

    @Test
    public void testCalculate2() throws Exception {
        Knapsack2 knapsack2 = new Knapsack2(16);
        Goods[] goods = new Goods[]{new Goods(1, 1), new Goods(3, 4), new Goods(4, 5), new Goods(5, 6), new Goods(6, 7)};
        int best = knapsack2.calculate(goods);
        assertEquals(21, best);
    }

    @Test
    public void testBest() throws Exception {

    }
}
