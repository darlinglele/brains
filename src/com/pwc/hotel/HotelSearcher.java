package com.pwc.hotel;

import java.util.*;

public class HotelSearcher {
    private static HotelSearcher instance = null;
    private static byte[] object = new byte[1];
    private IRepository repository;

    private HotelSearcher() {

    }

    public static synchronized HotelSearcher getInstance() {
        if (instance == null) {
            instance = new HotelSearcher();
        }
        if (instance.repository == null) {
            instance.setRepository(RepositoryFactory.getInstance());
        }

        return instance;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    public String Search(String condition) {
        Condition con = Condition.parse(condition);
        Hotel best = cheapest(con);
        return best.name();
    }

    private Hotel cheapest(Condition condition) {
        Hotel[] hotels = getHotelByType(condition.type());
        int min = 0x7fffffff;
        int rating = 0;
        Hotel result = null;
        for (int i = 0; i < hotels.length; i++) {
            int sum = 0;
            for (int j = 0; j < 7; j++) {
                sum += hotels[i].price(j) * condition.days[j];
            }
            if (sum <= min) {
                min = sum;
                if (hotels[i].rating() > rating) {
                    result = hotels[i];
                }
            }
        }
        return result;
    }

    public Hotel[] getHotelByType(String type) {
        Hotel[] hotels = this.repository.getHotel();
        List<Hotel> results = new ArrayList<Hotel>();
        for (int i = 0; i < hotels.length; i++) {
            if (hotels[i].type().equals(type)) {
                results.add(hotels[i]);
            }
        }
        return results.toArray(new Hotel[results.size()]);
    }
}
