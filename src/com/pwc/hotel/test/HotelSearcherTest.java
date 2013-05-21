package com.pwc.hotel.test;

import com.pwc.hotel.Hotel;
import com.pwc.hotel.HotelSearcher;
import com.pwc.hotel.IRepository;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class HotelSearcherTest {
    private IRepository repository;

    @Before
    public void setUp() {
        repository = EasyMock.createMock(IRepository.class);
        Hotel[] hotels = new Hotel[6];

        hotels[0] = new Hotel("LakeWood", "Regular", 3, new int[]{110, 110, 110, 110, 110, 90, 90});
        hotels[1] = new Hotel("LakeWood", "Rewards", 3, new int[]{80, 80, 80, 80, 80, 80, 80});
        hotels[2] = new Hotel("BridgeWood", "Regular", 4, new int[]{160, 160, 160, 160, 160, 60, 60});
        hotels[3] = new Hotel("BridgeWood", "Rewards", 4, new int[]{110, 110, 110, 110, 110, 50, 50});
        hotels[4] = new Hotel("RidgeWood", "Regular", 5, new int[]{220, 220, 220, 220, 220, 150, 150});
        hotels[5] = new Hotel("RidgeWood", "Rewards", 5, new int[]{100, 100, 100, 100, 100, 40, 40});

        EasyMock.expect(repository.getHotel()).andReturn(hotels);
        EasyMock.replay(repository);
    }

    @After
    public void clean() {
        repository = null;
    }

    @Test
    public void testSearch1() throws Exception {
        String key = "Regular: 16Mar2009(mon), 17Mar2009(tues),18Mar2009(wed)";
        HotelSearcher searcher = HotelSearcher.getInstance();
        searcher.setRepository(repository);
        String expected = "LakeWood";
        assertEquals(expected, searcher.Search(key));
    }

    @Test
    public void testSearch2() throws Exception {
        String key = "Regular: 20Mar2009(fri), 21Mar2009(sat),22Mar2009(sun)";
        HotelSearcher searcher = HotelSearcher.getInstance();
        searcher.setRepository(repository);
        String expected = "BridgeWood";
        assertEquals(expected, searcher.Search(key));
    }

    @Test
    public void testSearch3() throws Exception {
        String key = "Rewards: 26Mar2009(thur), 27Mar2009(fri),28Mar2009(sat)";
        HotelSearcher searcher = HotelSearcher.getInstance();
        searcher.setRepository(repository);
        String expected = "RidgeWood";
        assertEquals(expected, searcher.Search(key));
    }

    @Test
    public void testGetHotelByType() throws Exception {

        String type = "Rewards";
        HotelSearcher searcher = HotelSearcher.getInstance();
        searcher.setRepository(repository);

        Hotel[] expected = new Hotel[3];
        expected[0] = new Hotel("LakeWood", "Rewards", 3, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[1] = new Hotel("BridgeWood", "Rewards", 4, new int[]{110, 110, 110, 110, 110, 80, 80});
        expected[2] = new Hotel("RidgeWood", "Rewards", 5, new int[]{110, 110, 110, 110, 110, 80, 80});

        Hotel[] result = searcher.getHotelByType(type);

        assertEquals(expected.length, result.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }

}
