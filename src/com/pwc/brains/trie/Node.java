package com.pwc.brains.trie;

import java.util.HashMap;

public class Node {
    private final char letter;
    private boolean isWord = false;
    private HashMap<Character, Node> children = new HashMap<Character, Node>();

    public Node(char c) {
        this.letter = c;
    }

    public char getLetter() {
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
        if (o == null || !(o instanceof Node))
            return false;
        Node node = (Node) o;

        return node.getLetter() == node.getLetter();
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

