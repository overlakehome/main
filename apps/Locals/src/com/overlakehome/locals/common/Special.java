package com.overlakehome.locals.common;

public class Special {
    private final Source source;
    private final String id;
    private final String remoteId;
    private final String message;
    private final String finePrint;
    private final String title;
    private final String provider;
    private final String streetAddress;
    private final String city;
    private final String state;
    private final String country;
    private final String postcode;
    private final double latitude;
    private final double longitude;
    private final String name;
    private final String phone;
    private final String url;
    private final int reviewCount;
    private final double reviewAverage;
    private final Review[] reviews;
    private final String[] classifiers;

    public Special(
            Source source, String id, String remoteId, String message, String finePrint, 
            String title, String provider, String streetAddress, String city, String state, 
            String country, String postcode, double latitude, double longitude, String name, 
            String phone, String url, int reviewCount, double reviewAverage, Review[] reviews, 
            String[] classifiers) {

        this.source = source;
        this.id = id;
        this.remoteId = remoteId;
        this.message = message;
        this.finePrint = finePrint;
        this.title = title;
        this.provider = provider;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.url = url;
        this.reviewCount = 0;
        this.reviewAverage = 0.0;
        this.reviews = null;
        this.classifiers = classifiers;
    }
    
    public Special(
            Source source, String id, String remoteId, String message, String finePrint, 
            String title, String provider, String streetAddress, String city, String state, 
            String country, String postcode, double latitude, double longitude, String name, 
            String phone, String url, String[] classifiers) {

        this.source = source;
        this.id = id;
        this.remoteId = remoteId;
        this.message = message;
        this.finePrint = finePrint;
        this.title = title;
        this.provider = provider;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postcode = postcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.url = url;
        this.reviewCount = 0;
        this.reviewAverage = 0.0;
        this.reviews = null;
        this.classifiers = classifiers;
    }

    public String toString() {
        return "";
//        return Objects.toStringHelper(this)
//            .add("name", name)
//            .add("classifiers", Joiner.on(", ").join(classifiers))
//            .add("address", streetAddress)
//            .add("city", city)
//            .add("state", state)
//            .toString();
    }

    public Source getSource() {
        return source;
    }

    public String getId() {
        return id;
    }
    
    public String getRemoteId() {
        return remoteId;
    }

    public String getMessage() {
        return message;
    }
    
    public String getFinePrint() {
        return finePrint;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getProvider() {
        return provider;
    }
    
    public String getAddress() {
        if (null == streetAddress || 0 == streetAddress.length()) {
           return String.format("%s, %s, %s", city, state);
        } else {
            return String.format("%s, %s, %s", streetAddress, city, state);
        }
    }

    public String getStressAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }
    

    public String getState() {
        return state;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String getPostcode() {
        return postcode;
    }
    
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    
    public String getName() {
        return name;
    }

    public String getPhone() {
        if (phone.length() < 10) {
            return phone;
        } else {
        String phoneArea = phone.substring(0, 3);
        String phoneExch = phone.substring(3, 6);
        String phoneNum = phone.substring(6);
        String phoneNumber = "("+phoneArea+") "+phoneExch +"-" + phoneNum;
        return phoneNumber;
        }

    }

    public String getUrl() {
        return url;
    }
    
    public int getReviewCount() {
        return reviewCount;
    }

    public double getReviewAverage() {
        return reviewAverage;
    }

    public Review[] getReviews() {
        return reviews;
    }
    
    public String[] getClassifiers() {
        return classifiers;
    }
}
