package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Show_Add_records extends AppCompatActivity {
    EditText Add_Records;
    EditText Show_Records;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_show_records);
        Add_Records=(EditText)findViewById(R.id.Addrecord);
        Show_Records=(EditText)findViewById(R.id.showrecord);
        Show_Records.setFocusable(false);
        Add_Records.setFocusable(false);
        Show_Records.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(Show_Add_records.this, show_patient_records.class);
                startActivity(intent);
                finish();
                return false;            }
        });
        Add_Records.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(Show_Add_records.this, Add_Patiet.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
    }
}
