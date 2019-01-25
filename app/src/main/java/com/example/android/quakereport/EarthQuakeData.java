package com.example.android.quakereport;
public class EarthQuakeData {
    private double mag;
    private String location, websiteLink;
    private long date;
    public EarthQuakeData(double mag, String location, long date, String websiteLink){
        this.mag = mag;
        this.location = location;
        this.date = date;
        this.websiteLink = websiteLink;
    }

    public double getMag() {
        return mag;
    }

    public long getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getWebsiteLink(){
        return websiteLink;
    }
}
