package com.pwc.brains.btree;

import com.pwc.brains.Console;

import java.io.IOException;
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
            } catch (ObjectSerializationException e) {
                Console.exception("unable to length node from file name " + name, e);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return this.node;
    }

    @Override
    public Object clone() {
        return new Child(this.name, null);
    }
}
