package com.pwc.collect.test;

import com.pwc.collect.Collection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CollectionTest {

    @Test
    public void filterFunctionTest() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("Hi");
        arrayList.add("Live is so beautiful");
        arrayList.add("Any way");
        Collection<String> myCollection = new Collection<String>(arrayList);
        assertEquals(1, myCollection.filter((String item) -> (item.length() > 10)).toList().size());
        assertEquals(2, new Collection<String>(arrayList).filter((String item) -> (item.length() <= 10)).toList().size());
    }

    @Test
    public void mapFunctionTest() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("1");
        arrayList.add("22");
        arrayList.add("333");
        Collection<String> myCollection = new Collection<String>(arrayList);
        List<Integer> lengthOfStrings = myCollection.map((String item) -> item.length()).toList();

        assertEquals(Integer.valueOf(1), lengthOfStrings.get(0));
        assertEquals(Integer.valueOf(2), lengthOfStrings.get(1));
        assertEquals(Integer.valueOf(3), lengthOfStrings.get(2));
    }

    @Test
    public void eachFunctionTest() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("1");
        arrayList.add("22");
        arrayList.add("333");
        Collection<String> myCollection = new Collection<String>(arrayList);
        myCollection.each((String item) -> {
            assertTrue(item.length() < 4);
        });
        myCollection.each(null);
    }

}
