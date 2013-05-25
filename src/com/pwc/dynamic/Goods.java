package com.pwc.dynamic;

public class Goods {
    private final int volume;
    private final int price;

    public Goods(int volume, int price) {
        this.volume = volume;
        this.price = price;
    }

    public int size() {
        return this.volume;
    }

    public int price() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.valueOf(this.size()) + ":" + this.price();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null) {
            throw new NullPointerException();
        }

        Goods goods = null;
        if (o instanceof Goods) {
            goods = (Goods) o;
        } else {
            throw new ClassCastException();
        }
        return this.price() == goods.price() && this.size() == goods.size();
    }
}
