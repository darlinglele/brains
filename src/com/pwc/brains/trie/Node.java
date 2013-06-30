package com.pwc.brains.trie;

import java.io.Serializable;
import java.util.HashMap;

public class Node implements Serializable {
    private final char letter;
    private boolean isWord = false;
    private long serialVersionUID = 3334143;
    private HashMap<Character, Node> children = new HashMap<Character, Node>();

    public Node(char c) {
        this.letter = c;
    }

    public char letter() {
        return letter;
    }

    public HashMap<Character, Node> children() {
        return children;
    }

    public Node getChild(char c) {
        return children.get(c);
    }

    public void putChild(char c, Node node) {
        children.put(c, node);
    }

    public int sizeOfChildren() {
        return children.size();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        Node node;
        if (o instanceof Node) {
            node = (Node) o;
        }
        else {
            throw new ClassCastException();
        }

        return node.letter() == node.letter();
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public boolean contains(char c) {
        return children.containsKey(c);
    }
}

