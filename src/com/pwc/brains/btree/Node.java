package com.pwc.brains.btree;

import com.pwc.brains.Util;

import java.io.*;

public class Node implements Serializable, Cloneable {
    private static final String INDEX_FILE_DIR = "";
    private long serialVersionUID = 232334343;
    public static int M = 10;
    public static int KEY_MAX = 2 * M - 1;
    public static int KEY_MIN = M - 1;
    public static int CHILD_MAX = KEY_MAX + 1;
    public static int CHILD_MIN = KEY_MIN + 1;


    private String name;
    public boolean isLeaf = true;
    private int size = 0;
    public Child children[] = new Child[Node.CHILD_MAX];

    private Entity entities[] = new Entity[Node.KEY_MAX];

    public Entity[] getEntities() {
        return entities;
    }

    public Entity getEntity(int index) {
        return entities[index];
    }

    public void setEntity(int index, Entity entity) {
        this.entities[index] = entity;
    }

    public void addEntity(int index, Entity entity) {
        this.entities[index] = entity;
        this.size++;
    }

    public Node() {
        this.name = Util.getUuid();
    }

    public Node(String name) {
        this.children = new Child[Node.CHILD_MAX];
        this.entities = new Entity[Node.KEY_MAX];
        this.name = name;
    }

    public int indexOf(int key) {
        return binarySearch(this.entities, 0, this.size() - 1, key);
    }

    public String save() throws NodeSerializationException {
        FileOutputStream os = null;
        ObjectOutputStream o = null;
        String fileName = INDEX_FILE_DIR + String.valueOf(this.getName());
        try {
            os = new FileOutputStream(fileName);
            o = new ObjectOutputStream(os);
        } catch (FileNotFoundException e) {
            throw new NodeSerializationException("Unable to find or access the file:" + fileName, e);

        } catch (IOException e) {
            throw new NodeSerializationException("Unable to write the file:" + fileName, e);
        }

        try {
            o.writeObject(this.clone());
            o.flush();
        } catch (IOException e) {
            throw new NodeSerializationException(e.getMessage(), e);
        } finally {
            try {
                o.close();
            } catch (IOException e) {
                // I don't how to handle this kind error, throw a checked exception?
            }
        }

        return this.getName();
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.entities[i].getKey());
            if (i < this.size - 1)
                System.out.print(" ");
        }
    }

    public static Node load(String file) throws NodeSerializationException {
        Object node = null;
        String fileName = INDEX_FILE_DIR + String.valueOf(file);
        try {
            FileInputStream input = new FileInputStream(fileName);
            ObjectInputStream objInput = new ObjectInputStream(input);
            node = (Object) objInput.readObject();
            return (Node) node;
        } catch (Exception e) {
            throw new NodeSerializationException("Unable to load the node from file:", e);
        }
        finally {
            return (Node) node;
        }


    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() {
        Node node = new Node();
        node.isLeaf = this.isLeaf;
        node.size = this.size;
        node.setName(this.name);
        node.entities = this.entities;
        node.children = new Child[Node.CHILD_MAX];
        if (!this.isLeaf) {
            for (int i = 0; i <= this.size; i++) {
                node.children[i] = (Child) (this.children[i].clone());
            }
        }
        return node;
    }

    public int size() {
        return this.size;
    }

    public void removeEntity(int i) {
        this.entities[i] = null;
        this.size--;
    }


    private int binarySearch(Entity[] array, int l, int h, int key) {
        if (array[l].getKey() == key) {
            return l;
        }
        if (array[h].getKey() == key) {
            return h;
        }

        if (h - l <= 1) {
            return -1;
        }

        int m = (l + h) / 2;
        if (array[m].getKey() == key) {
            return m;
        }
        if (array[l].getKey() > key || array[h].getKey() < key) {
            return -1;
        }
        if (array[m].getKey() > key) {
            return binarySearch(array, l, m, key);
        }
        if (array[m].getKey() < key) {
            return binarySearch(array, m, h, key);
        }
        return -1;
    }

}


