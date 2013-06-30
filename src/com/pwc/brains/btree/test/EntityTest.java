package com.pwc.brains.btree.test;

import com.pwc.brains.btree.Entity;
import com.pwc.brains.btree.Item;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EntityTest {
    @Test
    public void testContains() throws Exception {
        Entity entity = new Entity(1);
        String url = "www.google.com";
        Item item = new Item(url);
        assertFalse(entity.contains(item));
        entity.put(new Item(url));
        assertTrue(entity.contains(item));
    }

    @Test
    public void testMerge() {
        Entity entity = new Entity(1);
        Entity entity1 = new Entity(1);
        entity.merge(entity1);
    }
}
