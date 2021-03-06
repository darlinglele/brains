package com.pwc.brains.btree.test;

import com.pwc.brains.btree.Entity;
import com.pwc.brains.btree.Node;
import com.pwc.brains.btree.ObjectSerializationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NodeTest {
    private Node node = null;

    @Before
    public void setUp() throws Exception {
        node = new Node();
        setEntities(node);
    }

    @After
    public void tearDown() throws Exception {
        clearEntities(node);
    }

    @Test
    public void testIndexOf() throws Exception {
        for (int i = 0; i < node.size() - 1; i++) {
            assertEquals(i, node.indexOf(i));
        }
    }

    @Test(expected = ObjectSerializationException.class)
    public void saveNodeToFileUnreachable() throws ObjectSerializationException {
        String unreachableFile = "";
        node.setName(unreachableFile);
        node.save();
    }

//    @Test(expected = ObjectSerializationException.class)
//    public void loadNodeFromFileUnreachable() throws ObjectSerializationException {
//        String unreachableFile = "";
//        Node.loadDictionary(unreachableFile);
//    }

    private void setEntities(Node node) {
        for (int i = 0; i < Node.KEY_MAX; i++) {
            node.addEntity(i, new Entity(i));
        }
    }


    private void clearEntities(Node node) {
        for (Entity entity : node.getEntities()) {
            entity = null;
        }
    }
}
