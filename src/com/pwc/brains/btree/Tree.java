package com.pwc.brains.btree;

import com.pwc.brains.Console;
import com.pwc.brains.Util;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Tree implements Serializable {
    public static final String SUFFIX = "-Tree";
    private long serialVersionUID = 232334343;
    private String name;
    private Node root;
    private HashMap<String, Node> changes;
    private int size;
    private Node currentNode;

    public Tree(String name) {
        this.name = name;
        changes = new HashMap<String, Node>();
        getBasicInfo();
    }

    public void load() {
        try {
            this.root = Node.load(this.name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ObjectSerializationException e) {
            Console.exception(e);
        }
    }


    public void save() throws ObjectSerializationException {
        Iterator iterator = changes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object val = entry.getValue();
            Node node = (Node) val;
            try {
                node.save();
            } catch (ObjectSerializationException e) {
                Console.exception(e);
                return;
            }
        }
        Tree.save((Tree) this.clone());
        changes.clear();
    }

    public Entity search(int key) {
        if (this.root == null) {
            this.load();
        }
        return search(this.root, key);
    }

    public boolean insert(Entity entity) {
        Entity result = this.search(entity.getKey());
        if (result != null) {
            changes.put(currentNode.name(), currentNode);
            update(result, entity);
            return true;
        }

        if (root == null) {
            root = new Node(name);
        }

        if (root.size() == Node.KEY_MAX) {
            updateTreeRoot();
        }

        insertNonFull(root, entity);
        this.increase();
        return true;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Object clone() {
        Tree tree = new Tree(this.name);
        tree.size = this.size;

        return tree;
    }

    public void print() {
        ArrayList<Node> current = new ArrayList<Node>();
        ArrayList<Node> children = new ArrayList<Node>();
        ArrayList<Node> temp;
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
                    for (int i = 0; i <= node.size(); i++) {
                        children.add(node.children[i].getNode());
                    }
                }

            }

            System.out.println("");  // new line for next level printing
        }
    }

    private Entity update(Entity target, Entity entity) {
        return target.put(entity);
    }

    private void increase() {
        this.size++;
    }

    private void insertNonFull(Node node, Entity entity) {
        int index = node.size();
        if (node.isLeaf) {
            while (index > 0 && entity.compareTo(node.getEntity(index - 1)) == -1) {
                node.setEntity(index, node.getEntity(index - 1));
                --index;
            }
            node.addEntity(index, entity);// add new entity to node
            this.changes.put(node.name(), node);
        } else {
            while (index > 0 && entity.compareTo(node.getEntity(index - 1)) == -1) {
                --index;
            }
            Node childNode = node.children[index].getNode();
            if (childNode.size() == Node.KEY_MAX) {
                splitFullNode(node, index, childNode);
                if (entity.compareTo(node.getEntity(index)) == 1)
                    childNode = node.children[index + 1].getNode();
            }
            insertNonFull(childNode, entity);
        }
    }

    private void splitFullNode(Node parent, int childNodeIndex, Node fullNode) {
        Node rightBrother = new Node();
        rightBrother.isLeaf = fullNode.isLeaf;

        moveEntities(fullNode, rightBrother);

        if (!fullNode.isLeaf) {
            moveChildren(fullNode, rightBrother);
        }


        int i;
        for (i = parent.size(); i > childNodeIndex; --i) {
            parent.children[i + 1] = parent.children[i];
            parent.setEntity(i, parent.getEntity(i - 1));

        }

        parent.children[childNodeIndex + 1] = new Child(rightBrother.name(), rightBrother);  //存储右子树指针
        parent.addEntity(childNodeIndex, fullNode.getEntity(Node.KEY_MIN));//把节点的中间值提到父节点
        changes.put(parent.name(), parent);
        changes.put(fullNode.name(), fullNode);
        changes.put(rightBrother.name(), rightBrother);
    }

    private void moveEntities(Node left, Node right) {
        int i;
        for (i = 0; i < Node.KEY_MIN; ++i) {
            right.addEntity(i, left.getEntity(i + Node.M));
            left.removeEntity(i + Node.M);
        }
    }

    private void moveChildren(Node origin, Node rightNode) {
        for (int i = 0; i < Node.M; ++i) {
            rightNode.children[i] = origin.children[i + Node.M];
        }
    }

    private void updateTreeRoot() {
        Node newRoot = new Node(name);
        newRoot.isLeaf = false;
        root.setName(Util.getUuid()); // rename the node when it's not root
        newRoot.children[0] = new Child(root.name(), root);
        splitFullNode(newRoot, 0, root);
        Node oldRoot = root;
        root = newRoot;
        changes.put(root.name(), root);
        changes.put(oldRoot.name(), oldRoot);
    }

    private Entity search(Node node, int key) {
        if (node == null) {
            return null;
        }
        int index = node.indexOf(key);
        if (index != -1) {
            currentNode = node;
            return node.getEntity(index);
        }

        if (node.isLeaf) {
            return null;
        }

        int i = node.size();

        while (i > 0 && key < node.getEntity(i - 1).getKey())
            i--;
        return search(node.children[i].getNode(), key);
    }

    private void getBasicInfo() {
        Tree tree = (Tree) Load(this.name);
        if (tree != null)
            this.size = tree.size();
    }

    private static void save(Tree tree) throws ObjectSerializationException {
        Util.serialize(tree.name + SUFFIX, tree);
    }

    private static Tree Load(String fileName) {
        fileName = fileName + SUFFIX;
        Tree tree = null;
        try {
            tree = (Tree) Util.deserialize(String.valueOf(fileName));
        } catch (ObjectSerializationException e) {
            Console.exception(e);
        }
        return tree;
    }
}
