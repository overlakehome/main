package com.overlakehome.locals;

public class SearchResults {
    private String name = "";
    private String address = "";
    private String distance = "";
    private int categoryIcon = 1;

    public void setName(String name) {
     this.name = name;
    }

    public String getName() {
     return name;
    }

    public void setAddress(String address) {
     this.address = address;
    }

    public String getAddress() {
     return address;
    }

    public void setDistance(String distance) {
     this.distance = distance;
    }

    public String getDistance() {
     return distance;
    }

    public void setCategoryIcon(int categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }
}