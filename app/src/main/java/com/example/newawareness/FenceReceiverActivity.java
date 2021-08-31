package com.example.newawareness;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newawareness.Database.DatabaseClass;
import com.example.newawareness.Objects.ObjectSituation;
import com.google.android.gms.awareness.fence.FenceState;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class FenceReceiverActivity extends BroadcastReceiver {
    String fenceKey;
    Context context;
    String wheather;
    TimeDate timeDate;
    private static final String TAG = "FenceReceiver";
    private PackageManager packageManager = null;

    public FenceReceiverActivity(Activity activityContext) {
        context = activityContext;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        FenceState fenceState = FenceState.extract(intent);
        switch (fenceState.getCurrentState()) {
            case FenceState.TRUE:
                fenceKey = fenceState.getFenceKey();
                DatabaseClass databaseClass = new DatabaseClass(context);
                timeDate=new TimeDate();
                int key = databaseClass.latestPrimarykey();
                ObjectSituation objectSituation = databaseClass.checkKey_GetData(key);
                wheatherexists(objectSituation);
                if ((objectSituation.getAction() == 1) &&(objectSituation.getSwitchActive())) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "My Notiication")
                            .setContentTitle("NOTIFICATION")
                            .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                            .setContentText(objectSituation.getNotification())
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    builder.setVibrate(new long[]{1000, 1000});
                    builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    Random random = new Random();
                    int m = random.nextInt(9999 - 1000) + 1000;
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(m, builder.build());
                }
               else if ((objectSituation.getAction() == 0) &&(objectSituation.getSwitchActive())) {

                    Intent intent1 = context.getPackageManager().getLaunchIntentForPackage(objectSituation.getPakagename());
                    if (intent1 != null) {
                        context.startActivity(intent1);
                    }else {
                        Log.i(TAG, "just for checking");
                    }
                }
              else{Log.i(TAG, "just for checking");}
                break;
            case FenceState.FALSE:
                Log.i(TAG, "User is not walking");
                break;
            case FenceState.UNKNOWN:
                Log.i(TAG, "User is doing something unknown");
                break;
        }
    }

    public boolean checkWeather(ObjectSituation objectSituation) {
             String cityname = "lahore";
            String countryname = "pakistan";
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityname + "," + countryname + "uk&APPID=a46e8db6cdb0ae1b25ec614aa18a8c52";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String RESPONCE = response;
                    try {
                        JSONObject jsonresponce = new JSONObject(response);
                        JSONArray array = jsonresponce.getJSONArray("weather");
                        JSONObject object = array.getJSONObject(0);
                        wheather = object.getString("main");
                        chkweathertxt(objectSituation,wheather);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
            requestQueue.add(stringRequest);
             return true;
        }
    public void wheatherexists(ObjectSituation objectSituation) {
        if (objectSituation.getWeather() == 0) {
            return;

        } else {
            checkWeather(objectSituation);
        }
    }
    public boolean chkweathertxt(ObjectSituation objectSituation,String wheather) {
        if (wheather.equals(objectSituation.getWeather_txt())) {
            return  true;
        }else {
            return false;
        }
    }
}















