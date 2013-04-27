package com.pwc.brains;
import java.io.*;

public class Node implements Serializable, Cloneable {
    private static final String INDEX_FILE_DIR = "c:\\index\\";
    private long serialVersionUID = 232334343;
    public static int M = 3;
    public static int KEY_MAX = 2 * M - 1;
    public static int KEY_MIN = M - 1;
    public static int CHILD_MAX = KEY_MAX + 1;
    public static int CHILD_MIN = KEY_MIN + 1;


    private String name;
    public boolean isLeaf = true;
    public int size = 0;
    public Child children[] = null;
    public Entity entities[] = null;

    public Node() {
        this.children = new Child[Node.CHILD_MAX];
        this.entities = new Entity[Node.KEY_MAX];
        this.name = Util.getUuid();
    }


    public Node(String name) {
        this.children = new Child[Node.CHILD_MAX];
        this.entities = new Entity[Node.KEY_MAX];
        this.name = name;
    }

    public String save() {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(INDEX_FILE_DIR + String.valueOf(this.getName()));
            ObjectOutputStream o = new ObjectOutputStream(os);
            o.writeObject(this.clone());
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.getName();
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {//the entities of node to print
            System.out.print(this.entities[i].getKey());
            if (i < this.size - 1)
                System.out.print(" "); // space between entities
        }
    }

    public int indexOf(int key) {
        for (int i = 0; i < this.size; i++) {
            if (this.entities[i].getKey() == key) {
                return i;
            }
        }
        return -1;
    }

    public int binarySearch(int[] array, int key) {
        return search(array, 0, array.length - 1, key);
    }

    private int search(int[] array, int l, int h, int key) {
        if (array[l + h] == key) {
            return l + h;
        }
        if (array[l + h] > key) {
            return search(array, l, l + h, key);
        }
        if (array[l + h] > key) {
            return search(array, l + h, h, key);
        }
        return -1;
    }


    public static Node load(String file) {
        try {
            FileInputStream input = new FileInputStream(INDEX_FILE_DIR + String.valueOf(file));
            ObjectInputStream objInput = new ObjectInputStream(input);
            Object node = (Object) objInput.readObject();
            return (Node) node;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
}


