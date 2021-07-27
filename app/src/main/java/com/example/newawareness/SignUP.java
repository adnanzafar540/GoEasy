package com.example.newawareness;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class

SignUP extends AppCompatActivity {
    Button submit;
    TextView email;
    TextView Password;
    FirebaseAuth mAuth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email = (TextView) findViewById(R.id.email1);
        Password = (TextView) findViewById(R.id.password1);
        submit = (Button) findViewById(R.id.submitt1);
    }

    public void singup(View view) {
        String Email = email.getText().toString();
        final String Pass = Password.getText().toString();
        mAuth = FirebaseAuth.getInstance();
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(Pass)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(Email,Pass)
                .addOnCompleteListener(SignUP.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUP.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast.makeText(SignUP.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(SignUP.this,  Show_Add_records.class));
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUP.this, "createUserWithEmail:onComplete:" + e.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }     }


