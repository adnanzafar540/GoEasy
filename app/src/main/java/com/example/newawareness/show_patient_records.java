package com.example.newawareness;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newawareness.Adapters.show_patient_records_adapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class show_patient_records extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference root=firebaseDatabase.getReference().child("patienthistory");

    com.example.newawareness.Adapters.show_patient_records_adapter show_patient_records_adapter;
    ArrayList<Dataholder> list;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_record_recylerview);
        RecyclerView recyclerView = findViewById(R.id.records_recyclerrview);
        LinearLayoutManager layoutManager =
                new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        show_patient_records_adapter = new show_patient_records_adapter(list,this);
        recyclerView.setAdapter(show_patient_records_adapter);
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Dataholder dataholder=dataSnapshot.getValue(Dataholder.class);
                    list.add(dataholder);
                }
                show_patient_records_adapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


}
}
