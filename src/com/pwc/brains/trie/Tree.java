package com.pwc.brains.trie;

import com.pwc.brains.Util;
import com.pwc.brains.btree.ObjectSerializationException;

import java.io.Serializable;
import java.util.ArrayList;

public class Tree implements Serializable {
    private Node root;
    private long serialVersionUID = 3323343;

    public Tree(String word) {
        this.root = new Node(word.charAt(0));
        if (word.length() == 1) {
            this.root.setWord(true);
        }
        if (word.length() > 1) {
            insert(word);
        }
    }

    public String name() {
        return String.valueOf(this.root.letter());
    }

    public boolean insert(String word) {
        if (word == "") {
            return false;
        }
        if (word.charAt(0) != this.root.letter()) {
            return false;
        }
        if (word.length() == 1) {
            root.setWord(true);
            return true;
        }

        insert(root, word.substring(1));

        return false;
    }

    private void insert(Node node, String word) {
        char target = word.charAt(0);
        boolean isExist = node.contains(word.charAt(0));
        if (!isExist) {
            node.putChild(target, new Node(target));
        }
        if (word.length() > 1) {
            insert(node.getChild(target), word.substring(1));
        } else {
            node.getChild(target).setWord(true);
        }
    }


    public ArrayList<String> search(String prefix) {
        Node node = searchNodeByPrefix(this.root, prefix);
        if (node == null)
            return null;
        ArrayList<String> results = new ArrayList<String>();
        allWordsWith(results, node, prefix);
        return results;
    }

    private void allWordsWith(ArrayList<String> results, Node node, String prefix) {
        if (node.isWord()) {
            results.add(prefix);
        }
        for (Node childNode : node.children().values()) {
            allWordsWith(results, childNode, prefix + childNode.letter());
        }
    }

    private Node searchNodeByPrefix(Node node, String prefix) {
        if (node == null || prefix == null || prefix.length() == 0 || prefix.charAt(0) != node.letter())
            return null;
        if (prefix.length() == 1) {
            return node;
        } else {
            if (prefix.charAt(0) == node.letter()) {
                return searchNodeByPrefix(node.getChild(prefix.charAt(1)), prefix.substring(1));
            } else
                return null;
        }
    }

    public Node root() {
        return root;
    }

    public void save(String fileName) throws ObjectSerializationException {
        Util.serialize(fileName, this);
    }

    public void save() throws ObjectSerializationException {
        save(this.name());
    }

    public static Tree load(String s) throws ObjectSerializationException {
        return (Tree) Util.deserialize(s);
    }
}
