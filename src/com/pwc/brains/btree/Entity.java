package com.pwc.brains.btree;
import java.io.Serializable;

public class Entity implements Comparable,Serializable {
    private int key;
    public Entity(int key){
        this.key = key;
    }
    @Override
    public int compareTo(Object o) {
        if(o == null){
            throw new NullPointerException("Can not compare to an null reference!");
        }
        if(o instanceof Entity) {
            Entity entity = (Entity)o;
            if (this.key > entity.getKey())
                return 1;
            if(this.key == entity.getKey())
                return 0;
            if(this.key<entity.getKey())
                return  -1;
        }
        else
            throw new ClassCastException("Can not compare between different type!");
        return 0;
    }

    public int getKey(){
        return key;
    }
}
