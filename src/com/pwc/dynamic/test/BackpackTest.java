package com.pwc.dynamic.test;

import com.pwc.dynamic.Backpack;
import com.pwc.dynamic.Goods;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BackpackTest {
    @Test
    public void newBackpack() {
        Backpack bag = new Backpack(100);
        assertEquals(100, bag.capacity());
    }

    @Test
    public void maxPrice() {
        Backpack bag = new Backpack(10);
        Goods[] goods = new Goods[]{new Goods(3, 4), new Goods(4, 5), new Goods(5, 6)};
        int max = bag.max(goods);
        assertEquals(11, max);
    }

    @Test
    public void maxPrice2() {
        Backpack bag = new Backpack(100);
        Goods[] goods = new Goods[]{new Goods(39, 4), new Goods(87, 5), new Goods(21, 6), new Goods(3, 3), new Goods(5, 7)};
        int max = bag.max(goods);
        assertEquals(20, max);
    }


}
