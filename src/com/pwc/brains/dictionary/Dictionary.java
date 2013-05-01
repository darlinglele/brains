package com.pwc.brains.dictionary;

import com.pwc.brains.Util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

public class Dictionary implements Serializable {
    private String name;
    private long serialVersionUID = 332334143;

    private Collection<Letter> letters = new HashSet<Letter>();

    public Dictionary(String name) {
        this.name = name;

    }

    public String name() {
        return this.name;
    }

    public static Dictionary load(String name) throws LoadDictionaryException {
        return (Dictionary) Util.deserialize(name);
    }

    public Collection<Letter> letters() {
        return this.letters;
    }

    public void put(String word) {
        for (char c : word.toCharArray()) {
            this.letters.add(new Letter(c));
        }
    }

    public void save() throws Exception {
        Util.serialize(this.name(), this);
    }


}
