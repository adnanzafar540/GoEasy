package com.example.newawareness;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.core.app.NotificationCompat;

public class FenceReceiver extends BroadcastReceiver {
    String fenceKey;
    Context context;
    String wheather;
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
                DatabaseClass databaseClass = new DatabaseClass(context);
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
                break;
            case FenceState.FALSE:
                Log.i(TAG, "User is not walking");
                break;
            case FenceState.UNKNOWN:
                Log.i(TAG, "User is doing something unknown");
                break;
        }
    }

    public void makeNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, getChannelId())
                .setContentTitle("Hi")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("Welcome to Android");

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);
        if (shouldSound && !shouldVibrate) {
            builder.setDefaults(Notification.DEFAULT_SOUND)
                    .setVibrate(new long[]{0L});
        }
        if (shouldVibrate && !shouldSound) {
            builder.setDefaults(Notification.DEFAULT_VIBRATE)
                    .setSound(null);
        }
        if (shouldSound && shouldVibrate) {
            builder.setDefaults(Notification.DEFAULT_ALL);
        }


        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
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
        if(objectSituation.getWeather()==0){
            return true;
        }
        else {
            String cityname = objectSituation.getCity_name();
            String countryname = objectSituation.getCountry_name();

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
            if (wheather == objectSituation.getWeather_txt()) {
                return true;
            }
        }
        return false;
    }


}















