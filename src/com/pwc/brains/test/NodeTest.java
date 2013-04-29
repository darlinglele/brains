package com.pwc.brains.test;

import com.pwc.brains.Entity;
import com.pwc.brains.Node;
import com.pwc.brains.NodeSerializationException;
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

    @Test(expected = NodeSerializationException.class)
    public void saveNodeToFileUnreachable() throws NodeSerializationException {
        String unreachableFile = "";
        node.setName(unreachableFile);
        node.save();
    }

    @Test(expected = NodeSerializationException.class)
    public void loadNodeFromFileUnreachable() throws NodeSerializationException {
        String unreachableFile = "";
        Node node = Node.load(unreachableFile);
    }

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
