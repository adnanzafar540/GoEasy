package com.example.newawareness;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.newawareness.Objects.ObjectSituation;
import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.FenceState;
import com.google.android.gms.awareness.snapshot.WeatherResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FenceReceiver extends BroadcastReceiver {
    String fenceKey;
    Context context;
    private static final String TAG = "FenceReceiver";

    public FenceReceiver(Activity activityContext) {
        context = activityContext;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);
        switch (fenceState.getCurrentState()) {
            case FenceState.TRUE:
                fenceKey = fenceState.getFenceKey();
                /**DatabaseClass databaseClass = new DatabaseClass(context);
                ObjectSituation objectSituation = databaseClass.checkKey_GetData(fenceKey);
             if(checkDate(objectSituation)){
                 Log.i(TAG, "Date is exist and matched");

             }else{
                 Log.i(TAG, "Date is not exist and matched");

             };
                if(checkWeather(objectSituation)){
                    Log.i(TAG, "Weather is exist and matched");

                }else {
                    Log.i(TAG, "Weather is  not exist and matched");
                };
                break;*/
            case FenceState.FALSE:
                Log.i(TAG, "User is not walking");
                break;
            case FenceState.UNKNOWN:
                Log.i(TAG, "User is doing something unknown");
                break;
        }
    }

    public boolean checkDate(ObjectSituation objectSituation) {
        String date = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        if (objectSituation.getDate() == null) {
            return true;
        } else if (date.equals(objectSituation.getDate())){

                return true;
            }

        else{
            return false;

        }
    }

    public boolean checkWeather(ObjectSituation objectSituation) {
        MainActivity mainActivity=new MainActivity();
       if(objectSituation.getWeather()==-1){
           return true;
       }
       else {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            GoogleApiClient client = new GoogleApiClient.Builder(context)
                    .addApi(Awareness.API)
                    .build();
            client.connect();
           Awareness.getSnapshotClient(context).getWeather().addOnSuccessListener((Activity) context, new OnSuccessListener<WeatherResponse>() {
               @Override
               public void onSuccess(WeatherResponse weatherResponse) {
                  int[] asd= weatherResponse.getWeather().getConditions();
                  String asdd="SDFSDF";
               }
           }).addOnFailureListener((Activity) context, new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   String asdd="SDFSDF";

               }
           });

            if (Awareness.getSnapshotClient(context).getWeather().equals(objectSituation.getWeather())) {

                return true;
            }
            return false;
        }


    }
}

