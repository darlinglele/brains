package com.pwc.brains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Tree implements Serializable {
    private String name;
    private Node root;
    private int sizeOfEntity;
    private int sizeOfNode;
    private Counter counter;
    private HashMap<String, Node> changes;

    public Tree(String name) {
        this.name = name;
        changes = new HashMap<String, Node>();
    }

    public void load() {
        try {
            this.root = Node.load(this.name);
        } catch (NodeSerializationException e) {
            Console.exception("unable to load the tree root from file:" + this.name, e);
        }
    }

    public void save() {
        Iterator iterator = changes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object val = entry.getValue();
            Node node = (Node) val;
            try {
                node.save();
            } catch (NodeSerializationException e) {
                Console.exception(e);
                return;
            }
        }
        changes.clear();
    }

    public Entity search(int key) {
        if (this.root == null) {
            return null;
        }
        return search(this.root, key);
    }

    public boolean insert(Entity entity) {
        if (root == null) {
            root = new Node(name);
        }

        if (root.size() == Node.KEY_MAX) {
            updateTreeRoot();
        }

        insertNonFull(root, entity);
        return true;

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

    private void insertNonFull(Node node, Entity entity) {
        int index = node.size();
        if (node.isLeaf) {
            while (index > 0 && entity.compareTo(node.getEntity(index - 1)) == -1) {
                node.setEntity(index, node.getEntity(index - 1));
                --index;
            }
            node.addEntity(index, entity);// add new entity to node
            this.changes.put(node.getName(), node);
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


        parent.children[childNodeIndex + 1] = new Child(rightBrother.getName(), rightBrother);  //存储右子树指针
        parent.addEntity(childNodeIndex, fullNode.getEntity(Node.KEY_MIN));//把节点的中间值提到父节点
        changes.put(parent.getName(), parent);
        changes.put(fullNode.getName(), fullNode);
        changes.put(rightBrother.getName(), rightBrother);
    }

    private void moveEntities(Node left, Node right) {
        int i;
        for (i = 0; i < Node.KEY_MIN; ++i) {
            right.addEntity(i, left.getEntity(i + Node.M));
            left.removeEntity(i + Node.M);
        }
    }

    private void moveChildren(Node origin, Node rightNode) {
        int i;
        for (i = 0; i < Node.M; ++i) {
            rightNode.children[i] = origin.children[i + Node.M];
        }
    }

    private void updateTreeRoot() {
        Node newRoot = new Node(name);
        newRoot.isLeaf = false;
        root.setName(Util.getUuid()); // rename the node when it's not root
        newRoot.children[0] = new Child(root.getName(), root);
        splitFullNode(newRoot, 0, root);
        Node oldRoot = root;
        root = newRoot;
        changes.put(root.getName(), root);
        changes.put(oldRoot.getName(), oldRoot);
    }

    private Entity search(Node node, int key) {
        node.print();
        System.out.println("");
        int index = node.indexOf(key);
        if (index != -1) {
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
}
