package com.overlakehome.locals.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

public class Places {
    public static final Comparator<Place> ORDER_BY_CHECKINS_DESC = new Comparator<Place>() {
        @Override
        public int compare(Place lhs, Place rhs) {
            return rhs.getCheckins() - lhs.getCheckins();
        }
    };
    
    public static String toString(Context context, Location location) {
        StringBuilder sb = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context);
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                sb.append(addresses.get(0).getAddressLine(0));
                for (int i = 1; i < addresses.get(0).getMaxAddressLineIndex(); i++) {
                    sb.append(", ").append(addresses.get(0).getAddressLine(i));
                }
            }
        } catch (IOException e) {
            sb.append(String.format("%7.4f\u00B0, %7.4f\u00B0", location.getLatitude(), location.getLongitude()));
        }

        return sb.toString();
    }

    public static class Foursquare {
        private final static String FOURSQUARE_PLACES_SEARCH_URL = "https://api.foursquare.com/v2/venues/search?";
        private final static String FOURSQUARE_SPECIALS_SEARCH_URL = "https://api.foursquare.com/v2/specials/search?";

        private static DefaultHttpClient client = null;

        static {
            client = new DefaultHttpClient();
        }

        public static Object findSpecials(double latitude, double longitude, int limit) throws ClientProtocolException, IOException {
            String search = FOURSQUARE_SPECIALS_SEARCH_URL.concat(queryOfSpecials(latitude, longitude, limit));
            HttpResponse response = client.execute(new HttpGet(search));
            if (null == response) {
                return new Place[0];
            } else {
                // https://api.foursquare.com/v2/specials/search?ll=40.7,-73.9&oauth_token=BTXXZ04L2S3DB2S2HULMH2QQRNXO1O45JEU2FOMFWKZIYGKH&v=20110706
                // Name, Address, Message;
                JSONObject o = toJSONObject(response);
                return o; // toSpecials(response);
            }
        }

        public static Place[] findNearby(double latitude, double longitude, String query, int meters, int limit) throws InterruptedException, ExecutionException, ClientProtocolException, IOException, JSONException {
            String search = FOURSQUARE_PLACES_SEARCH_URL.concat(queryOf(latitude, longitude, query, meters, limit));
            HttpResponse response = client.execute(new HttpGet(search));
            if (null == response) {
                return new Place[0];
            } else {
                return toPlaces(response);
            }
        }

        private static Place[] toPlaces(HttpResponse response) throws InterruptedException, ExecutionException, JSONException, ParseException, IOException {
            JSONArray groups = toJSONObject(response).getJSONObject("response").getJSONArray("groups");
            for (int i = 0; i < groups.length(); i++) {
                JSONObject group = groups.getJSONObject(i);
                String type = group.getString("type");
                if ("nearby".equals(type) || "matches".equals(type) || "places".equals(type)) {
                    return toPlaces(group.getJSONArray("items"));
                }
            }

            throw new IllegalStateException("UNCHECKED: this bug should go unhandled.");
        }

        private static JSONObject toJSONObject(HttpResponse response) throws ParseException, IOException {
            try {
                return new JSONObject(EntityUtils.toString(response.getEntity()));
            } catch (JSONException e) {
                throw new IllegalStateException("UNCHECKED: this bug should go unhandled; the businesses: " + response, e);
            }
        }

        public static <T> T firstNonNull(T first, T second) {
            return first != null ? first : checkNotNull(second);
        }

        public static <T> T checkNotNull(T reference) {
            if (reference == null) {
                throw new NullPointerException();
            }

            return reference;
        }

        private static Place[] toPlaces(JSONArray items) throws InterruptedException, ExecutionException {
            Place[] places = new Place[items.length()];
            for (int i = 0; i < items.length(); i++) {
                try {
                    JSONObject item = items.getJSONObject(i);
                    JSONObject location = item.getJSONObject("location");
                    JSONObject contact = item.getJSONObject("contact");
                    JSONObject stats = item.getJSONObject("stats");
                    String address = firstNonNull(location.optString("address"), location.optString("crossStreet"));

                    // FIXME: See if it is a good idea to add 'specials', 'photos', and 'tips' into the place model.
                    places[i] = new Place(Source.Foursquare, UUID.randomUUID().toString(), item.getString("id"), item.getString("name"),
                            toClassifiers(item.getJSONArray("categories")), toSpecials(item.optJSONArray("specials")),
                            location.getDouble("lat"), location.getDouble("lng"), address, location.optString("city"),
                            location.optString("state"), "US", location.optString("postalCode"), null, 
                            contact.optString("phone"), null, stats.getInt("checkinsCount"), 
                            stats.getInt("usersCount"), item.getJSONObject("hereNow").getInt("count"));
                } catch (JSONException e) {
                    throw new IllegalStateException("UNCHECKED: this bug should go unhandled; the businesses: " + items, e);
                }
            }
            return places;
        }

        private static String[] toSpecials(JSONArray jsons) throws JSONException {
            if (null == jsons) return null;

            String[] specials = new String[jsons.length()];
            for (int i = 0; i < jsons.length(); i++) {
                specials[i] = jsons.getJSONObject(i).getString("message");
            }

            return specials;
        }

        private static String[] toClassifiers(JSONArray categories) throws JSONException {
            String[] classifiers = new String[categories.length()];
            for (int i = 0; i < categories.length(); i++) {
                classifiers[i] = categories.getJSONObject(i).getJSONArray("parents").getString(0);
            }

            return classifiers;
        }

        private static String queryOf(double latitude, double longitude, String query, double miles, int limit) {
            List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();
            qparams.add(new BasicNameValuePair("oauth_token", "LTPEYPHEF3UHHIXNIHT2WGSTPXSXVI41MEJYQTWUGRNOLGM5"));
            qparams.add(new BasicNameValuePair("ll", latitude + "," + longitude));
            qparams.add(new BasicNameValuePair("limit", Integer.toString(limit)));
            qparams.add(new BasicNameValuePair("intent", "checkin"));
            if (null != query && 0 != query.length()) {
                qparams.add(new BasicNameValuePair("query", query));
            }

            return URLEncodedUtils.format(qparams, "UTF-8");
        }

        private static String queryOfSpecials(double latitude, double longitude, int limit) {
            List<BasicNameValuePair> qparams = new ArrayList<BasicNameValuePair>();
            qparams.add(new BasicNameValuePair("oauth_token", "LTPEYPHEF3UHHIXNIHT2WGSTPXSXVI41MEJYQTWUGRNOLGM5"));
            qparams.add(new BasicNameValuePair("ll", latitude + "," + longitude));
            qparams.add(new BasicNameValuePair("limit", Integer.toString(limit)));

            return URLEncodedUtils.format(qparams, "UTF-8");
        }
    }

//    public static class Gowalla {
//        private final static String GOWALLA_API_KEY = "fa574894bddc43aa96c556eb457b4009";
//        private final static String GOWALLA_PLACES_SEARCH_URL = "https://api.gowalla.com/spots?";
//
//        private final static int GOWALLA_FOOD = 7; // Food
//        private final static int GOWALLA_THEATRE = 103; // Theatre
//    }
}