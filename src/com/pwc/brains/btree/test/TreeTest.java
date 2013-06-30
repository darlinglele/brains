package com.pwc.brains.btree.test;

import com.pwc.array.Array;
import com.pwc.brains.Util;
import com.pwc.brains.btree.*;
import com.pwc.test.TestBase;
import org.junit.Test;

import static com.pwc.brains.btree.Tree.SUFFIX;
import static junit.framework.Assert.*;

public class TreeTest extends TestBase {
    @Test
    public void afterInsertEntityThenSizeIncreased() throws Exception {
        String treeName = "MemoryTree";
        gc.add(treeName + SUFFIX);
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
        gc.add(treeName + SUFFIX);
        createTestBinaryData(treeName);
        Tree tree = new Tree(treeName);
        tree.load();
        assertEquals(tree.size(), 4);
    }

    @Test
    public void searchEntityWithHashCode() throws ObjectSerializationException {
        String treeName = "treeContainsEntityWithKey1";
        gc.add(treeName);
        gc.add(treeName + SUFFIX);
        createTestBinaryData(treeName);

        int hashCode = 2;
        Entity expected = new Entity(hashCode);
        Entity result = new Tree(treeName).search(hashCode);
        assertEquals(expected, result);
    }

    @Test
    public void willNotIncreaseSizeWhenInsertAnExistingEntity() throws ObjectSerializationException {
        String newTree = "newTree";
        gc.add(newTree + SUFFIX);
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
        gc.add(newTree + SUFFIX);
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
        gc.add(oldTreeName + SUFFIX);
        createTestBinaryData(oldTreeName);

        int key = 1;
        Entity expected = new Entity(key);
        expected.put(new Item("testUrl"));
        updateAndSaveWith(oldTreeName, expected);

        Tree newTree = new Tree(oldTreeName);

        Entity result = newTree.search(key);
        assertEquals(expected, result);
    }

    //    @Test
    public void autoSerializeWhenReachesPreset() {
        String name = "newTree";
//        gc.add(name);
//        gc.add(name + Tree.SUFFIX);
        Tree tree = new Tree(name);
        assertFalse(Util.isFileExists(name));

        int[] array = new int[Node.KEY_MAX];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        Array.shuffle(array);

        for (int i = 0; i < array.length; i++) {
            Entity entity = new Entity(array[i]);
            for (int j = 0; j < 1000; j++) {
                entity.put(new Item(String.valueOf(j)));
                tree.insert(entity);
            }
        }

        tree.save();
        assertTrue(Util.isFileExists(name));
        assertTrue(Util.isFileExists(name + SUFFIX));

        Tree tree1 = new Tree(name);
        assertEquals(array.length, tree1.size());

        for (int i = 0; i < array.length; i++) {
            assertTrue(tree1.contains(array[i]));
        }
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
