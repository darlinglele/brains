package com.pwc.collect;

public interface MapFunction<T, R> {
    R invoke(T t);
}
