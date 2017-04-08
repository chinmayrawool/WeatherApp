package com.mad.homework08weather;

/**
 * Created by neha5 on 07-04-2017.
 */

public class CityDetails {

    private String cityKey, cityName, countryCode, tempCel, lastUpdated;
    private boolean favorite;

    public CityDetails(){

    }


    public CityDetails(String cityKey, String cityName, String countryCode, String tempCel, String lastUpdated, boolean favorite) {
        this.cityKey = cityKey;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.tempCel = tempCel;
        this.lastUpdated = lastUpdated;
        this.favorite = favorite;
    }

    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String cityKey) {
        this.cityKey = cityKey;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTempCel() {
        return tempCel;
    }

    public void setTempCel(String tempCel) {
        this.tempCel = tempCel;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "CityDetails{" +
                "cityKey='" + cityKey + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", tempCel='" + tempCel + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
