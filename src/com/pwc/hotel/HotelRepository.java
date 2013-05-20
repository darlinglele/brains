package com.pwc.hotel;

import com.pwc.brains.Util;
import com.pwc.brains.btree.ObjectSerializationException;

import java.io.Serializable;

public class HotelRepository implements IRepository, Serializable {
    private long serialVersionUID = 232332421;
    private Hotel[] hotels;

    public HotelRepository() {
        HotelRepository repository = (HotelRepository) Util.deserialize("hotels.xml");
        if (repository != null) {
            hotels = repository.getHotel();
        }
    }

    @Override
    public Hotel[] getHotel() {
        return hotels;
    }

    @Override
    public void save(Hotel[] hotels) throws ObjectSerializationException {
        this.hotels = hotels;
        Util.serialize("hotels.xml", this);
    }
}
