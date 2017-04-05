package com.mad.homework08weather;

import java.io.Serializable;

/**
 * Created by Chinmay Rawool on 4/4/2017.
 */

public class Location implements Serializable{

    String cityName, countryName, countryCode, time, icon, tempCel, tempF;
    boolean tempUnit = false; //false=Celsius, true= fahrenheit

    public boolean isTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(boolean tempUnit) {
        this.tempUnit = tempUnit;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTempCel() {
        return tempCel;
    }

    public void setTempCel(String tempCel) {
        this.tempCel = tempCel;
    }

    public String getTempF() {
        return tempF;
    }

    public void setTempF(String tempF) {
        this.tempF = tempF;
    }


    public Location(String cityName, String countryName, String countryCode, String time, String icon, String tempCel, String tempF, boolean tempUnit) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.time = time;
        this.icon = icon;
        this.tempCel = tempCel;
        this.tempF = tempF;
        this.tempUnit = tempUnit;
    }

    @Override
    public String toString() {
        return "Location{" +
                "cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", time='" + time + '\'' +
                ", icon='" + icon + '\'' +
                ", tempCel='" + tempCel + '\'' +
                ", tempF='" + tempF + '\'' +
                ", tempUnit=" + tempUnit +
                '}';
    }
}
