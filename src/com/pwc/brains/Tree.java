package com.pwc.brains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tree implements Serializable {
    private static final String NAME = "11111";
    private Node root;
    private HashMap<String, Node> changes = new HashMap<String, Node>();

    public Tree() {
        this.root = Node.load(NAME);
    }

    public String save() {
        Iterator iterator = changes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object val = entry.getValue();
            Node node = (Node) val;
            node.save();
        }
        changes.clear();
        return NAME;
    }

    public void load(String name) {
        this.root = Node.load(name);
    }

    public Entity search(int key) {
        if (this.root == null) {
            return null;
        }
        return search(this.root, key);
    }

    public Entity search(Node node, int key) {
        node.print();
        System.out.println("");
        int index = node.indexOf(key);
        if (index != -1) {
            return node.entities[index];
        }

        if (node.isLeaf) {
            return null;
        }

        int i = node.size;
        while (i > 0 && key < node.entities[i - 1].getKey())
            i--;
        return search(node.children[i].getNode(), key);
    }

    public boolean insert(Entity entity) {
        if (root == null) {
            root = new Node(NAME);          //create root
        }

        if (root.size == Node.KEY_MAX) { //create new root
            Node newRoot = new Node(NAME);
            newRoot.isLeaf = false;
            root.setName(Util.getUuid());
            newRoot.children[0] = new Child(root.getName(), root);
            splitChild(newRoot, 0, root);
            Node oldRoot = root;
            root = newRoot;
            changes.put(root.getName(), root);         // the new root
            changes.put(oldRoot.getName(), oldRoot);      // the old root
        }
        insertNonFull(root, entity);
        return true;

    }

    public void display() {
        ArrayList<Node> current = new ArrayList<Node>();
        ArrayList<Node> children = new ArrayList<Node>();
        ArrayList<Node> temp = null;
        current.add(this.root);
        children.add(this.root);
        while (children.size() > 0) {
            temp = current;
            current = children; // swap currentLevel and nextLevel
            children = temp;
            children.clear();

            for (Node node : current) {
                node.print();
                System.out.print("    ");
                // prepare  the next level printing
                if (!node.isLeaf) {
                    for (int i = 0; i <= node.size; i++) {
                        children.add(node.children[i].getNode());
                    }
                }

            }

            System.out.println("");  // new line for next level printing
        }
    }

    private void insertNonFull(Node node, Entity entity) {
        int index = node.size;
        if (node.isLeaf) {
            while (index > 0 && entity.compareTo(node.entities[index - 1]) == -1) {
                node.entities[index] = node.entities[index - 1];
                --index;
            }
            node.entities[index] = entity;
            node.size++;
            this.changes.put(node.getName(), node);
        } else {
            while (index > 0 && entity.compareTo(node.entities[index - 1]) == -1) {
                --index;
            }
            Node childNode = node.children[index].getNode();
            if (childNode.size == Node.KEY_MAX) {
                splitChild(node, index, childNode);
                if (entity.compareTo(node.entities[index]) == 1)
                    childNode = node.children[index + 1].getNode();
            }
            insertNonFull(childNode, entity);
        }
    }

    private void splitChild(Node parent, int childNodeIndex, Node left) {

        Node right = new Node();
        right.isLeaf = left.isLeaf;
        right.size = Node.KEY_MIN;

        moveKeys(left, right);

        if (!left.isLeaf) {
            moveChildNode(left, right);
        }

        left.size = Node.KEY_MIN;

        int i;
        for (i = parent.size; i > childNodeIndex; --i)//将父节点中的nChildIndex后的所有关键字的值和子树指针向后移一位
        {
            parent.children[i + 1] = parent.children[i];
            parent.entities[i] = parent.entities[i - 1];
        }

        parent.size++;  //更新父节点的关键字个数

        parent.children[childNodeIndex + 1] = new Child(right.getName(), right);  //存储右子树指针
        parent.entities[childNodeIndex] = left.entities[Node.KEY_MIN];//把节点的中间值提到父节点

        changes.put(parent.getName(), parent);
        changes.put(left.getName(), left);
        changes.put(right.getName(), right);
    }

    private void moveKeys(Node origin, Node rightNode) {
        int i;
        for (i = 0; i < Node.KEY_MIN; ++i) {
            rightNode.entities[i] = origin.entities[i + Node.M];
        }
    }

    private void moveChildNode(Node origin, Node rightNode) {
        int i;
        for (i = 0; i < Node.M; ++i) {
            rightNode.children[i] = origin.children[i + Node.M];
        }
    }


}
