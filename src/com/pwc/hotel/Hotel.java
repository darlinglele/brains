package com.pwc.hotel;

import java.io.Serializable;

public class Hotel implements Serializable {
    private long serialVersionUID = 23233421;
    private String type;
    private String name;
    private int rating;
    private int[] prices;

    public Hotel(String name, String type, int rating, int[] prices) {
        this.name = name;
        this.type = type;
        this.rating = rating;
        this.prices = prices;
    }

    public int price(int j) {
        return this.prices[j];
    }

    public int rating() {
        return this.rating;
    }

    public String name() {
        return this.name;
    }

    public String type() {
        return this.type;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }

        Hotel hotel = null;
        if (o instanceof Hotel) {
            hotel = (Hotel) o;
        } else {
            throw new ClassCastException();
        }
        return this.name().equals(hotel.name()) && this.type().equals(hotel.type()) && this.rating() == hotel.rating();
    }
}
