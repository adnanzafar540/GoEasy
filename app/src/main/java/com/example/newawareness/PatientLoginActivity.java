package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PatientLoginActivity extends AppCompatActivity {
    Button submit;
    TextView email;
    TextView Password;
    FirebaseAuth mAuth;
        TextView signupAccount;
        public static String Email;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_login);
        mAuth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.patient_login_email);
        Password = (EditText) findViewById(R.id.patient_login_password);
        signupAccount = (TextView) findViewById(R.id.tv_patient_signup);
        submit = (Button) findViewById(R.id.btn_patient_login);
        signupAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientLoginActivity.this, PatientSignUPActivity.class);
                startActivity(intent);
                finish();
                email.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
            }
        });
    }
    public void singin(View view) {
         Email = email.getText().toString();
        final String Pass = Password.getText().toString();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return; }
        mAuth.signInWithEmailAndPassword(Email, Pass)
                .addOnCompleteListener(PatientLoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (Pass.length() < 6) {
                                Toast.makeText(PatientLoginActivity.this,"Please Enter Password Length Above 6", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PatientLoginActivity.this,"Not Authentic Loag_in Details", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(PatientLoginActivity.this, ShowORAddRecordsActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }


























}