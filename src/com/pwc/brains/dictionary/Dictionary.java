package com.pwc.brains.dictionary;

import com.pwc.brains.Util;
import com.pwc.brains.btree.ObjectSerializationException;
import com.pwc.brains.trie.Tree;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Dictionary implements Serializable {
    private String name;
    private long serialVersionUID = 332334143;
    private HashMap<Letter, Tree> trees = new HashMap<Letter, Tree>();
    public Dictionary(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public static Dictionary load(String name) throws ObjectSerializationException {
        return (Dictionary) Util.deserialize(name);
    }

    public HashMap<Letter, Tree> trees() {
        return this.trees;
    }

    public void put(String word) {
        if(word == null)
            return;
        if(word.length()==0)
            return;
        Letter c= new Letter(word.charAt(0)) ;
        if(!trees.containsKey(c)){
            trees.put(c, new Tree(word));
        }
        else
            this.findTree(c).insert(word);
    }

    private Tree findTree(Letter c) {
        if(trees.get(c)== null){
            Tree  tree =  loadTree(c.name());
            trees.put(c,tree);
        }
        return  trees.get(c);
    }

    private Tree loadTree(char name) {
        try {
            Tree tree =  Tree.load(String.valueOf(name)) ;
            if(tree  == null){
                tree= new Tree(String.valueOf(name)) ;
            }
            return tree;
        } catch (ObjectSerializationException e) {
           return new Tree(String.valueOf(name)) ;
        }

    }

    public void save() throws Exception {
        Util.serialize(this.name(), this);
//        for(Tree tree: this.trees.values()){
//            tree.save();
//        }
    }
    @Override
    protected  Object clone() {
        Dictionary dic = new Dictionary(this.name);
//        dic.trees = new HashMap<Letter, Tree>();
//        dic.letters = this.letters;
        return dic;
    }


    public List<String> search(String name) {
             return  findTree(new Letter(name.charAt(0))).search(name);
    }
}
