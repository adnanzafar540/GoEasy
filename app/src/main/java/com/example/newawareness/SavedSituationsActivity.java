package com.example.newawareness;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Adapters.SituationsAdapter;
import com.example.newawareness.Database.DatabaseClass;

public class SavedSituationsActivity extends AppCompatActivity implements OnItemClick{
    SituationsAdapter adapter;
    DatabaseClass db;
    MainActivity mainActivity;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel(this);

        setContentView(R.layout.saved_situations_recyclerrview);
        db = new DatabaseClass(this);
        mainActivity=new MainActivity();
        mainActivity.checkExistanceforHeadphone();
        mainActivity.checkExistanceforphysicalAction();
       createNotificationChannel(this);
        RecyclerView recyclerView = findViewById(R.id.sitrecyclerrview);
        LinearLayoutManager layoutManager =
                new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SituationsAdapter(this, db.readAllData(),this);
        recyclerView.setAdapter(adapter);
    }
    public boolean onClickswitchCheck(boolean boolea) {
        return boolea;
    }

    @Override
    public long onClickgetid(long id) {
        return id;
    }

    @Override
    public void forcheckIDandSwitch(int id,boolean b ) {
        DatabaseClass db=new DatabaseClass(this);
        db.update(id,b);
    }


 /*   public void viewNotification(String Notification){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "My Notiication")
                .setContentTitle("NOTIFICATION")
                .setContentText(Notification)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setVibrate(new long[] { 1000, 1000});
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(m, builder.build());
    }**/
 public void createNotificationChannel(Context context) {
     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
         NotificationChannel channel = new NotificationChannel("My Notiication", "My Notiication", NotificationManager.IMPORTANCE_DEFAULT);
         NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
         notificationManager.createNotificationChannel(channel);
     }
 }


}



