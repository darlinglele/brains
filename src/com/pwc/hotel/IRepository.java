package com.pwc.hotel;

import com.pwc.brains.btree.ObjectSerializationException;

public interface IRepository {
    Hotel[] getHotel();
    void save(Hotel[] hotels) throws ObjectSerializationException;
}
