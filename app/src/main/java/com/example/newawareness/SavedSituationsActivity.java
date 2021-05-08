package com.example.newawareness;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Adapters.SituationsAdapter;
import com.example.newawareness.Database.DatabaseActivity;

public class SavedSituationsActivity extends AppCompatActivity {
    SituationsAdapter adapter;
    DatabaseActivity db;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_situations_recyclerrview);
        db = new DatabaseActivity(this);
        RecyclerView recyclerView = findViewById(R.id.sitrecyclerrview);
        LinearLayoutManager layoutManager =
                new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SituationsAdapter(this, db.readAllData());
        recyclerView.setAdapter(adapter);
    }
}
