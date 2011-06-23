package com.overlakehome.locals;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Places {
    private final static String FOURSQUARE_PLACES_SEARCH_URL = "https://api.foursquare.com/v2/venues/search?";
    private final static String FOURSQUARE_PLACES_TRENDING_URL = "https://api.foursquare.com/v2/venues/trending?";

    private static DefaultHttpClient client = null;

    static {
        client = new DefaultHttpClient();
    }

    public static Place[] findNearby(double latitude, double longitude, String query, int meters, int limit) throws InterruptedException, ExecutionException, ClientProtocolException, IOException, JSONException {

        // UNDONE: String search = FOURSQUARE_PLACES_SEARCH_URL.concat(queryOf(latitude, longitude, query, meters, limit));
    	String search = "https://api.foursquare.com/v2/venues/search?oauth_token=LTPEYPHEF3UHHIXNIHT2WGSTPXSXVI41MEJYQTWUGRNOLGM5&ll=47.59755%2C-122.327752&limit=500&intent=checkin";
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
              places[i] = new Place(
                  Source.Foursquare, UUID.randomUUID().toString(), item.getString("id"), item.getString("name"), toClassifiers(item.getJSONArray("categories")),
                  location.getDouble("lat"), location.getDouble("lng"),
                  address, location.optString("city"), location.optString("state"), "US", location.optString("postalCode"),
                  null, contact.optString("phone"), null,
                  stats.getInt("checkinsCount"), stats.getInt("usersCount"), item.getJSONObject("hereNow").getInt("count"));
          } catch (JSONException e) {
              throw new IllegalStateException("UNCHECKED: this bug should go unhandled; the businesses: " + items, e);
          }
      }

      return places;
    }

    private static String[] toClassifiers(JSONArray categories) throws JSONException {
        String[] classifiers = new String[categories.length()];
        for (int i = 0; i < categories.length(); i++) {
            classifiers[i] = categories.getJSONObject(i).getString("name");
        }

        return classifiers;
    }
}
