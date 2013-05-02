package com.pwc.dynamic;

public class Goods {
    private final int volume;
    private final int price;

    public Goods(int volume, int price) {
        this.volume = volume;
        this.price = price;
    }

    public int volume() {
        return this.volume;
    }

    public int price() {
        return this.price;
    }
}
