package com.pwc.brains.btree;

import com.pwc.brains.Console;

import java.io.Serializable;

public class Child implements Serializable, Cloneable {
    private String name;
    private Node node;

    public Child(String name, Node node) {
        this.name = name;
        this.node = node;
    }

    public Node getNode() {
        if (this.node == null) {
            try {
                this.node = Node.load(this.name);
            } catch (NodeSerializationException e) {
                Console.exception("unable to get node from file name " + name, e);
            }
        }
        return this.node;
    }

    @Override
    public Object clone() {
        return new Child(this.name, null);
    }
}