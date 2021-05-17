package com.example.newawareness;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.DetectedActivityFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.awareness.fence.LocationFence;
import com.google.android.gms.awareness.fence.TimeFence;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class FenceCreateUtilites {
    private static final String FENCE_RECEIVER_ACTION = BuildConfig.APPLICATION_ID +
            "FENCE_RECEIVER_ACTION";
    private static final String FENCE_KEY_Headphone = "Headphone";
    private static final String FENCE_KEY_Location = "Location";
    private static final String FENCE_KEY_PhysicalActivity = "PhysicalActivity";
    private FenceReceiver mFenceReceiver;
    private PendingIntent mPendingIntent;

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

    public AwarenessFence createphysicalactivityFence(int state) {
        AwarenessFence fencephysicalActivityState = DetectedActivityFence.during(state);
                return fencephysicalActivityState;
    }

    public static AwarenessFence createTimeDateFence(long time1,long time2,Context context) {

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        AwarenessFence TimeDateFence = TimeFence.inInterval(time1, time2);

        return TimeDateFence;

    }
    public static  AwarenessFence getFinalFence(ArrayList<AwarenessFence> list){
        AwarenessFence FinalFence;
        FinalFence=AwarenessFence.and(list);
        return FinalFence;
    }
    public static void registerUpdateFinalFence(AwarenessFence FinalFence,Context context){
        FenceReceiver mFenceReceiver = new FenceReceiver();
         GoogleApiClient client = new GoogleApiClient.Builder(context)
         .addApi(Awareness.API)
         .build();
         client.connect();
         PendingIntent mPendingIntent = PendingIntent.getBroadcast(context, 0,
         new Intent(FENCE_RECEIVER_ACTION), 0);
         context.registerReceiver(mFenceReceiver, new IntentFilter(FENCE_RECEIVER_ACTION));
         Awareness.getFenceClient(context).updateFences( new FenceUpdateRequest.Builder()
         .addFence(FENCE_KEY_PhysicalActivity, FinalFence, mPendingIntent)
         .build())
         .addOnSuccessListener( new OnSuccessListener<Void>() {
        @Override public void onSuccess(Void aVoid) {
        Log.i(FENCE_KEY_PhysicalActivity, "Successfully registered.");
        }
        })
         .addOnFailureListener(new OnFailureListener() {
        @Override public void onFailure(@NonNull Exception e) {
        Log.e(FENCE_KEY_PhysicalActivity, "Could not be registered: " + e.getLocalizedMessage());
        }
        });


    }

         }













