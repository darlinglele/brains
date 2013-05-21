package com.pwc.hotel;

public class RepositoryFactory {
    public static IRepository getInstance() {
        return new HotelRepository("hotels.xml");
    }
}
