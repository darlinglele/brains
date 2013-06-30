package com.pwc.brains.trie.test;

import com.pwc.brains.btree.ObjectSerializationException;
import com.pwc.brains.trie.Node;
import com.pwc.brains.trie.Tree;
import com.pwc.test.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TreeTest extends TestBase {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void insertWord() {
        String word = "你好";
        Tree tree = new Tree(word);
        assertEquals(1, tree.root().sizeOfChildren());
        Node node = new Node('好');
        assertEquals(node, tree.root().getChild('好'));
    }

    @Test
    public void cannotInsertWordsWithDiffPrefix() {
        String initWord = "你";
        String newWord = "您好";
        Tree tree = new Tree(initWord);
        assertEquals(0, tree.root().sizeOfChildren());
        boolean inserted = tree.insert(newWord);
        assertEquals(false, inserted);
        assertEquals(0, tree.root().sizeOfChildren());
    }

    @Test
    public void insertWordsWithSamePrefix() {
        String word1 = "你好";
        String word2 = "你们";

        Tree tree = new Tree(word1);
        tree.insert(word2);
        assertEquals(2, tree.root().sizeOfChildren());
        Node node1 = new Node('好');
        Node node2 = new Node('们');
        assertEquals(node1, tree.root().getChild('好'));
        assertEquals(node2, tree.root().getChild('们'));
    }

    @Test
    public void insertMoreWordsWithSamePrefix() {
        String word1 = "你好";
        String word2 = "你们";
        String word3 = "你们好";

        Tree tree = new Tree(word1);
        tree.insert(word2);
        tree.insert(word3);
        assertEquals(2, tree.root().sizeOfChildren());
        Node node1 = new Node('好');
        Node node2 = new Node('们');
        Node node3 = new Node('好');

        assertEquals(node1, tree.root().getChild('好'));
        assertEquals(node2, tree.root().getChild('们'));
        assertEquals(node3, tree.root().getChild('们').getChild('好'));
    }

    @Test
    public void searchWords() {
        String[] worlds = new String[]{"你", "你好", "你好吗", "你好坏", "你好毒", "你们", "你们好", "你妹"};
        Tree tree = new Tree(worlds[0]);
        for (String word : worlds) {
            tree.insert(word);
        }

        String prefix = "你";
        ArrayList<String> results = tree.search(prefix);
        assertEquals(worlds.length, results.size());
        for (String word : worlds) {
            assertTrue(results.contains(word));
        }
    }

    @Test
    public void searchWords2() {
        String[] worlds = new String[]{"你", "你好", "你好吗", "你好坏", "你好毒", "你们", "你们好", "你妹"};
        Tree tree = new Tree(worlds[0]);
        for (String word : worlds) {
            tree.insert(word);
        }
        String prefix = "你好";
        ArrayList<String> results = tree.search(prefix);
        String[] expected = new String[]{"你好", "你好吗", "你好坏", "你好毒"};
        assertEquals(expected.length, results.size());
        for (String word : expected) {
            assertTrue(results.contains(word));
        }
    }

    @Test
    public void load() throws ObjectSerializationException {
         Tree tree =  Tree.load("你.dic");
        ArrayList<String> results= tree.search("你");
        String[] worlds = new String[]{"你", "你好", "你好吗", "你好坏", "你好毒", "你们", "你们好", "你妹"};
        assertEquals(worlds.length, results.size());
        for (String word : worlds) {
            assertTrue(results.contains(word));
        }

    }

    @Test
    public void save() throws ObjectSerializationException {
        String[] worlds = new String[]{"你", "你好", "你好吗", "你好坏", "你好毒", "你们", "你们好", "你妹"};
        Tree tree = new Tree(worlds[0]);
        for (String word : worlds) {
            tree.insert(word);
        }
       String fileName =  "ni.dic";
       gc.add(fileName);
       tree.save(fileName);
    }




}
