package com.overlakehome.locals;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;

import com.overlakehome.locals.common.Place;
import com.overlakehome.locals.common.Places;

public class NearBys {
    private static NearBys nearBys = new NearBys();
    private List<Place> places = new ArrayList<Place>();
    private Location location;

    public static NearBys getInstance() {
        return nearBys;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void findNearBys() {
        try {
            Place[] places = Places.Foursquare.findNearby(location.getLatitude(), location.getLongitude(), null, 100, 50);
            if (null != places && places.length > 0) {
                this.places.clear();
                for (Place place : places) {
                    this.places.add(place);
                }
            }
        } catch (Exception e) { // TODO: get right exception handling.
        }
    }

    public double distanceTo(Place place) {
        return distanceTo(place.getLatitude(), place.getLongitude());
    }

    private double distanceTo(double latitude, double longitude) {
        double pk = (180/3.14169);

        double a1 = location.getLatitude() / pk;
        double a2 = location.getLongitude() / pk;
        double b1 = latitude / pk;
        double b2 = longitude / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        double t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
       
        return 3963167*tt;
    }

}
