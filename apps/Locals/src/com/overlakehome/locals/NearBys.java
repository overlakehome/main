package com.overlakehome.locals;

import java.util.HashMap;
import java.util.Map;

import android.location.Location;

import com.overlakehome.locals.common.Place;
import com.overlakehome.locals.common.Places;
import com.overlakehome.locals.common.Special;

public class NearBys {
    public static enum Clazz {
        Food(R.drawable.ic_categ_food), Nightlife(R.drawable.ic_categ_nightlife), Shopping(R.drawable.ic_categ_shopping), Entertainment(R.drawable.ic_categ_entertainment), Education(R.drawable.ic_categ_edu), Travel(R.drawable.ic_categ_travel), Outdoor(R.drawable.ic_categ_outdoor), Other(R.drawable.ic_categ_other);

        private final int drawableId;

        Clazz(int drawableId) {
            this.drawableId = drawableId;
        }

        public int getDrawableId() {
            return this.drawableId;
        }
    }

    private static Map<String, Clazz> map = new HashMap<String, Clazz>();

    private static NearBys nearBys = new NearBys();
    private Place[] places;
    private Special[] specials;
    private Location base;

    static {
        map.put("Great Outdoors", Clazz.Outdoor);
        map.put("Food", Clazz.Food);
        map.put("Arts & Entertainment", Clazz.Entertainment);
        map.put("College & Education", Clazz.Education);
        map.put("Home, Work, Other", Clazz.Other);
        map.put("Nightlife Spots", Clazz.Nightlife);
        map.put("Shops", Clazz.Shopping);
        map.put("Travel Spots", Clazz.Travel);
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

            // TODO:
            Special[] specials = Places.Foursquare.findSpecials(base.getLatitude(), base.getLongitude(), 50);
            if (null != specials && specials.length > 0) {
                this.specials = specials;
            }
        } catch (Exception e) { // TODO: get right exception handling.
        }
    }

    private static Clazz toClazz(Place place) {
        return place.getClassifiers().length > 0 && map.containsKey(place.getClassifiers()[0]) ? map.get(place.getClassifiers()[0]) : Clazz.Other;
    }

    private static Clazz toClazz(Special special) {
        return special.getClassifiers().length > 0 && map.containsKey(special.getClassifiers()[0]) ? map.get(special.getClassifiers()[0]) : Clazz.Other;
    }
    
    public static int toDrawableId(Place place) {
        return NearBys.toClazz(place).getDrawableId();
    }
    
    public static int toDrawableId(Special special) {
        return NearBys.toClazz(special).getDrawableId();
    }

    public static double toDistance(Place place) {
        return distanceTo(place.getLatitude(), place.getLongitude());
    }

    private static double distanceTo(double latitude, double longitude) {
        double pk = (180 / 3.14169);

        double a1 = nearBys.base.getLatitude() / pk;
        double a2 = nearBys.base.getLongitude() / pk;
        double b1 = latitude / pk;
        double b2 = longitude / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        return 3963167 * tt;
    }

    public Place[] getPlaces() {
        return places;
    }
    
    public Special[] getSpecials() {
        return specials;
    }

}
