package com.example.newawareness;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.fence.LocationFence;
import com.google.android.gms.awareness.fence.TimeFence;

import java.util.ArrayList;

public class FenceCreateUtilites {
/*    private static final String FENCE_RECEIVER_ACTION = BuildConfig.APPLICATION_ID +
            "FENCE_RECEIVER_ACTION";
    private static final String FENCE_KEY_Headphone = "Headphone";
    private static final String FENCE_KEY_Location = "Location";
    private static final String FENCE_KEY_PhysicalActivity = "PhysicalActivity";
    private FenceReceiver mFenceReceiver;
    private PendingIntent mPendingIntent;*/

    public static AwarenessFence createHeadphonrFence(int state) {
        AwarenessFence fenceHeadPhoneState = HeadphoneFence.during(state);
        return fenceHeadPhoneState;
    }

    public static AwarenessFence createLocationFence(double logitutude, double latitude, double radius, long l, Context context) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        }
        AwarenessFence fenceLocationState = LocationFence.in(logitutude, latitude, radius, l);
        return fenceLocationState;


    }

    public static AwarenessFence createphysicalactivityFence(int state) {
        AwarenessFence fencephysicalActivityState = DetectedActivityFence.during(state);
        return fencephysicalActivityState;
    }

    public static AwarenessFence createTimeDateFence(long time1,long time2,Context context) {

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
       // AwarenessFence TimeFence= com.google.android.gms.awareness.fence.TimeFence.inTimeInterval(time1);
        AwarenessFence TimeDateFence = TimeFence.inInterval(time1, Long.MAX_VALUE);

        return TimeDateFence;

    }
    public static  AwarenessFence getFinalFence(ArrayList<AwarenessFence> list){
        AwarenessFence FinalFence;
        FinalFence=AwarenessFence.and(list);
        return FinalFence;
    }


}