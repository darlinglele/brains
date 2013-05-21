package com.pwc.hotel.test;

import com.pwc.hotel.Hotel;
import com.pwc.hotel.HotelRepository;
import com.pwc.hotel.IRepository;
import com.pwc.test.TestBase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HotelRepositoryTest extends TestBase {


    @Test
    public void testGetHotel() throws Exception {
        createHotelsData();
        IRepository repository = new HotelRepository("hotels.xml");
        gc.add("hotels.xml");
        Hotel[] expected = new Hotel[6];
        expected[0] = new Hotel("LakeWood", "Regular", 3, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[1] = new Hotel("LakeWood", "Rewards", 3, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[2] = new Hotel("BridgeWood", "Regular", 4, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[3] = new Hotel("BridgeWood", "Rewards", 4, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[4] = new Hotel("RidgeWood", "Regular", 5, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[5] = new Hotel("RidgeWood", "Rewards", 5, new int[]{110, 110, 110, 110, 110, 80, 80});
        Hotel[] hotels = repository.getHotel();
        for (int i = 0; i < hotels.length; i++) {
            assertEquals(expected[i], hotels[i]);
        }
    }

    private void createHotelsData() throws Exception {
        Hotel[] hotels = new Hotel[6];
        hotels[0] = new Hotel("LakeWood", "Regular", 3, new int[]{110, 110, 110, 110, 110, 80, 80});
        hotels[1] = new Hotel("LakeWood", "Rewards", 3, new int[]{110, 110, 110, 110, 110, 80, 80});
        hotels[2] = new Hotel("BridgeWood", "Regular", 4, new int[]{110, 110, 110, 110, 110, 80, 80});
        hotels[3] = new Hotel("BridgeWood", "Rewards", 4, new int[]{110, 110, 110, 110, 110, 80, 80});
        hotels[4] = new Hotel("RidgeWood", "Regular", 5, new int[]{110, 110, 110, 110, 110, 80, 80});
        hotels[5] = new Hotel("RidgeWood", "Rewards", 5, new int[]{110, 110, 110, 110, 110, 80, 80});
        IRepository repository = new HotelRepository("hotels.xml");
        repository.save(hotels);
    }
}
