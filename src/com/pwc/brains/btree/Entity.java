package com.pwc.brains.btree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Entity implements Comparable, Serializable {
    private int key;
    private List<Item> items;

    public Entity(int key) {
        this.key = key;
        items = new ArrayList<Item>();
    }

    public Entity(int i, Content content) {
        this(i);
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("Can not compare to an null reference!");
        }
        if (o instanceof Entity) {
            Entity entity = (Entity) o;
            if (this.key > entity.getKey())
                return 1;
            if (this.key == entity.getKey())
                return 0;
            if (this.key < entity.getKey())
                return -1;
        } else
            throw new ClassCastException("Can not compare between different type!");
        return 0;
    }

    public int getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        if (!(o instanceof Entity)) {
            throw new ClassCastException();
        }

        Entity entity = (Entity) o;
        return entity.getKey() == this.key && isItemsSame(this, entity);
    }

    private boolean isItemsSame(Entity entity, Entity entity1) {
        if (entity.items.size() != entity1.items.size()) {
            return false;
        }
        for (int i = 0; i < entity.items.size(); i++) {
            if (!entity.items.get(i).equals(entity1.items.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void put(Item item) {
        this.items.add(item);
    }

    public Entity put(Entity entity) {
        this.items.addAll(entity.items);
        return this;
    }
}
