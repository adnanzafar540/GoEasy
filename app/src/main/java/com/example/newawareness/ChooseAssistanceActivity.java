package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseAssistanceActivity extends AppCompatActivity {
    Button VirtualAssistant;
    Button HealthAssistant;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_assistance);
        VirtualAssistant = (Button) findViewById(R.id.virtual);
        HealthAssistant = (Button) findViewById(R.id.Health);
        HealthAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PatientLoginActivity.class);
                startActivity(i);
            }
        });
        VirtualAssistant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

}
