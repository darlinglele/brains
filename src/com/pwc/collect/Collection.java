package com.pwc.collect;

import java.util.ArrayList;
import java.util.List;

public class Collection<T> {
    private final List<T> innerList;

    public List<T> toList() {
        return this.innerList;
    }

    public Collection(List<T> list) {
        this.innerList = list;
    }

    public <R> Collection map(MapFunction<T, R> func) {
        if (func == null)
            return null;

        final List<R> newList = new ArrayList<R>();
        this.each((T t) -> {
            newList.add(func.invoke(t));
        });
        return new Collection<R>(newList);
    }

    public Collection filter(FilterFunction<T> func) {
        if (func == null) {
            return null;
        }
        final List<T> newList = new ArrayList<T>();
        this.each((T t) -> {
            if (func.invoke(t))
                newList.add(t);
        });
        return new Collection<T>(newList);
    }

    public Collection each(EachFunction<T> func) {
        if (func == null)
            return this;
        for (T t : this.innerList) {
            func.invoke(t);
        }
        return this;
    }

}


