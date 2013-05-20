package com.pwc.hotel.test;

import com.pwc.hotel.Condition;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ConditionTest {
    @Test
    public void testParse() {
        String condition = "Regular: 16Mar2009(mon), 17Mar2009(tues),18Mar2009(wed)";
        Condition expected = new Condition("Regular", new int[]{1, 1, 1, 0, 0, 0, 0});
        Condition result = Condition.parse(condition);
        assertEquals(expected, result);
    }

    @Test
    public void testParseWhenOverOneWeek() {
        String condition = "Regular: 16Mar2009(mon), 17Mar2009(tues),18Mar2009(wed), 20Mar2009(fri),21Mar2009(sat),22Mar2009(sun),26Mar2009(thur),27Mar2009(fri)";
        Condition expected = new Condition("Regular", new int[]{1, 1, 1, 1, 2, 1, 1});
        Condition result = Condition.parse(condition);
        assertEquals(expected, result);
    }
}
