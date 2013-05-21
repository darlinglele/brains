package com.pwc.brains.btree.test;

import com.pwc.brains.btree.*;
import com.pwc.test.TestBase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TreeTest extends TestBase {
    private String suffix = "-Tree";

    @Test
    public void afterInsertEntityThenSizeIncreased() throws Exception {
        String treeName = "MemoryTree";
        gc.add(treeName + suffix);
        gc.add(treeName);
        Tree tree = new Tree(treeName);
        assertEquals(0, tree.size());
        tree.insert(new Entity(0));
        assertEquals(1, tree.size());
        tree.insert(new Entity(1));
        assertEquals(2, tree.size());
    }

    @Test
    public void loadTreeBasicInfoFromBinaryData() throws ObjectSerializationException {
        String treeName = "treeWith4entity";
        gc.add(treeName);
        gc.add(treeName + suffix);
        createTestBinaryData(treeName);
        Tree tree = new Tree(treeName);
        tree.load();
        assertEquals(tree.size(), 4);
    }

    @Test
    public void searchEntityWithHashCode() throws ObjectSerializationException {
        String treeName = "treeContainsEntityWithKey1";
        gc.add(treeName);
        gc.add(treeName + suffix);
        createTestBinaryData(treeName);

        int hashCode = 2;
        Entity expected = new Entity(hashCode);
        Entity result = new Tree(treeName).search(hashCode);
        assertEquals(expected, result);
    }

    @Test
    public void willNotIncreaseSizeWhenInsertAnExistingEntity() throws ObjectSerializationException {
        String newTree = "newTree";
        gc.add(newTree + suffix);
        gc.add(newTree);
        Tree tree = new Tree(newTree);
        tree.insert(new Entity(1));
        assertEquals(1, tree.size());
        tree.insert(new Entity(1));
        assertEquals(1, tree.size());
    }

    @Test
    public void updateEntityWhenInsertAnExistingEntity() throws ObjectSerializationException {
        String newTree = "newTree";
        gc.add(newTree + suffix);
        gc.add(newTree);
        Tree tree = new Tree(newTree);
        int key = 1;
        tree.insert(new Entity(key));
        tree.save();

        Entity entity = new Entity(key);
        entity.put(new Item("testUrl"));
        tree.insert(entity);
        Entity result = tree.search(key);
        assertEquals(entity, result);
    }

    @Test
    public void updateAndSaveEntityToBinary() throws ObjectSerializationException {
        String oldTreeName = "treeToBeUpdated";
        gc.add(oldTreeName);
        gc.add(oldTreeName + suffix);
        createTestBinaryData(oldTreeName);

        int key = 1;
        Entity expected = new Entity(key);
        expected.put(new Item("testUrl"));
        updateAndSaveWith(oldTreeName, expected);

        Tree newTree = new Tree(oldTreeName);

        Entity result = newTree.search(key);
        assertEquals(expected, result);
    }

    private void updateAndSaveWith(String treeName, Entity expected) throws ObjectSerializationException {
        Tree tree = new Tree(treeName);
        tree.insert(expected);
        tree.save();
    }

    private void createTestBinaryData(String treeName) throws ObjectSerializationException {
        Tree tree = new Tree(treeName);
        tree.insert(new Entity(0));
        tree.insert(new Entity(1));
        tree.insert(new Entity(2));
        tree.insert(new Entity(3));
        tree.save();
    }
}
