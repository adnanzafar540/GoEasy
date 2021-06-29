package com.example.newawareness.Objects;

public class ObjectSituation {
    int weather = -1;
    int action;
    int activity=-1;
    int headphone=-1;
    Boolean active;
    String appname;
    String minutes;
    String hours;
    String situationname, locationname, date, notification;
    long time;
    Double lat;
    Double longi;
    String headphone_txt;
    String weather_txt;
    String activity_txt;
    String Situation_name;
    String date_txt;
    String time_txt;
    Boolean isSwitchActive;

    public boolean isIs_activitySelected() {
        return is_activitySelected;
    }

    public void setIs_activitySelected(boolean is_activitySelected) {
        this.is_activitySelected = is_activitySelected;
    }

    boolean is_WeatherSelected;

    public boolean isIs_WeatherSelected() {
        return is_WeatherSelected;
    }

    public void setIs_WeatherSelected(boolean is_WeatherSelected) {
        this.is_WeatherSelected = is_WeatherSelected;
    }

    boolean is_activitySelected;

    public boolean isIs_HeadphonESelected(boolean b) {
        return is_HeadphonESelected;
    }

    public void setIs_HeadphonESelected(boolean is_HeadphonESelected) {
        this.is_HeadphonESelected = is_HeadphonESelected;
    }

    boolean is_HeadphonESelected;


    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    String city_name;
    String country_name;

    public Boolean getSwitchActive() {
        return isSwitchActive;
    }

    public void setSwitchActive(Boolean switchActive) {
        isSwitchActive = switchActive;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getHeadphone() {
        return headphone;
    }

    public String getSituation_name() {
        return Situation_name;
    }

    public void setSituation_name(String situation_name) {
        Situation_name = situation_name;
    }

    public void setHeadphone(int headphone) {
        this.headphone = headphone;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public int getWeather() {
        return weather;
    }

    public void setWeather(int weather) {
        this.weather = weather;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public String getSituationname() {
        return situationname;
    }

    public void setSituationname(String situationname) {
        this.situationname = situationname;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }


    public String getHeadphone_txt() {
        return headphone_txt;
    }

    public void setHeadphone_txt(String headphone_txt) {
        this.headphone_txt = headphone_txt;
    }

    public String getWeather_txt() {
        return weather_txt;
    }

    public void setWeather_txt(String weather_txt) {
        this.weather_txt = weather_txt;
    }

    public String getActivity_txt() {
        return activity_txt;
    }

    public void setActivity_txt(String activity_txt) {
        this.activity_txt = activity_txt;
    }

    public String getDate_txt() {
        return date_txt;
    }

    public void setDate_txt(String date_txt) {
        this.date_txt = date_txt;
    }

    public String getTime_txt() {
        return time_txt;
    }

    public void setTime_txt(String time_txt) {
        this.time_txt = time_txt;
    }
}
