package com.example.newawareness;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Adapters.AppsAdapter;

import java.util.ArrayList;
import java.util.List;

public class InstalledAppsActivity extends AppCompatActivity implements AppsAdapter.OnNoteListner {
    private PackageManager packageManager = null;
    private List<ApplicationInfo> applist = null;
    TextView AppsName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.installed_apps_recyclerview);
        RecyclerView AppListRecycleView = findViewById(R.id.Applist);
        AppsName = (TextView) findViewById(R.id.Appname);
        AppListRecycleView.setLayoutManager(new LinearLayoutManager(this));
        final PackageManager packageManager = getPackageManager();
        applist = checkForLaunchIntent(packageManager.getInstalledApplications(packageManager.GET_META_DATA));
        AppListRecycleView.setAdapter(new AppsAdapter(this, applist, this));
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(InstalledAppsActivity.this, MainActivity.class);
        startActivity(i);
    }
    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> appslist = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        for (ApplicationInfo info : list) {
            try {
                if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                    appslist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appslist;
    }
    @Override
    public void onNoteClick(int position) {
        PackageManager packageManager = getPackageManager();
        ApplicationInfo data = applist.get(position);
        Intent intent = new Intent();
        intent.putExtra("nameOfAction", data.loadLabel(packageManager));
        setResult(5, intent);
        finish();
    }
}
