package com.example.newawareness.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newawareness.Dataholder;
import com.example.newawareness.PatientLoginActivity;
import com.example.newawareness.R;
import com.example.newawareness.ShowORAddRecordsActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPatientRecordsActivity extends AppCompatActivity {
    EditText patientname;
    EditText Doctorname;
    EditText Descriptionnn;
    EditText date;
    EditText record_no;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_patient_record);
        patientname = (EditText) findViewById(R.id.et_patient_record_patientName);
        Doctorname = (EditText) findViewById(R.id.et_patient_record_Doctor_name);
        Descriptionnn = (EditText) findViewById(R.id.et_patient_record_description);
        date = (EditText) findViewById(R.id.et_patient_record_date);
        record_no = (EditText) findViewById(R.id.et_patient_record_no);
    }

    public void onBackPressed() {
        Intent moveback =
                new Intent(AddPatientRecordsActivity.this, ShowORAddRecordsActivity.class);
        startActivity(moveback);
        finish();
    }

    public void process(View view) {
        String Date = date.getText().toString();
        String Description = Descriptionnn.getText().toString();
        String PatientName = patientname.getText().toString();
        String DoctorName = Doctorname.getText().toString();
        String record = record_no.getText().toString();
        Dataholder Data = new Dataholder(Date, PatientLoginActivity.Email, DoctorName, Description, PatientName, record);
        FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference("patienthistory");
        reference.child(record).setValue(Data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid)
            {
                Log.d("app","data inserted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("app","failed to inserted: "+e.getMessage());
            }
        });

        patientname.setText("");
        Doctorname.setText("");
        Descriptionnn.setText("");
        date.setText("");
        record_no.setText("");
    }
}