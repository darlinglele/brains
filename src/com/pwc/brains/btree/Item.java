package com.pwc.brains.btree;

import java.io.Serializable;

public class Item implements Serializable {
    private long serialVersionUID = 232784343;
    private String url;

    public Item(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            throw new NullPointerException();
        }

        if (!(o instanceof Item)) {
            throw new ClassCastException();
        }

        Item item = (Item) o;
        return item.url.equals(this.url);
    }
}
