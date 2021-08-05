package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Patiet extends AppCompatActivity {
    EditText patientname;
    EditText Doctorname;
    EditText Descriptionnn;
    EditText date;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient);
        patientname=(EditText)findViewById(R.id.patientName);
        Doctorname=(EditText)findViewById(R.id.Doctornamr);
        Descriptionnn=(EditText)findViewById(R.id.description);
        date=(EditText)findViewById(R.id.date);
    }



    public void onBackPressed()
    {

        Intent moveback =
                new Intent(Add_Patiet.this, Show_Add_records.class);
        startActivity(moveback);
        finish();
    }

    public void process(View view){


        String Date=date.getText().toString();
        String Description=Descriptionnn.getText().toString();
        String PatientName=patientname.getText().toString();
        String DoctorName=Doctorname.getText().toString();
        Dataholder Data=new Dataholder(Date,DoctorName,Description,PatientName);
        String user= FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference reference=db.getReference("patienthistory");
        reference.child(Date).child(user).setValue(Data);

        patientname.setText("");
        Doctorname.setText("");
        Descriptionnn.setText("");
        date.setText("");

    }
}
