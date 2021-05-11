package com.example.newawareness;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.awareness.Awareness;
import com.google.android.gms.awareness.fence.AwarenessFence;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.awareness.fence.HeadphoneFence;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class FenceActivity extends AppCompatActivity {
    private static final String FENCE_RECEIVER_ACTION = BuildConfig.APPLICATION_ID +
            "FENCE_RECEIVER_ACTION";
    private static final String FENCE_KEY = "walkingFenceKey";
    private FenceReceiver mFenceReceiver;
    private PendingIntent mPendingIntent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void createFence(int state){
        GoogleApiClient client = new GoogleApiClient.Builder(FenceActivity.this)
                .addApi(Awareness.API)
                .build();
        client.connect();
        mPendingIntent = PendingIntent.getBroadcast(this, 0,
                new Intent(FENCE_RECEIVER_ACTION), 0);
        registerReceiver(mFenceReceiver, new IntentFilter(FENCE_RECEIVER_ACTION));
        AwarenessFence fence = HeadphoneFence.during(state);
        Awareness.FenceApi.updateFences(client, new FenceUpdateRequest.Builder()
                .addFence(FENCE_KEY, fence, mPendingIntent)
                .build())
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if (status.isSuccess()) {
                            Log.i(FENCE_KEY, "Successfully registered.");
                        } else {
                            Log.e(FENCE_KEY, "Could not be registered: " + status);
                        }
                    }
                });
    }
}








