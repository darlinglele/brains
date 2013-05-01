package com.pwc.brains.dictionary;

import java.io.Serializable;

public class Letter implements Serializable {
    private char name;

    public Letter(char name) {
        this.name = name;
    }

    public char name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        Letter l = null;
        if (o instanceof Letter) {
            l = (Letter) o;
        } else {
            throw new ClassCastException();
        }
        return this.name == l.name();
    }

    @Override
    public int hashCode() {
        return 13 * 7 * this.name;
    }
}
