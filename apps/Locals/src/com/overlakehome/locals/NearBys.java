package com.overlakehome.locals;

import java.util.HashMap;
import java.util.Map;

import android.location.Location;

import com.overlakehome.locals.common.Place;
import com.overlakehome.locals.common.Places;

public class NearBys {
    public static enum XXXType {
        Food, Entertainment, Park;
    }

    private static Map<String, XXXType> map = new HashMap<String, XXXType>();

    private static NearBys nearBys = new NearBys();
    private Place[] places;
    private Location base;

    static {
        map.put("4Square/XXX/YYYY", XXXType.Food); // XXXX, YYYY
        map.put("4Square/XXX/ZZZZ", XXXType.Entertainment);
        map.put("Gowalla/XXX/YYYY", XXXType.Park);
    }

    public static NearBys getInstance() {
        return nearBys;
    }

    public void setLocation(Location location) {
        this.base = location;
    }

    public Location getLocation() {
        return base;
    }

    public void findNearBys() {
        try {
            Place[] places = Places.Foursquare.findNearby(base.getLatitude(), base.getLongitude(), null, 100, 50);
            if (null != places && places.length > 0) {
                this.places = places;
            }
        } catch (Exception e) { // TODO: get right exception handling.
        }
    }

    public double distanceTo(Place place) {
        return distanceTo(place.getLatitude(), place.getLongitude());
    }

    private double distanceTo(double latitude, double longitude) {
        double pk = (180/3.14169);

        double a1 = base.getLatitude() / pk;
        double a2 = base.getLongitude() / pk;
        double b1 = latitude / pk;
        double b2 = longitude / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
       
        return 3963167*tt;
    }

    public Place[] getPlaces() {
        return places;
    }

}
