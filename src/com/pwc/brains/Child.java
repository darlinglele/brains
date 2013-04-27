package com.pwc.brains;

import java.io.Serializable;

public class Child implements Serializable, Cloneable {
    private String name;
    private Node node;

    public Child(String name, Node node) {
        this.name = name;
        this.node = node;
    }

    public Node getNode() {
        if (node == null) {
            node = Node.load(this.name);
        }
        return node;
    }

    @Override
    public Object clone() {
        return new Child(this.name, null);
    }
}
