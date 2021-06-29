package com.example.newawareness.Utilities;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class Utilities {
    public static String[] getHeadphoneList() {
        return new String[]{"Plugged In", "Plugged Out",};
    }

    public static String getHeadphoneItemFromIndexNumber(int index) {
        String item = getHeadphoneList()[index];
        return item;
    }

    public static String[] getWeatherList() {
        return new String[]{"Thunderstorm", "Drizzle", "Rain", "Snow", "Clear", "Clouds", "Snowy", "Stormy", "Windy"};
    }
    public static String getWeatherItemFromIndexNumber(int index) {
        String item = getWeatherList()[index];
        return item;
    }
    public static int getIndexPhysicalActivitydetected(int index){
        if(index==4 || index ==5){
            return index +3;
        }
            return index;
    }
    public static String[] getPhysicalActivityList() {
        return new String[]{"In Vehicle", "On Bicycle", "On Foot", "Still", "Walking","Running"};
    }
    public static String getPhysicalActivityItemFromIndexNumber(int index) {


        String item = getPhysicalActivityList()[index];
        return item;
    }

    public static String[] getActionList() {
        return new String[]{"Open Application", "Show notification"};
    }

    public static List<ApplicationInfo> getAppList() {
        PackageManager packageManager;
        packageManager = null;
        List<ApplicationInfo> appList;
        appList = (packageManager.getInstalledApplications(packageManager.GET_META_DATA));
        return appList;
    }

    public static List<ApplicationInfo> getAppListindex(int index) {
        ApplicationInfo item = getAppList().get(index);
        return (List<ApplicationInfo>) item;
    }

    public static String getActionItemFromIndexNumber(int index) {
        String item = getActionList()[index];
        return item;
    }


}
