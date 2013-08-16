package com.pwc.collect;

public  interface FilterFunction<T> {
    boolean invoke(T t);
}
