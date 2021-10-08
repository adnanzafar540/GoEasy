package com.example.newawareness;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.fence.LocationFence;
import com.google.android.gms.awareness.fence.TimeFence;

import java.util.ArrayList;

import static com.google.android.gms.awareness.fence.DetectedActivityFence.starting;


public class FenceCreateUtilitesActivity {

    public static AwarenessFence createHeadphonrFence(int state) {
        AwarenessFence fenceHeadPhoneState = HeadphoneFence.during(state);
        return fenceHeadPhoneState;
    }
    public static AwarenessFence createLocationFence(double logitutude, double latitude, double radius, Long l, Context context) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        AwarenessFence fenceLocationState = LocationFence.in(logitutude, latitude, radius, l);
        return fenceLocationState;
    }
    public static AwarenessFence createphysicalactivityFence(int state) {
        AwarenessFence fencephysicalActivityState = starting(state);
        return fencephysicalActivityState;
    }
    public static AwarenessFence createTimeDateFence(long time1, long time2, Context context) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        AwarenessFence TimeDateFence = TimeFence.inInterval(time1, Long.MAX_VALUE);
        return TimeDateFence;
    }
    public static AwarenessFence getFinalFence(ArrayList<AwarenessFence> list) {
        AwarenessFence FinalFence;
        FinalFence = AwarenessFence.and(list);
        return FinalFence;
    }
}