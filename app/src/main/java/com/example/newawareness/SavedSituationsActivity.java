package com.example.newawareness;

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

        setContentView(R.layout.saved_situations_recyclerrview);
        db = new DatabaseClass(this);
        mainActivity=new MainActivity();
        mainActivity.checkExistanceforHeadphone();
        mainActivity.checkExistanceforphysicalAction();
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
    public void forcheckIDandSwitch(int id,boolean b){
        db.update(id,b);
    }
}
