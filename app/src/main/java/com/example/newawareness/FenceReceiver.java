package com.example.newawareness;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.awareness.fence.FenceState;

public class FenceReceiver extends BroadcastReceiver {
    String Key;
    String SituationName;
    private static final String TAG = "FenceReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);

        switch (fenceState.getCurrentState()) {
            case FenceState.TRUE:
               Key=fenceState.getFenceKey();
               //1 query from DB using kye, this mehtod should return objectsituatin
                //Using Snapshot api, check if the weather is same
                // check if the date condition exists, if yes, compare date with mobiles date.

                Log.i(TAG, "User is walking");
                break;
            case FenceState.FALSE:
                Log.i(TAG, "User is not walking");
                break;
            case FenceState.UNKNOWN:
                Log.i(TAG, "User is doing something unknown");
                break;
        }
    }
    }

