package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newawareness.Activities.AddPatientRecordsActivity;

public class ShowORAddRecordsActivity extends AppCompatActivity {
    EditText Add_Records;
    EditText Show_Records;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_or_show_records);
        Add_Records = (EditText) findViewById(R.id.btn_add_record);
        Show_Records = (EditText) findViewById(R.id.btn_show_record);
        Show_Records.setFocusable(false);
        Add_Records.setFocusable(false);

        Show_Records.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(ShowORAddRecordsActivity.this, ShowPatientRecordsActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
        Add_Records.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(ShowORAddRecordsActivity.this, AddPatientRecordsActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
    }
    public void onBackPressed() {
        Intent moveback =
                new Intent(ShowORAddRecordsActivity.this, PatientLoginActivity.class);
        startActivity(moveback);
        finish();
    }
}
