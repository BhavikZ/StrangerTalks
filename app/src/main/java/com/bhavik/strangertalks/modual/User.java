package com.bhavik.strangertalks.modual;

import android.net.Uri;

public class User {
    private String uId,name,profile,city;
    private long coins;

    public User() {
    }

    public User(String uId, String name, Uri profile, String city, long coins) {
        this.uId = uId;
        this.name = name;
        this.profile = String.valueOf(profile);
        this.city = city;
        this.coins=coins;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

}
