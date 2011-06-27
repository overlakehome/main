package com.overlakehome.locals;

public class SearchResults {
    private String name = "";
    private String cityState = "";
    private String phone = "";
    private int categoryIcon = 1;

    public void setName(String name) {
     this.name = name;
    }

    public String getName() {
     return name;
    }

    public void setCityState(String cityState) {
     this.cityState = cityState;
    }

    public String getCityState() {
     return cityState;
    }

    public void setPhone(String phone) {
     this.phone = phone;
    }

    public String getPhone() {
     return phone;
    }

    public void setCategoryIcon(int categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public int getCategoryIcon() {
        return categoryIcon;
    }
}