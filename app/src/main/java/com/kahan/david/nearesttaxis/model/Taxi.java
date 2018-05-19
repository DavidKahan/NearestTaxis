package com.kahan.david.nearesttaxis.model;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by david on 17/05/2018.
 */
public class Taxi {

    private int etaInMin, stationIconUrl;
    private String stationName;

    public Taxi(int stationIconUrl, String stationName) {
        this.stationIconUrl = stationIconUrl;
        this.stationName = stationName;
    }

    public int getStationIconUrl() {
        return stationIconUrl;
    }

    public void setStationIconUrl(int stationIconUrl) {
        this.stationIconUrl = stationIconUrl;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getEtaInMin() {
        return etaInMin;
    }

    public void setEtaInMin(int etaInMin) {
        this.etaInMin = etaInMin;
    }

    public String getEtaStr() {
        long hours = TimeUnit.MINUTES.toHours(etaInMin);
        long remainMinute = etaInMin - TimeUnit.HOURS.toMinutes(hours);
        Locale locale = Locale.getDefault();
        if (hours <= 0) {
            return String.format(locale, "%2d", remainMinute) + "m";
        }

        if (remainMinute <= 0) {
            return String.format(locale, "%2d", hours) + "h ";
        }

        return String.format(locale, "%2d", hours) + "h "
                + String.format(locale, "%2d", remainMinute) + "m";
    }


}
